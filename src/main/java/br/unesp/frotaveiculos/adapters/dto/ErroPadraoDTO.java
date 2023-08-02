package br.unesp.frotaveiculos.adapters.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErroPadraoDTO {
    private LocalDateTime dataHoraErro;
    private Integer status;
    private String error;
    private String message;
}
