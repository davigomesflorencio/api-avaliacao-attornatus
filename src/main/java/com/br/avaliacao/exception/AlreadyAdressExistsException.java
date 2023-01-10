package com.br.avaliacao.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Endereço já existe para uma pessoa!")
public class AlreadyAdressExistsException extends RuntimeException {
    private final HttpStatus httpStatus = HttpStatus.CONFLICT;

    public AlreadyAdressExistsException(Long id, String cep, Long numero, Long pessoaId) {
        super("Já existe um Endereço com o id = " + id + " , cep = " + cep + ", numero = " + numero + " para a Pessoa com id = " + pessoaId + "!");
    }

}
