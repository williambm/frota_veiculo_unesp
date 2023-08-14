package br.unesp.frotaveiculos.usecase.viagem;

import br.unesp.frotaveiculos.adapters.db.ports.ViagemPersist;
import br.unesp.frotaveiculos.dto.ViagemDTO;
import br.unesp.frotaveiculos.dto.ViagemMaisInfoDTO;
import br.unesp.frotaveiculos.usecase.exceptions.ViagemUCExedePrazoSolicitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDate;

@Service
public class ViagemUCImpl implements ViagemUC {

    @Value("${negocio.viagem.dias-antecedencia}")
    private Long quantidadeDiasAntecedenciaParSolicitar;

    @Autowired
    private ViagemPersist viagemPersist;

    @Override
    public ViagemDTO cadastrar(ViagemDTO viagemDTO) {

        validarPrazoSolicitacao(viagemDTO);
        return viagemPersist.salvar(viagemDTO);

    }

    @Override
    public Page<ViagemMaisInfoDTO> listarViagensPorFuncionario(Long matricula, Pageable pageable) {
        return viagemPersist.listarViagensPorFuncionario(matricula, pageable);
    }

    @Override
    public Page<ViagemMaisInfoDTO> listarViagens(Pageable pageable) {
        return viagemPersist.listarViagensPaginado(pageable);
    }

    private void validarPrazoSolicitacao(ViagemDTO viagemDTO) {
        if (viagemDTO.getDataViagem().isBefore(LocalDate.now().minusDays(quantidadeDiasAntecedenciaParSolicitar))) {
            throw new ViagemUCExedePrazoSolicitacao(
                    MessageFormat.format(
                            "O prazo máximo para solicitar uma viagem deve anteceder a {0} dias da data atual. ",
                            quantidadeDiasAntecedenciaParSolicitar
                    )
            );
        }
    }
}
