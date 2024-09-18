package ru.akvine.wild.emulator.admin.rest.meta.card;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akvine.wild.emulator.admin.rest.dto.card.CardCreateRequest;
import ru.akvine.wild.emulator.common.dto.Response;

@RequestMapping(value = "/cards")
public interface CardControllerMeta {
    @GetMapping
    Response list();

    @PostMapping
    Response create(@RequestBody @Valid CardCreateRequest request);
}
