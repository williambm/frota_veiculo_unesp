package br.unesp.frotaveiculos.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ViagemMaisInfoDTO {
    private Long id;
    private Long solicitanteId;
    private String solicitanteNome;
    private Long motoristaId;
    private String motoristaNome;
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
}
