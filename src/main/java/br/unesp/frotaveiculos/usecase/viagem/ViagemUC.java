package br.unesp.frotaveiculos.usecase.viagem;

import br.unesp.frotaveiculos.dto.ViagemDTO;

public interface ViagemUC {
    ViagemDTO cadastrar(ViagemDTO viagemDTO);
}
