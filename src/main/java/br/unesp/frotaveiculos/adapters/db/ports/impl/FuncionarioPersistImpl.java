package br.unesp.frotaveiculos.adapters.db.ports.impl;

import br.unesp.frotaveiculos.adapters.db.exceptions.FuncionarioDBInexistenteException;
import br.unesp.frotaveiculos.adapters.db.model.Funcionario;
import br.unesp.frotaveiculos.adapters.db.ports.FuncionarioPersist;
import br.unesp.frotaveiculos.adapters.db.repository.FuncionarioRepository;
import br.unesp.frotaveiculos.adapters.dto.FuncionarioDTO;
import br.unesp.frotaveiculos.adapters.dto.FuncionarioUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    @Override
    public FuncionarioDTO cadastrarFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            Funcionario entity = converteDTOEmEntidade(funcionarioDTO);
            entity = repository.save(entity);
            funcionarioDTO = converteEntidadeEmDTO(entity);
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
        return listaDeFuncionarios.map(entidade -> converteEntidadeEmDTO(entidade));
    }

    @Override
    public FuncionarioDTO buscarPorId(Long id) {
        Funcionario entidade = repository.findById(id).orElseThrow(
                () -> new FuncionarioDBInexistenteException()
                //Logar que não foi encontrado
        );
        return converteEntidadeEmDTO(entidade);
    }

    @Override
    public FuncionarioDTO atualizar(Long id, FuncionarioUpdateDTO updateDTO) {
        try {
            Funcionario entidade = repository.findById(id).orElseThrow();
            //TODO: Trocar pelo modelMapper porque esse cara não ignora valores nulos
            BeanUtils.copyProperties(updateDTO,entidade);
            entidade = repository.save(entidade);
            return converteEntidadeEmDTO(entidade);
        }catch(NoSuchElementException ex){
            throw ex;
        }
    }

    //Todo: Se der Tempo colocar aqui um Conversor como o ModelMapper para abstrair os Builders
    private static FuncionarioDTO converteEntidadeEmDTO(Funcionario entity) {
        return FuncionarioDTO.builder()
                .matricula(entity.getMatricula())
                .nome(entity.getNome())
                .dataAdmissao(entity.getDataAdmissao())
                .dataNascimento(entity.getDataNascimento())
                .funcao(entity.getFuncao())
                .build();
    }

    private static Funcionario converteDTOEmEntidade(FuncionarioDTO funcionarioDTO) {
        return Funcionario.builder()
                .matricula(funcionarioDTO.getMatricula())
                .nome(funcionarioDTO.getNome())
                .dataAdmissao(funcionarioDTO.getDataAdmissao())
                .dataNascimento(funcionarioDTO.getDataNascimento())
                .funcao(funcionarioDTO.getFuncao())
                .build();
    }
}
