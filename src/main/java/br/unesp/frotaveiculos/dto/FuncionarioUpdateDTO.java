package br.unesp.frotaveiculos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FuncionarioUpdateDTO {
    private String nome;
    private LocalDate dataAdmissao;
    private LocalDate dataNascimento;
    private String funcao;
    private String perfil;
}
