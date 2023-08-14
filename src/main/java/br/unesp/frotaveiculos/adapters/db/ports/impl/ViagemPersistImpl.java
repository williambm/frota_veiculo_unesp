package br.unesp.frotaveiculos.adapters.db.ports.impl;

import br.unesp.frotaveiculos.adapters.db.model.Endereco;
import br.unesp.frotaveiculos.adapters.db.model.Funcionario;
import br.unesp.frotaveiculos.adapters.db.model.Veiculo;
import br.unesp.frotaveiculos.adapters.db.model.Viagem;
import br.unesp.frotaveiculos.adapters.db.model.enumerations.PerfilFuncionario;
import br.unesp.frotaveiculos.adapters.db.model.enumerations.StatusViagem;
import br.unesp.frotaveiculos.adapters.db.model.enumerations.Unidade;
import br.unesp.frotaveiculos.adapters.db.ports.ViagemPersist;
import br.unesp.frotaveiculos.adapters.db.repository.ViagemRepository;
import br.unesp.frotaveiculos.dto.ViagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViagemPersistImpl implements ViagemPersist {

    @Autowired
    private ViagemRepository viagemRepository;

    @Override
    public ViagemDTO salvar(ViagemDTO viagemDTO) {
        Viagem entidade = builderDTOemEntidadeToCreate(viagemDTO);
        entidade = viagemRepository.save(entidade);
        return builderEntitytoDTO(entidade);
    }

    private static Viagem builderDTOemEntidadeToCreate(ViagemDTO viagemDTO) {

        return Viagem.builder()
                .campusOrigem(Unidade.valueOf(viagemDTO.getCampusOrigem()))
                .status(StatusViagem.SOLICITADA)
                .dataViagem(viagemDTO.getDataViagem())
                .enderecoDestino(
                        Endereco.builder()
                                .cep(viagemDTO.getCep())
                                .bairro(viagemDTO.getBairro())
                                .cidade(viagemDTO.getCidade())
                                .complemento(viagemDTO.getComplemento())
                                .estado(viagemDTO.getEstado())
                                .logradouro(viagemDTO.getLogradouro())
                                .numero(viagemDTO.getNumero())
                                .build()
                )
                .veiculo(
                        Veiculo.builder()
                                .id(viagemDTO.getVeiculoId())
                                .build()
                )
                .solicitante(
                        Funcionario.builder()
                                .matricula(viagemDTO.getSolicitanteId())
                                .perfilFuncionario(PerfilFuncionario.valueOf("PASSAGEIRO"))
                                .build()
                )
                .build();
    }

    private static ViagemDTO builderEntitytoDTO(Viagem viagem) {
        ViagemDTO.ViagemDTOBuilder builder = ViagemDTO.builder()
                .id(viagem.getId())
                .solicitanteId(viagem.getSolicitante().getMatricula())
                .veiculoId(viagem.getVeiculo().getId())
                .cep(viagem.getEnderecoDestino().getCep())
                .logradouro(viagem.getEnderecoDestino().getLogradouro())
                .numero(viagem.getEnderecoDestino().getNumero())
                .complemento(viagem.getEnderecoDestino().getComplemento())
                .bairro(viagem.getEnderecoDestino().getBairro())
                .cidade(viagem.getEnderecoDestino().getCidade())
                .estado(viagem.getEnderecoDestino().getEstado())
                .campusOrigem(viagem.getCampusOrigem().toString())
                .dataViagem(viagem.getDataViagem());

        if (viagem.getMotorista() != null && viagem.getMotorista().getMatricula() != null) {
            builder.motoristaId(viagem.getMotorista().getMatricula());
        }

        return builder.build();
    }
}
