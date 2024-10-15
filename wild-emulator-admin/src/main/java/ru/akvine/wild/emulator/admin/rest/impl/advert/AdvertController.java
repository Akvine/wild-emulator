package ru.akvine.wild.emulator.admin.rest.impl.advert;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.wild.emulator.admin.rest.converters.advert.AdvertConverter;
import ru.akvine.wild.emulator.admin.rest.dto.advert.AdvertCreateRequest;
import ru.akvine.wild.emulator.admin.rest.dto.advert.AdvertUpdateRequest;
import ru.akvine.wild.emulator.admin.rest.meta.advert.AdvertControllerMeta;
import ru.akvine.wild.emulator.admin.rest.validators.AdvertValidator;
import ru.akvine.wild.emulator.common.dto.NextPage;
import ru.akvine.wild.emulator.common.dto.Response;
import ru.akvine.wild.emulator.core.domain.AdvertModel;
import ru.akvine.wild.emulator.core.services.AdvertService;
import ru.akvine.wild.emulator.core.services.dto.advert.AdvertCreate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdvertController implements AdvertControllerMeta {
    private final AdvertConverter advertConverter;
    private final AdvertService advertService;
    private final AdvertValidator advertValidator;

    @Override
    public Response list(NextPage nextPage) {
        List<AdvertModel> adverts = advertService.list(nextPage.getPage(), nextPage.getCount());
        return advertConverter.convertToConvertAdvertListResponse(adverts);
    }

    @Override
    public Response create(AdvertCreateRequest request) {
        advertValidator.verifyAdvertCreateRequest(request);
        AdvertCreate advertCreate = advertConverter.convertToAdvertCreate(request);
        AdvertModel createdAdvert = advertService.create(advertCreate);
        return advertConverter.convertToConvertAdvertListResponse(List.of(createdAdvert));
    }

    @Override
    public Response update(AdvertUpdateRequest request) {
        return null;
    }
}
