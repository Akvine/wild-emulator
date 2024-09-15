package ru.akvine.wild.emulator.admin.rest.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.wild.emulator.admin.rest.dto.AuthRequest;
import ru.akvine.wild.emulator.admin.rest.meta.SecurityControllerMeta;
import ru.akvine.wild.emulator.admin.utils.SecurityHelper;
import ru.akvine.wild.emulator.common.dto.Response;
import ru.akvine.wild.emulator.common.dto.SuccessfulResponse;
import ru.akvine.wild.emulator.core.domain.ClientModel;
import ru.akvine.wild.emulator.core.services.security.AdminAuthenticationService;
import ru.akvine.wild.emulator.core.services.ClientService;

@RestController
@RequiredArgsConstructor
public class SecurityController implements SecurityControllerMeta {
    private final ClientService clientService;
    private final SecurityHelper securityHelper;
    private final AdminAuthenticationService adminAuthenticationService;

    @Override
    public Response registration(@RequestBody @Valid AuthRequest request,
                                 HttpServletRequest httpServletRequest) {
        ClientModel clientModel = clientService.create(request.getEmail(), request.getPassword());
        securityHelper.authenticate(clientModel, httpServletRequest);
        return new SuccessfulResponse();
    }

    @Override
    public Response auth(@RequestBody @Valid AuthRequest request,
                         HttpServletRequest httpServletRequest) {
        ClientModel clientModel = adminAuthenticationService.authenticate(
                request.getEmail(),
                request.getPassword());
        securityHelper.authenticate(clientModel, httpServletRequest);
        return new SuccessfulResponse();
    }

    @Override
    public Response logout(HttpServletRequest request) {
        securityHelper.doLogout(request);
        return new SuccessfulResponse();
    }
}
