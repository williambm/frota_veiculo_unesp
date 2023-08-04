package br.unesp.frotaveiculos.adapters.db.ports.impl;

import br.unesp.frotaveiculos.adapters.config.mapstruct.MapperFuncionario;
import br.unesp.frotaveiculos.adapters.db.exceptions.FuncionarioDBInexistenteException;
import br.unesp.frotaveiculos.adapters.db.model.Funcionario;
import br.unesp.frotaveiculos.adapters.db.model.enumerations.PerfilFuncionario;
import br.unesp.frotaveiculos.adapters.db.ports.FuncionarioPersist;
import br.unesp.frotaveiculos.adapters.db.repository.FuncionarioRepository;
import br.unesp.frotaveiculos.dto.FuncionarioDTO;
import br.unesp.frotaveiculos.dto.FuncionarioUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioPersistImpl implements FuncionarioPersist {

    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private MapperFuncionario mapperFuncionario;

    @Override
    public FuncionarioDTO cadastrarFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            Funcionario entity = converteDTOEmEntidade(funcionarioDTO);
            entity = repository.save(entity);
            funcionarioDTO = mapperFuncionario.convertEntidadeEmDTO(entity);
        } catch (Exception erro) {
            throw erro;
            //TODO: Antes de lançar exception Logar erro
        }
        return funcionarioDTO;
    }

    @Override
    public Boolean isFuncionarioCadastrado(FuncionarioDTO funcionarioDTO) {
        Optional<Funcionario> optionalFuncionario = repository.findById(funcionarioDTO.getMatricula());
        if (optionalFuncionario.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<FuncionarioDTO> listarFuncionariosPaginado(Pageable pageable) {
        Page<Funcionario> listaDeFuncionarios = repository.findAll(pageable);
        return listaDeFuncionarios.map(entidade -> mapperFuncionario.convertEntidadeEmDTO(entidade));
    }

    @Override
    public FuncionarioDTO buscarPorId(Long id) {
        Funcionario entidade = repository.findById(id).orElseThrow(
                () -> new FuncionarioDBInexistenteException()
                //Logar que não foi encontrado
        );
        return mapperFuncionario.convertEntidadeEmDTO(entidade);
    }

    @Override
    public FuncionarioDTO atualizar(Long id, FuncionarioUpdateDTO updateDTO) {
        try {
            Funcionario entidade = repository.findById(id).orElseThrow();

            entidade = mapperFuncionario.convertUpdateDtoEmEntidade(entidade, updateDTO);
            entidade = repository.save(entidade);

            return mapperFuncionario.convertEntidadeEmDTO(entidade);
        } catch (NoSuchElementException ex) {
            throw ex;
        }
    }

    //TODO: agora o projeto já tem o MapStruct - depois passar este Builder para Lá.
    private static Funcionario converteDTOEmEntidade(FuncionarioDTO funcionarioDTO) {
        return Funcionario.builder()
                .matricula(funcionarioDTO.getMatricula())
                .nome(funcionarioDTO.getNome())
                .dataAdmissao(funcionarioDTO.getDataAdmissao())
                .dataNascimento(funcionarioDTO.getDataNascimento())
                .funcao(funcionarioDTO.getFuncao())
                .perfilFuncionario(PerfilFuncionario.valueOf(funcionarioDTO.getPerfil()))
                .build();
    }

}
