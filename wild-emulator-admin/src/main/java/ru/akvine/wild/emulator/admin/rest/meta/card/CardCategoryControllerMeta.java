package ru.akvine.wild.emulator.admin.rest.meta.card;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akvine.wild.emulator.common.dto.Response;

@RequestMapping(value = "/cards/categories")
public interface CardCategoryControllerMeta {
    @GetMapping
    Response list();
}
