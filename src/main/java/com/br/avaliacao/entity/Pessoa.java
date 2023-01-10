package com.br.avaliacao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "pessoas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @Column(name = "nome")
    @NotBlank(message = "nome é um campo obrigatório!")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dt_nascimento;
}
