package br.unesp.frotaveiculos.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
}
