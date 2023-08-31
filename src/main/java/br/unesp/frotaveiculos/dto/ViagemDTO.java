package br.unesp.frotaveiculos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViagemDTO {

    private Long id;
    private Long solicitanteId;
    private Long motoristaId;
    private Long veiculoId;
    private String cep;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String campusOrigem;
    private LocalDate dataViagem;
    private String passageirosObservacoes;
}
