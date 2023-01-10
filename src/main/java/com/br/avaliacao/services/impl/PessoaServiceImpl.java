package com.br.avaliacao.services.impl;

import com.br.avaliacao.entity.Pessoa;
import com.br.avaliacao.exception.EntityNotFoundException;
import com.br.avaliacao.repositories.PessoaRepository;
import com.br.avaliacao.services.PessoaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    @Override
    public Pessoa create(Pessoa pessoa) {

        log.info("Criando uma nova pessoa");
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa edit(Long id, Pessoa pessoa) {
        log.info("Editando uma pessoa pelo id = " + id);
        pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa", id));

        pessoa.setId(id);
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa findById(Long id) {
        log.info("Buscando pessoa pelo id = " + id);
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            return pessoa.get();
        } else {
            throw new EntityNotFoundException("Pessoa", id);
        }
    }

    @Override
    public List<Pessoa> findAll() {
        log.info("Buscando todas as pessoas");
        return pessoaRepository.findAll();
    }
}
