package ru.akvine.wild.emulator.core.services;

import com.google.common.base.Preconditions;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.akvine.wild.emulator.common.utils.RandomCodeGenerator;
import ru.akvine.wild.emulator.core.constants.UUIDConstants;
import ru.akvine.wild.emulator.core.domain.AdvertModel;
import ru.akvine.wild.emulator.core.domain.meta.AdvertBudgetSpendingModel;
import ru.akvine.wild.emulator.core.enums.AdvertStatus;
import ru.akvine.wild.emulator.core.enums.AdvertType;
import ru.akvine.wild.emulator.core.exceptions.models.AdvertAlreadyRunningException;
import ru.akvine.wild.emulator.core.exceptions.models.AdvertNotFoundException;
import ru.akvine.wild.emulator.core.exceptions.models.AdvertPauseException;
import ru.akvine.wild.emulator.core.exceptions.models.AdvertRunningException;
import ru.akvine.wild.emulator.core.providers.SpendingBudgetFactoryProvider;
import ru.akvine.wild.emulator.core.repositories.AdvertRepository;
import ru.akvine.wild.emulator.core.repositories.entities.AdvertEntity;
import ru.akvine.wild.emulator.core.repositories.entities.CardEntity;
import ru.akvine.wild.emulator.core.repositories.entities.meta.AdvertBudgetSpendingEntity;
import ru.akvine.wild.emulator.core.repositories.meta.AdvertBudgetSpendingRepository;
import ru.akvine.wild.emulator.core.services.card.CardService;
import ru.akvine.wild.emulator.core.services.dto.advert.*;
import ru.akvine.wild.emulator.core.strategy.budget.SpendingBudgetStrategy;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final AdvertBudgetSpendingRepository advertBudgetSpendingRepository;
    private final CardService cardService;
    private final SpendingBudgetFactoryProvider spendingBudgetFactoryProvider;
    private final AdvertBudgetStrategyService advertBudgetStrategyService;

    public List<AdvertModel> list(AdvertList advertList) {
        Preconditions.checkNotNull(advertList, "advertList is null");
        PageRequest pageable = PageRequest.of(
                advertList.getCount(),
                advertList.getPages()
        );
        return advertRepository
                .list(advertList.getClientId(), pageable)
                .stream()
                .map(AdvertModel::new)
                .toList();
    }

    public List<AdvertModel> list(AdvertListByIds advertListByIds) {
        Preconditions.checkNotNull(advertListByIds, "advertListByIds");
        return advertRepository
                .list(advertListByIds.getClientId(),
                        advertListByIds.getIds())
                .stream()
                .map(AdvertModel::new)
                .toList();
    }

    public Map<Integer, Map<Integer, GroupedAdverts>> listAndGroupBy(long clientId) {
        List<AdvertModel> adverts = advertRepository
                .list(clientId)
                .stream()
                .map(AdvertModel::new)
                .toList();
        return adverts.stream()
                .collect(Collectors.groupingBy(
                        AdvertModel::getStatus,
                        Collectors.groupingBy(
                                AdvertModel::getType,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        (List<AdvertModel> advertList) -> new GroupedAdverts(advertList.size(), advertList) // Передаем список с типом Advert
                                )
                        )
                ));
    }

    public AdvertModel create(AdvertCreate advertCreate) {
        Preconditions.checkNotNull(advertCreate, "advertCreate is null");

        CardEntity card = cardService.verifyExistsByUuid(advertCreate.getCardUuid(), advertCreate.getClientId());
        AdvertBudgetSpendingEntity advertBudgetSpendingToSave = new AdvertBudgetSpendingEntity()
                .setValue(advertCreate.getAdvertBudgetSpendingCreate().getValue())
                .setDelaySeconds(advertCreate.getAdvertBudgetSpendingCreate().getDelaySeconds())
                .setType(advertCreate.getAdvertBudgetSpendingCreate().getType());
        AdvertBudgetSpendingEntity savedBudgetEntity = advertBudgetSpendingRepository.save(advertBudgetSpendingToSave);
        AdvertEntity advertToSave = new AdvertEntity()
                .setUuid(RandomCodeGenerator.generateNewRandomNumericCode(UUIDConstants.ADVERT_UUID_LENGTH))
                .setName(advertCreate.getName())
                .setType(AdvertType.AUTO.getCode())
                .setStatus(AdvertStatus.READY_FOR_START.getCode())
                .setChangeTime(new Date())
                .setCpm(advertCreate.getCpm())
                .setBudgetSum(advertCreate.getBudgetSum())
                .setAdvertBudgetSpending(savedBudgetEntity)
                .setCard(card);

        return new AdvertModel(advertRepository.save(advertToSave));
    }

    public AdvertModel run(int uuid) {
        AdvertEntity advert = verifyExistsByUuid(uuid);
        if (advert.isRunning()) {
            throw new AdvertAlreadyRunningException("Advert with uuid = [" + uuid + "] already running!");
        }

        if (advert.getBudgetSum() == 0) {
            String errorMessage = String.format(
                    "Advert with uuid = [%s] can't be running. Sum budget is 0",
                    uuid
            );
            throw new AdvertRunningException(errorMessage);
        }

        AdvertModel advertToRun = new AdvertModel(advert);
        AdvertBudgetSpendingModel advertBudgetSpendingModel = advertToRun.getAdvertBudgetSpendingModel();
        SpendingBudgetStrategy strategyToRun = spendingBudgetFactoryProvider
                .getFactories()
                .get(advertToRun.getAdvertBudgetSpendingModel().getType())
                .create(advertBudgetSpendingModel.getValue(), advertBudgetSpendingModel.getDelaySeconds());

        advertBudgetStrategyService.put(advertToRun.getUuid(), strategyToRun);
        advertToRun.setStatus(AdvertStatus.RUNNING.getCode());
        return update(new AdvertUpdate()
                .setStatus(AdvertStatus.RUNNING.name()));
    }

    public AdvertModel rename(AdvertRename advertRename) {
        Preconditions.checkNotNull(advertRename, "advertRename is null");
        AdvertUpdate advertUpdate = new AdvertUpdate()
                .setUuid(advertRename.getUuid())
                .setName(advertRename.getName());
        return update(advertUpdate);
    }

    public AdvertModel pause(int advertUuid) {
        AdvertEntity advert = verifyExistsByUuid(advertUuid);
        if (advert.getStatus() == AdvertStatus.PAUSE.getCode()) {
            String errorMessage = String.format(
                    "Advert with uuid = [%s] already is pause!",
                    advertUuid
            );
            throw new AdvertPauseException(errorMessage);
        }
        advertBudgetStrategyService.remove(advertUuid);
        AdvertUpdate advertUpdate = new AdvertUpdate()
                .setUuid(advertUuid)
                .setStatus(AdvertStatus.PAUSE.name());
        return update(advertUpdate);
    }

    public AdvertModel deposit(AdvertDeposit advertDeposit) {
        Preconditions.checkNotNull(advertDeposit, "advertDeposit is null");
        int advertUuid = advertDeposit.getUuid();
        AdvertEntity advert = verifyExistsByUuid(advertUuid);

        AdvertUpdate advertUpdate = new AdvertUpdate()
                .setUuid(advertUuid)
                .setBudget(advert.getBudgetSum() + advertDeposit.getSum());
        return update(advertUpdate);
    }

    public AdvertModel update(AdvertUpdate advertUpdate) {
        Preconditions.checkNotNull(advertUpdate, "advertUpdate is null");

        AdvertEntity advertToUpdate = verifyExistsByUuid(advertUpdate.getUuid());
        if (!advertToUpdate.getName().equals(advertUpdate.getName())) {
            advertToUpdate.setName(advertUpdate.getName());
        }
        if (advertToUpdate.getCpm() != advertUpdate.getCpm()) {
            advertToUpdate.setCpm(advertUpdate.getCpm());
        }
        if (advertToUpdate.getBudgetSum() != advertUpdate.getBudget()) {
            advertToUpdate.setBudgetSum(advertUpdate.getBudget());
        }
        if (StringUtils.isNotBlank(advertUpdate.getStatus())) {
            advertToUpdate.setStatus(AdvertStatus.getByValue(advertUpdate.getStatus()).getCode());
        }

        return new AdvertModel(advertRepository.save(advertToUpdate));
    }

    public List<AdvertModel> getByStatus(int status) {
        return advertRepository
                .findByStatus(status)
                .stream()
                .map(AdvertModel::new)
                .toList();
    }

    public AdvertModel getByUuid(int uuid) {
        return new AdvertModel(verifyExistsByUuid(uuid));
    }

    public AdvertEntity verifyExistsByUuid(int uuid) {
        return advertRepository
                .findByUuid(uuid)
                .orElseThrow(() -> new AdvertNotFoundException("Advert with uuid = [" + uuid + "] not found!"));
    }
}
