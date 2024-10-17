package ru.akvine.wild.emulator.api.rest.meta;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.akvine.wild.emulator.api.rest.dto.adverts.AdvertChangeCpmRequest;
import ru.akvine.wild.emulator.api.rest.dto.adverts.AdvertDepositRequest;
import ru.akvine.wild.emulator.api.rest.dto.adverts.AdvertRenameRequest;
import ru.akvine.wild.emulator.common.dto.Response;

@RequestMapping(value = "/api/adverts")
public interface ApiAdvertsControllerMeta {
    @GetMapping(value = "/adv/v1/budget")
    Response budget(@RequestParam("id") int uuid,
                    @RequestHeader("Authorization") String token);

    @GetMapping(value = "/adv/v0/start")
    Response start(@RequestParam("id") int id,
                   @RequestHeader("Authorization") String token);

    @PostMapping(value = "/adv/v0/rename")
    Response rename(@Valid @RequestBody AdvertRenameRequest request,
                    @RequestHeader("Authorization") String token);

    @GetMapping(value = "/adv/v0/pause")
    Response pause(@RequestParam("id") int uuid,
                   @RequestHeader("Authorization") String token);

    @PostMapping(value = "/adv/v0/cpm")
    Response cpm(@Valid @RequestBody AdvertChangeCpmRequest request,
                 @RequestHeader("Authorization") String token);

    @PostMapping(value = "/adv/v1/budget/deposit")
    Response deposit(@RequestParam("id") int uuid,
                     @Valid @RequestBody AdvertDepositRequest request,
                     @RequestHeader("Authorization") String token);
}
