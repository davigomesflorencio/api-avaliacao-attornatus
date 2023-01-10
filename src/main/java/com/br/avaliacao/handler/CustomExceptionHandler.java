package com.br.avaliacao.handler;

import com.br.avaliacao.exception.AlreadyAdressExistsException;
import com.br.avaliacao.exception.ApiErrorMessage;
import com.br.avaliacao.exception.EntityNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(status, errors);

        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorMessage apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleException(EntityNotFoundException e) {
        ApiErrorMessage apiError = new ApiErrorMessage(e.getHttpStatus(), List.of(e.getMessage()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AlreadyAdressExistsException.class)
    public ResponseEntity<Object> handleException(AlreadyAdressExistsException e) {
        ApiErrorMessage apiError = new ApiErrorMessage(e.getHttpStatus(), List.of(e.getMessage()));
        return buildResponseEntity(apiError);
    }
}