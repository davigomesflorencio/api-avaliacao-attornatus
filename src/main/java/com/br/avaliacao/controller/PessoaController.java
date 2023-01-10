package com.br.avaliacao.controller;

import com.br.avaliacao.entity.Pessoa;
import com.br.avaliacao.services.impl.PessoaServiceImpl;
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
public class PessoaController {
    private final PessoaServiceImpl pessoaService;

    @GetMapping(value = "/pessoa", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pessoa>> findAll() {
        List<Pessoa> list = pessoaService.findAll();
        return ResponseEntity.ok().body(list);
    }


    @GetMapping(value = "/pessoa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.findById(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @PostMapping(value = "/pessoa", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> save(@RequestBody @Valid Pessoa pessoaBody) {
        Pessoa pessoa = pessoaService.create(pessoaBody);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(pessoa.getId())
                .toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

    @PutMapping(value = "/pessoa/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @Valid @RequestBody Pessoa pessoaBody) {
        Pessoa pessoa = pessoaService.edit(id, pessoaBody);
        return ResponseEntity.ok().body(pessoa);
    }
}
