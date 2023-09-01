package br.unesp.frotaveiculos.adapters.db.ports;

import br.unesp.frotaveiculos.dto.MotoristaAtribuicaoDTO;
import br.unesp.frotaveiculos.dto.ViagemDTO;
import br.unesp.frotaveiculos.dto.ViagemMaisInfoDTO;
import br.unesp.frotaveiculos.dto.ViagensStatusCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ViagemPersist {
    ViagemDTO salvar(ViagemDTO viagemDTO);

    Page<ViagemMaisInfoDTO> listarViagensPorFuncionario(Long matricula, Pageable pageable);

    Page<ViagemMaisInfoDTO> listarViagensPaginado(Pageable pageable);

    void atribuirMotorista(Long id, MotoristaAtribuicaoDTO dto);

    List<ViagensStatusCountDTO> buscaHistoricoDeSolicitacoesPorCategoria();
}
