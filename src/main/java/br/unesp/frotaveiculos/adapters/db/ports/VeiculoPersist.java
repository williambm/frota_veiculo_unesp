package br.unesp.frotaveiculos.adapters.db.ports;

import br.unesp.frotaveiculos.dto.VeiculoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VeiculoPersist {
    VeiculoDTO salvar(VeiculoDTO veiculoDTO);

    Page<VeiculoDTO> listarVeiculosPaginado(Pageable pageable);

    VeiculoDTO buscarPorId(Long id);

    void deletar(Long id);

    VeiculoDTO atualizar(Long id, VeiculoDTO updateDTO);
}
