package br.unesp.frotaveiculos.adapters.db.ports.impl;

import br.unesp.frotaveiculos.adapters.config.mapstruct.MapperFuncionario;
import br.unesp.frotaveiculos.adapters.db.exceptions.FuncionarioDBInexistenteException;
import br.unesp.frotaveiculos.adapters.db.exceptions.PerfilInvalidoDBException;
import br.unesp.frotaveiculos.adapters.db.model.Funcionario;
import br.unesp.frotaveiculos.adapters.db.model.enumerations.PerfilFuncionario;
import br.unesp.frotaveiculos.adapters.db.ports.FuncionarioPersist;
import br.unesp.frotaveiculos.adapters.db.repository.FuncionarioRepository;
import br.unesp.frotaveiculos.dto.FuncionarioDTORequest;
import br.unesp.frotaveiculos.dto.FuncionarioDTOResponse;
import br.unesp.frotaveiculos.dto.FuncionarioUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FuncionarioPersistImpl implements FuncionarioPersist {

    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private MapperFuncionario mapperFuncionario;

    @Override
    public FuncionarioDTORequest cadastrarFuncionario(FuncionarioDTORequest funcionarioDTORequest) {
        try {
            Funcionario entity = converteDTOEmEntidade(funcionarioDTORequest);
            String senhaHash = new BCryptPasswordEncoder().encode(funcionarioDTORequest.getSenha());
            System.out.println(senhaHash);
            entity.setSenha(senhaHash);
            //Encode de senha em BCRYPT
            //entity.setSenha(new BCryptPasswordEncoder().encode(entity.getSenha()));
            System.out.println(entity.getSenha());

            entity = repository.save(entity);
            System.out.println(entity);
            funcionarioDTORequest = mapperFuncionario.convertEntidadeEmDTO(entity);
        } catch (IllegalArgumentException ex) {
            throw new PerfilInvalidoDBException(MessageFormat.format(
                    "O perfil {0} não está cadastrado em nossas bases, o que invalida o processo de inserção deste registro.", funcionarioDTORequest.getPerfil()
            ));
        } catch (Exception erroGeneralizado) {
            throw erroGeneralizado;
        }
        return funcionarioDTORequest;
    }

    @Override
    public Boolean isFuncionarioCadastrado(FuncionarioDTORequest funcionarioDTORequest) {
        Optional<Funcionario> optionalFuncionario = repository.findById(funcionarioDTORequest.getMatricula());
        if (optionalFuncionario.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<FuncionarioDTOResponse> listarFuncionariosPaginado(Pageable pageable) {
        Page<Funcionario> listaDeFuncionarios = repository.findAll(pageable);
//        return listaDeFuncionarios.map(entidade -> mapperFuncionario.convertEntidadeEmDTO(entidade));
        return listaDeFuncionarios.map(entidade -> mapperFuncionario.convertEntidadeEmResponseDTO(entidade));
    }

    @Override
    public FuncionarioDTORequest buscarPorId(Long id) {
        Funcionario entidade = repository.findById(id).orElseThrow(FuncionarioDBInexistenteException::new);
        return mapperFuncionario.convertEntidadeEmDTO(entidade);
    }

    @Override
    public FuncionarioDTORequest atualizar(Long id, FuncionarioUpdateDTO updateDTO) {
        try {
            Funcionario entidade = repository.findById(id).orElseThrow();

            entidade = mapperFuncionario.convertUpdateDtoEmEntidade(entidade, updateDTO);
            entidade = repository.save(entidade);

            return mapperFuncionario.convertEntidadeEmDTO(entidade);
        } catch (NoSuchElementException ex) {
            throw new FuncionarioDBInexistenteException();
        } catch (IllegalArgumentException ex) {
            throw new PerfilInvalidoDBException(MessageFormat.format(
                    "O perfil {0} não está cadastrado em nossas bases, o que invalida o processo de atualização deste registro.", updateDTO.getPerfil()
            ));
        }
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    //TODO: agora o projeto já tem o MapStruct - depois passar este Builder para Lá.
    private static Funcionario converteDTOEmEntidade(FuncionarioDTORequest funcionarioDTORequest) {
        return Funcionario.builder()
                .matricula(funcionarioDTORequest.getMatricula())
                .nome(funcionarioDTORequest.getNome())
                .email(funcionarioDTORequest.getEmail())
                .senha(funcionarioDTORequest.getSenha())
                .dataAdmissao(funcionarioDTORequest.getDataAdmissao())
                .dataNascimento(funcionarioDTORequest.getDataNascimento())
                .funcao(funcionarioDTORequest.getFuncao())
                .perfilFuncionario(PerfilFuncionario.valueOf(funcionarioDTORequest.getPerfil()))
                .build();
    }

}
