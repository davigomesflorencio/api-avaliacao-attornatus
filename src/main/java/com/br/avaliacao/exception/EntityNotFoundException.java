package com.br.avaliacao.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity Not Found")
public class EntityNotFoundException extends RuntimeException {
    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public EntityNotFoundException(String entityName, Long id) {
        super(entityName + " com o id = " + id + " não foi encontrado!");
    }

    public EntityNotFoundException(String entityName, Long id, String name) {
        super(name + " da " + entityName + " com o id = " + id + " não foi encontrado!");
    }
}
