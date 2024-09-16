package ru.akvine.wild.emulator.admin.rest.meta;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akvine.wild.emulator.common.dto.Response;

@RequestMapping(value = "/cards/types")
public interface CardTypeControllerMeta {
    @GetMapping
    Response list();
}
