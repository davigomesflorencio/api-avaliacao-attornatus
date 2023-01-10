package com.br.avaliacao.services;

import com.br.avaliacao.entity.Endereco;

import java.util.List;

public interface EnderecoService {
    List<Endereco> list(Long pessoaId);

    Endereco create(Long pessoaId, Endereco endereco);

    Endereco setEnderecoPrincipal(Long pessoaId, Long enderecoId);

    Endereco getEnderecoPrincipal(Long pessoaId);
}
