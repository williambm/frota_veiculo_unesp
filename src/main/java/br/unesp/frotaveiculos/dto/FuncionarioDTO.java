package br.unesp.frotaveiculos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FuncionarioDTO {
    private Long matricula;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataAdmissao;
    private LocalDate dataNascimento;
    private String funcao;
    private String perfil;
    private Long imagemPerfilId;
}
