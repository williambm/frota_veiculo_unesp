package br.unesp.frotaveiculos.usecase.funcionario;

import br.unesp.frotaveiculos.adapters.db.ports.FuncionarioPersist;
import br.unesp.frotaveiculos.dto.FuncionarioDTO;
import br.unesp.frotaveiculos.dto.FuncionarioDTOSemSenha;
import br.unesp.frotaveiculos.usecase.funcionario.exceptions.FuncionarioJaCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class FuncionarioUCImpl implements FuncionarioUC {

    @Autowired
    private FuncionarioPersist funcionarioPersist;

    /**
     * Regra de negócio - não deve permitir seguir jornada caso o usuário já exista em nossas bases.
     *
     * @param funcionarioDTO
     * @return
     */
    @Override
    public FuncionarioDTO cadastrarFuncionario(FuncionarioDTO funcionarioDTO) {

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
    public Page<FuncionarioDTOSemSenha> listarComPaginacao(Pageable pageable) {
        return funcionarioPersist.listarFuncionariosPaginado(pageable);
    }

    @Override
    public FuncionarioDTO buscarPorId(Long id) {
        return funcionarioPersist.buscarPorId(id);
    }

    @Override
    public FuncionarioDTO atualizar(Long id, FuncionarioDTO updateDTO) {
        return funcionarioPersist.atualizar(id, updateDTO);
    }

    @Override
    public void deletar(Long id) {
        funcionarioPersist.deletar(id);
    }
}
