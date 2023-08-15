package br.unesp.frotaveiculos.usecase.viagem;

import br.unesp.frotaveiculos.dto.MotoristaAtribuicaoDTO;
import br.unesp.frotaveiculos.dto.ViagemDTO;
import br.unesp.frotaveiculos.dto.ViagemMaisInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ViagemUC {
    ViagemDTO cadastrar(ViagemDTO viagemDTO);

    Page<ViagemMaisInfoDTO> listarViagensPorFuncionario(Long matricula, Pageable pageable);

    Page<ViagemMaisInfoDTO> listarViagens(Pageable pageable);

    void atribuirMotorista(Long id, MotoristaAtribuicaoDTO dto);
}
