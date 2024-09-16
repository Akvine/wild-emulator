package ru.akvine.wild.emulator.api.rest.meta;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akvine.wild.emulator.common.dto.Response;

@RequestMapping(value = "/api/cards/types")
public interface ApiCardTypeControllerMeta {
    @GetMapping(value = "/content/v2/directory/kinds")
    Response list(@RequestHeader("Authorization") String token);
}
