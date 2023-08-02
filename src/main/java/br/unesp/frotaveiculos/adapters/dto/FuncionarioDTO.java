package br.unesp.frotaveiculos.adapters.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FuncionarioDTO {
    private Long matricula;
    private String nome;
    private LocalDate dataAdmissao;
    private LocalDate dataNascimento;
    private String funcao;
}
