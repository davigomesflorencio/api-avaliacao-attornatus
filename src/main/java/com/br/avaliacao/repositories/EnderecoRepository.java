package com.br.avaliacao.repositories;

import com.br.avaliacao.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByPessoaId(Long pessoaId);

    Optional<Endereco> findByPessoaIdAndPrincipal(Long pessoaId, Boolean principal);

    Optional<Endereco> findByPessoaIdAndCepAndNumero(Long pessoaId, String cep, Long numero);
}
