package ru.akvine.wild.emulator.admin.rest.meta.advert;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.akvine.wild.emulator.admin.rest.dto.advert.AdvertCreateRequest;
import ru.akvine.wild.emulator.admin.rest.dto.advert.AdvertUpdateRequest;
import ru.akvine.wild.emulator.common.dto.NextPage;
import ru.akvine.wild.emulator.common.dto.Response;

@RequestMapping(value = "/adverts")
public interface AdvertControllerMeta {
    @GetMapping
    Response list(@Valid @RequestBody NextPage nextPage);

    @PostMapping
    Response create(@Valid @RequestBody AdvertCreateRequest request);

    @PutMapping
    Response update(@Valid @RequestBody AdvertUpdateRequest request);
}
