package com.br.avaliacao.services.impl;

import com.br.avaliacao.entity.Endereco;
import com.br.avaliacao.entity.Pessoa;
import com.br.avaliacao.exception.AlreadyAdressExistsException;
import com.br.avaliacao.exception.EntityNotFoundException;
import com.br.avaliacao.repositories.EnderecoRepository;
import com.br.avaliacao.repositories.PessoaRepository;
import com.br.avaliacao.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;

    @Override
    public List<Endereco> list(Long pessoaId) {
        if (!pessoaRepository.existsById(pessoaId)) {
            throw new EntityNotFoundException("Pessoa", pessoaId);
        }
        return enderecoRepository.findByPessoaId(pessoaId);
    }

    @Override
    public Endereco create(Long pessoaId, Endereco endereco) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findByPessoaIdAndCepAndNumero(pessoaId, endereco.getCep(), endereco.getNumero());
        if (enderecoOptional.isPresent()) {
            throw new AlreadyAdressExistsException(enderecoOptional.get().getId(), endereco.getCep(), endereco.getNumero(), pessoaId);
        }
        return pessoaRepository.findById(pessoaId).map(pessoa -> {
            endereco.setPessoa(pessoa);
            return enderecoRepository.save(endereco);
        }).orElseThrow(() -> new EntityNotFoundException("Pessoa", pessoaId));
    }

    @Override
    public Endereco setEnderecoPrincipal(Long pessoaId, Long enderecoId) {
        if (!pessoaRepository.existsById(pessoaId)) {
            throw new EntityNotFoundException("Pessoa", pessoaId);
        }
        if (!enderecoRepository.existsById(enderecoId)) {
            throw new EntityNotFoundException("Endereco", enderecoId);
        }
        Optional<Endereco> existignEndereco = enderecoRepository.findByPessoaIdAndPrincipal(pessoaId, true);

        if (existignEndereco.isPresent()) {
            Endereco endereco = null;
            endereco = existignEndereco.get();
            endereco.setPrincipal(false);
            enderecoRepository.save(endereco);
        }
        return enderecoRepository.findById(enderecoId).map(enderecoFind -> {
            Pessoa pessoa = pessoaRepository.findById(pessoaId).get();
            enderecoFind.setPessoa(pessoa);
            enderecoFind.setPrincipal(true);
            return enderecoRepository.save(enderecoFind);
        }).orElseThrow(() -> new EntityNotFoundException("Endereco", enderecoId));

    }

    @Override
    public Endereco getEnderecoPrincipal(Long pessoaId) {
        if (!pessoaRepository.existsById(pessoaId)) {
            throw new EntityNotFoundException("Pessoa", pessoaId);
        }
        return enderecoRepository.findByPessoaIdAndPrincipal(pessoaId, true).orElseThrow(() -> new EntityNotFoundException("Pessoa", pessoaId, "Endereço principal"));
    }
}
