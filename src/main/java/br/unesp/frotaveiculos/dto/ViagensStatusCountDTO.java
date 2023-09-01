package br.unesp.frotaveiculos.dto;

import br.unesp.frotaveiculos.adapters.db.model.enumerations.StatusViagem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ViagensStatusCountDTO {

    private StatusViagem status;
    private Long quantidade;

    public ViagensStatusCountDTO(StatusViagem status, Long quantidade) {
        this.status = status;
        this.quantidade = quantidade;
    }
}
