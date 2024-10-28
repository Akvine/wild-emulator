package ru.akvine.wild.emulator.api.rest.meta;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.akvine.wild.emulator.api.rest.dto.card.good.SetGoodPriceAndDiscountRequest;
import ru.akvine.wild.emulator.common.dto.Response;

@RequestMapping(value = "/api/cards")
public interface ApiCardControllerMeta {

    @PostMapping(value = "/content/v2/get/cards/list")
    Response list(@RequestHeader("Authorization") String token);

    @GetMapping(value = "/list/goods/filter")
    Response listGoods(@RequestHeader("Authorization") String token,
                       @RequestParam("filterNmID") int uuid);

    @PostMapping(value = "/upload/task")
    Response setGoodsAndDiscount(@RequestHeader("Authorization") String token,
                                 @RequestBody @Valid SetGoodPriceAndDiscountRequest request);
}
