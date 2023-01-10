package com.br.avaliacao;

import com.br.avaliacao.entity.Endereco;
import com.br.avaliacao.entity.Pessoa;
import com.br.avaliacao.repositories.EnderecoRepository;
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
public class EnderecoControllerTests {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    private String baseUrl = "http://localhost:8080/";

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @Test
    void shouldCreateEnderecoTest() {
        Pessoa pessoa = Pessoa.builder().dt_nascimento(LocalDate.of(2000, Month.APRIL, 22)).nome("davi").build();
        Pessoa newPessoa = restTemplate.postForObject(baseUrl + "/pessoa/", pessoa, Pessoa.class);

        Endereco endereco = Endereco.builder().pessoa(newPessoa).cep("63900-351").numero(1L).cidade("Canindé").logradouro("l1").principal(false).build();

        Endereco enderecoCreated = restTemplate.postForObject(baseUrl + "/pessoa/" + newPessoa.getId() + "/endereco", endereco, Endereco.class);

        assertNotNull(newPessoa);
        assertThat(newPessoa.getId()).isNotNull();
        assertNotNull(enderecoCreated);
        assertThat(enderecoCreated.getId()).isNotNull();
    }

    @Test
    void shouldSizeListEnderecoTest() {
        Pessoa pessoa = Pessoa.builder().dt_nascimento(LocalDate.of(2000, Month.APRIL, 22)).nome("davi").build();
        Pessoa newPessoa = restTemplate.postForObject(baseUrl + "/pessoa/", pessoa, Pessoa.class);

        Endereco endereco1 = Endereco.builder().pessoa(newPessoa).cep("63900-351").numero(1L).cidade("Canindé").logradouro("l1").principal(false).build();
        Endereco endereco2 = Endereco.builder().pessoa(newPessoa).cep("63900-352").numero(2L).cidade("Canindé").logradouro("l2").principal(false).build();

        Endereco enderecoCreated1 = restTemplate.postForObject(baseUrl + "/pessoa/" + newPessoa.getId() + "/endereco", endereco1, Endereco.class);
        Endereco enderecoCreated2 = restTemplate.postForObject(baseUrl + "/pessoa/" + newPessoa.getId() + "/endereco", endereco2, Endereco.class);

        List<Endereco> enderecoList = restTemplate.getForObject(baseUrl + "/pessoa/" + newPessoa.getId() + "/enderecos", List.class);

        assertNotNull(newPessoa);
        assertThat(newPessoa.getId()).isNotNull();

        assertNotNull(enderecoCreated1);
        assertThat(enderecoCreated1.getId()).isNotNull();

        assertNotNull(enderecoCreated2);
        assertThat(enderecoCreated2.getId()).isNotNull();

        assertEquals(2, enderecoList.size());
    }

    @Test
    void shouldEnderecoPrincipalTest() {
        Pessoa pessoa = Pessoa.builder().dt_nascimento(LocalDate.of(2000, Month.APRIL, 22)).nome("davi").build();
        Pessoa newPessoa = restTemplate.postForObject(baseUrl + "/pessoa/", pessoa, Pessoa.class);

        Endereco endereco1 = Endereco.builder().pessoa(newPessoa).cep("63900-351").numero(1L).cidade("Canindé").logradouro("l1").principal(false).build();
        Endereco enderecoCreated1 = restTemplate.postForObject(baseUrl + "/pessoa/" + newPessoa.getId() + "/endereco", endereco1, Endereco.class);

        Endereco UpdateEndereco = restTemplate.postForObject(baseUrl + "/pessoa/" + newPessoa.getId() + "/endereco/" + enderecoCreated1.getId(), endereco1, Endereco.class);

        assertNotNull(newPessoa);
        assertThat(newPessoa.getId()).isNotNull();

        assertNotNull(enderecoCreated1);
        assertThat(enderecoCreated1.getId()).isNotNull();

        assertEquals(true, UpdateEndereco.getPrincipal());
    }
}
