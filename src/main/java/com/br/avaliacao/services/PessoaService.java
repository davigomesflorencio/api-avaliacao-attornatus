package com.br.avaliacao.services;

import com.br.avaliacao.entity.Pessoa;

import java.util.List;

public interface PessoaService {
    Pessoa create(Pessoa pessoa);

    Pessoa edit(Long id, Pessoa pessoa);

    Pessoa findById(Long id);

    List<Pessoa> findAll();


}
