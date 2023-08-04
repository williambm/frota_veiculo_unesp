package br.unesp.frotaveiculos.usecase.veiculo;

import br.unesp.frotaveiculos.adapters.db.ports.VeiculoPersist;
import br.unesp.frotaveiculos.dto.VeiculoDTO;
import br.unesp.frotaveiculos.dto.VeiculoUpdateDTO;
import br.unesp.frotaveiculos.usecase.exceptions.VeiculoUCExcedePrazoFabricacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDate;

@Service
public class VeiculoUCImpl implements VeiculoUC {

    @Value("${negocio.veiculo.tempo-maximo}")
    private Long quantidadeAnosCarroAceitavel;
    @Autowired
    private VeiculoPersist veiculoPersist;


    @Override
    public VeiculoDTO cadastrar(VeiculoDTO veiculoDTO) {

        validaAnoFabricacao(veiculoDTO);
        return veiculoPersist.salvar(veiculoDTO);
    }


    @Override
    public Page<VeiculoDTO> listarComPaginacao(Pageable pageable) {
        return veiculoPersist.listarVeiculosPaginado(pageable);
    }

    @Override
    public VeiculoDTO buscarPorId(Long id) {
        return veiculoPersist.buscarPorId(id);
    }

    @Override
    public void deletar(Long id) {
        veiculoPersist.deletar(id);
    }

    @Override
    public VeiculoDTO atualizar(Long id, VeiculoUpdateDTO updateDTO) {
        //todo: ajustar isso para que o método seja válido nos casos de cadastro e atualização - talvez unificar DTO - no update estamos sem validação do ano fabricação
        //validaAnoFabricacao(updateDTO);
        return veiculoPersist.atualizar(id, updateDTO);
    }

    /**
     * A Regra de negócio para esse método é que a UNESP não aceitará cadastrar/atualizar carros acima da quantidade de
     * anos de fabricação parametrizada (para este teste deixei 15 anos)
     *
     * @param veiculoDTO
     * @return
     */
    private void validaAnoFabricacao(VeiculoDTO veiculoDTO) {
        if (veiculoDTO.getAnoFabricacao() < LocalDate.now().minusYears(quantidadeAnosCarroAceitavel).getYear()) {
            throw new VeiculoUCExcedePrazoFabricacao(
                    MessageFormat.format(
                            "O veículo que está tentando cadastrar possui {0} anos de fabricação. " +
                                    "Isto excede nossa politíca de carros que possuam no máximo {1} anos de fabricação.",
                            veiculoDTO.getAnoFabricacao(), quantidadeAnosCarroAceitavel
                    )
            );
        }
    }
}
