package br.unesp.frotaveiculos.adapters.db.ports.impl;

import br.unesp.frotaveiculos.adapters.db.exceptions.MotoristaDBNaoLocalizadoException;
import br.unesp.frotaveiculos.adapters.db.exceptions.ViagemDBNaoLocalizadaException;
import br.unesp.frotaveiculos.adapters.db.model.Endereco;
import br.unesp.frotaveiculos.adapters.db.model.Funcionario;
import br.unesp.frotaveiculos.adapters.db.model.Veiculo;
import br.unesp.frotaveiculos.adapters.db.model.Viagem;
import br.unesp.frotaveiculos.adapters.db.model.enumerations.PerfilFuncionario;
import br.unesp.frotaveiculos.adapters.db.model.enumerations.StatusViagem;
import br.unesp.frotaveiculos.adapters.db.model.enumerations.Unidade;
import br.unesp.frotaveiculos.adapters.db.ports.ViagemPersist;
import br.unesp.frotaveiculos.adapters.db.repository.FuncionarioRepository;
import br.unesp.frotaveiculos.adapters.db.repository.ViagemRepository;
import br.unesp.frotaveiculos.dto.MotoristaAtribuicaoDTO;
import br.unesp.frotaveiculos.dto.ViagemDTO;
import br.unesp.frotaveiculos.dto.ViagemMaisInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class ViagemPersistImpl implements ViagemPersist {

    @Autowired
    private ViagemRepository viagemRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public ViagemDTO salvar(ViagemDTO viagemDTO) {
        Viagem entidade = builderDTOemEntidadeToCreate(viagemDTO);
        entidade = viagemRepository.save(entidade);
        return builderEntitytoDTO(entidade);
    }

    @Override
    public Page<ViagemMaisInfoDTO> listarViagensPorFuncionario(Long matricula, Pageable pageable) {
        Page<Viagem> viagens = viagemRepository.findBySolicitante_Matricula(matricula, pageable);

        Page<ViagemMaisInfoDTO> viagensDTO = viagens.map(viagem -> builderEntitytoMaisInfoDTO(viagem));

        return viagensDTO;
    }

    @Override
    public Page<ViagemMaisInfoDTO> listarViagensPaginado(Pageable pageable) {
        Page<Viagem> viagens = viagemRepository.findAll(pageable);
        return viagens.map(viagem -> builderEntitytoMaisInfoDTO(viagem));
    }

    @Override
    public void atribuirMotorista(Long id, MotoristaAtribuicaoDTO dto) {
        Viagem viagemEntidade = viagemRepository.findById(id).orElseThrow(
                () -> new ViagemDBNaoLocalizadaException(
                        MessageFormat.format("Não foi possível localizar a Viagem de id:{0}", id)
                )
        );

        Funcionario motoristaEntidade = funcionarioRepository.findById(dto.getMotoristaId()).orElseThrow(
                () -> new MotoristaDBNaoLocalizadoException(
                        MessageFormat.format("Não foi possível localizar o Motorista de matrícula:{0}", dto.getMotoristaId())
                )
        );

        viagemEntidade.setMotorista(
                Funcionario.builder()
                        .matricula(motoristaEntidade.getMatricula())
                        .perfilFuncionario(PerfilFuncionario.MOTORISTA)
                        .build()
        );
        viagemEntidade.setStatus(StatusViagem.CONFIRMADA);

        viagemRepository.save(viagemEntidade);

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
                .passageirosObservacoes(viagemDTO.getPassageirosObservacoes())
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

    private ViagemMaisInfoDTO builderEntitytoMaisInfoDTO(Viagem viagem) {
        return ViagemMaisInfoDTO.builder()
                .id(viagem.getId())
                .solicitanteId(viagem.getSolicitante().getMatricula())
                .solicitanteNome(viagem.getSolicitante().getNome())
                .motoristaId(viagem.getMotorista() != null ? viagem.getMotorista().getMatricula() : null)
                .motoristaNome(viagem.getMotorista() != null ? viagem.getMotorista().getNome() : null)
                .veiculoId(viagem.getVeiculo().getId())
                .cep(viagem.getEnderecoDestino().getCep())
                .logradouro(viagem.getEnderecoDestino().getLogradouro())
                .numero(viagem.getEnderecoDestino().getNumero())
                .complemento(viagem.getEnderecoDestino().getComplemento())
                .bairro(viagem.getEnderecoDestino().getBairro())
                .cidade(viagem.getEnderecoDestino().getCidade())
                .estado(viagem.getEnderecoDestino().getEstado())
                .campusOrigem(viagem.getCampusOrigem().toString())
                .statusViagem(viagem.getStatus().toString())
                .veiculoModelo(viagem.getVeiculo().getModelo())
                .dataViagem(viagem.getDataViagem())
                .dataViagem(viagem.getDataViagem())
                .build();
    }
}
