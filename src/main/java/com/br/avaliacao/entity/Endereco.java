package com.br.avaliacao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "endereços", uniqueConstraints = {@UniqueConstraint(name = "CepAndNumber", columnNames = {"pessoa_id", "cep", "numero"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "logradouro é um campo obrigatório!")
    private String logradouro;
    @NotBlank(message = "CEP é um campo obrigatório!")
    @Pattern(regexp = "^[0-9]{5}-[0-9]{3}$", message = "Formato do CEP inválido!")
    private String cep;
    @NotNull(message = "numero é um campo obrigatório!")
    private Long numero;
    @NotBlank(message = "cidade é um campo obrigatório!")
    private String cidade;

    private Boolean principal = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pessoa_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Pessoa pessoa;
}
