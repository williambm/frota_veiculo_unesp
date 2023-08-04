package br.unesp.frotaveiculos.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VeiculoUpdateDTO {
    private String placa;
    private String fabricante;
    private String modelo;
    private Integer totalPassageiros;
    private Long quilometragem;
    private Integer anoFabricacao;
    private Boolean possuiCacamba;
}
