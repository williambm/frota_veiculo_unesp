package br.unesp.frotaveiculos.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FuncionarioDTORequest {
    private Long matricula;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataAdmissao;
    private LocalDate dataNascimento;
    private String funcao;
    private String perfil;
}
