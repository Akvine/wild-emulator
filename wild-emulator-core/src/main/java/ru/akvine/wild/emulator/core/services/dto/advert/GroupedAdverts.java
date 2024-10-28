package ru.akvine.wild.emulator.core.services.dto.advert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.core.domain.AdvertModel;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class GroupedAdverts {
    private int count;
    private List<AdvertModel> advertList;
}
