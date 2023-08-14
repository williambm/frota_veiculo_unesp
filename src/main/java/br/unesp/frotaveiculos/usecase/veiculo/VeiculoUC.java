package br.unesp.frotaveiculos.usecase.veiculo;

import br.unesp.frotaveiculos.dto.VeiculoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VeiculoUC {
    VeiculoDTO cadastrar(VeiculoDTO veiculoDTO);

    Page<VeiculoDTO> listarComPaginacao(Pageable pageable);

    VeiculoDTO buscarPorId(Long id);

    void deletar(Long id);

    VeiculoDTO atualizar(Long id, VeiculoDTO updateDTO);
}
