package br.unesp.frotaveiculos.adapters.db.ports;

import br.unesp.frotaveiculos.dto.ViagemDTO;

public interface ViagemPersist {
    ViagemDTO salvar(ViagemDTO viagemDTO);
}
