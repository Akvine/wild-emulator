package ru.akvine.wild.emulator.api.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.akvine.wild.emulator.api.constants.ApiErrorConstants;
import ru.akvine.wild.emulator.common.dto.ErrorResponse;
import ru.akvine.wild.emulator.core.exceptions.AuthException;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({AuthException.class})
    public ResponseEntity<ErrorResponse> handleAuthException(AuthException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                ApiErrorConstants.AUTH_ERROR,
                exception.getMessage(),
                exception.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
