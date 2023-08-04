package br.unesp.frotaveiculos.usecase.funcionario;

import br.unesp.frotaveiculos.adapters.db.ports.FuncionarioPersist;
import br.unesp.frotaveiculos.dto.FuncionarioDTO;
import br.unesp.frotaveiculos.dto.FuncionarioUpdateDTO;
import br.unesp.frotaveiculos.usecase.funcionario.exceptions.FuncionarioJaCadastradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class FuncionarioUCImpl implements FuncionarioUC {

    @Autowired
    private FuncionarioPersist funcionarioPersist;

    @Override
    public FuncionarioDTO cadastrarFuncionario(FuncionarioDTO funcionarioDTO) {

        //todo: Não deve cadastrar se o recurso já for existente na base de dados, informando uma msg para a API e FRONT
        if (funcionarioPersist.isFuncionarioCadastrado(funcionarioDTO)) {
            throw new FuncionarioJaCadastradoException(
                    MessageFormat.format(
                            "O Funcionário {0} de matrícula {1} já está cadastrado.",
                            funcionarioDTO.getNome(),
                            funcionarioDTO.getMatricula()
                    )
            );
        }
        return funcionarioPersist.cadastrarFuncionario(funcionarioDTO);
    }

    @Override
    public Page<FuncionarioDTO> listarComPaginacao(Pageable pageable) {
        return funcionarioPersist.listarFuncionariosPaginado(pageable);
    }

    @Override
    public FuncionarioDTO buscarPorId(Long id) {
        return funcionarioPersist.buscarPorId(id);
    }

    @Override
    public FuncionarioDTO atualizar(Long id, FuncionarioUpdateDTO updateDTO) {
        return funcionarioPersist.atualizar(id, updateDTO);
    }
}
