package ru.akvine.wild.emulator.api.rest.dto.adverts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdvertDepositRequest {
    private int sum;
    private int type;
    @JsonProperty(value = "return")
    private boolean isReturn;
}
