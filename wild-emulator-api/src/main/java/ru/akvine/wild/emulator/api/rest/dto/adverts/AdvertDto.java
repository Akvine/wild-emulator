package ru.akvine.wild.emulator.api.rest.dto.adverts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class AdvertDto {
    private int advertId;
    private String name;
    private Date changeTime;
    private int type;
    private int status;
    @JsonProperty(value = "autoParams")
    private AdvertParams advertParams;
}
