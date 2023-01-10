package com.br.avaliacao;

import com.br.avaliacao.entity.Pessoa;
import com.br.avaliacao.repositories.PessoaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaControllerTests {

    @Autowired
    private PessoaRepository pessoaRepository;

    private String baseUrl = "http://localhost:8080/";

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @Test
    void shouldCreatePessoaTest() {
        Pessoa pessoa = Pessoa.builder().id(1L).dt_nascimento(LocalDate.of(2000, Month.APRIL, 22)).nome("davi").build();
        Pessoa newPessoa = restTemplate.postForObject(baseUrl + "/pessoa/", pessoa, Pessoa.class);

        assertNotNull(newPessoa);
        assertThat(newPessoa.getId()).isNotNull();
    }

    @Test
    void shouldFetchPessoaTest() {
        List<Pessoa> list = restTemplate.getForObject(baseUrl + "/pessoa/", List.class);

        assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void shouldFetchOnePessoaTest() {
        Pessoa existingPessoa = restTemplate.getForObject(baseUrl + "/pessoa/1", Pessoa.class);

        assertNotNull(existingPessoa);
        assertThat(!existingPessoa.getNome().isEmpty());
    }

    @Test
    void shouldUpdatePessoaTest() {
        Pessoa existingPessoa = restTemplate.getForObject(baseUrl + "/pessoa/1", Pessoa.class);
        existingPessoa.setNome("David");

        restTemplate.put(baseUrl + "/pessoa/{id}", existingPessoa, existingPessoa.getId());

        Pessoa newPessoa = restTemplate.getForObject(baseUrl + "/pessoa/" + existingPessoa.getId(), Pessoa.class);

        assertNotNull(newPessoa);
        assertEquals("David", newPessoa.getNome());
    }
}
