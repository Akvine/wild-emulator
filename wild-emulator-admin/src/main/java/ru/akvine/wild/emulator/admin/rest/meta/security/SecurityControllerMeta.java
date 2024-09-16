package ru.akvine.wild.emulator.admin.rest.meta.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akvine.wild.emulator.admin.rest.dto.AuthRequest;
import ru.akvine.wild.emulator.common.dto.Response;

@RequestMapping(value = "/security")
public interface SecurityControllerMeta {
    @PostMapping(value = "/registration")
    Response registration(@RequestBody @Valid AuthRequest request, HttpServletRequest httpServletRequest);

    @PostMapping(value = "/auth")
    Response auth(@RequestBody @Valid AuthRequest request, HttpServletRequest httpServletRequest);

    @PostMapping(value = "/logout")
    Response logout(HttpServletRequest request);
}
