package com.br.avaliacao.controller;

import com.br.avaliacao.entity.Endereco;
import com.br.avaliacao.services.impl.EnderecoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@Validated
public class EnderecoController {

    private final EnderecoServiceImpl enderecoService;

    @GetMapping(value = "/pessoa/{id}/enderecos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Endereco>> findAllByPessoaId(@PathVariable("id") Long pessoaId) {
        List<Endereco> list = enderecoService.list(pessoaId);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/pessoa/{id}/endereco", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Endereco> save(@PathVariable("id") Long pessoaId, @RequestBody @Valid Endereco enderecoBody) {
        Endereco endereco = enderecoService.create(pessoaId, enderecoBody);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(enderecoBody.getId())
                .toUri();
        return ResponseEntity.created(uri).body(enderecoBody);
    }

    @PostMapping(value = "/pessoa/{id}/endereco/{enderecoId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Endereco> setAdressPrincipal(@PathVariable("id") Long pessoaId, @PathVariable("enderecoId") Long enderecoId) {
        Endereco endereco = enderecoService.setEnderecoPrincipal(pessoaId, enderecoId);
        return ResponseEntity.ok().body(endereco);
    }

    @GetMapping(value = "/pessoa/{id}/endereco/principal", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Endereco> getAdressPrincipal(@PathVariable("id") Long pessoaId) {
        Endereco endereco = enderecoService.getEnderecoPrincipal(pessoaId);
        return ResponseEntity.ok().body(endereco);
}
}
