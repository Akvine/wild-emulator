package ru.akvine.wild.emulator.api.rest.meta;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akvine.wild.emulator.common.dto.Response;

@RequestMapping(value = "/api/cards")
public interface ApiCardControllerMeta {

    @PostMapping(value = "/content/v2/get/cards/list")
    Response list(@RequestHeader("Authorization") String token);
}
