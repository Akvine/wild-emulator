package ru.akvine.wild.emulator.api.rest.dto.card;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ApiCardDto {
    private int nmID;
    private String title;
    private int subjectID;
    private String subjectName;
    private List<ApiSizeDto> sizes;
    private String updatedAt;
    private List<ApiCardCharacteristic> characteristics;
}
