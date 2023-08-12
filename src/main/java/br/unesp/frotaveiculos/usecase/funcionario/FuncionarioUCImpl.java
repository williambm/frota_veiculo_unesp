package br.unesp.frotaveiculos.usecase.funcionario;

import br.unesp.frotaveiculos.adapters.db.ports.FuncionarioPersist;
import br.unesp.frotaveiculos.dto.FuncionarioDTORequest;
import br.unesp.frotaveiculos.dto.FuncionarioDTOResponse;
import br.unesp.frotaveiculos.dto.FuncionarioUpdateDTO;
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
     * @param funcionarioDTORequest
     * @return
     */
    @Override
    public FuncionarioDTORequest cadastrarFuncionario(FuncionarioDTORequest funcionarioDTORequest) {

        if (funcionarioPersist.isFuncionarioCadastrado(funcionarioDTORequest)) {
            throw new FuncionarioJaCadastradoException(
                    MessageFormat.format(
                            "O Funcionário {0} de matrícula {1} já está cadastrado.",
                            funcionarioDTORequest.getNome(),
                            funcionarioDTORequest.getMatricula()
                    )
            );
        }
        return funcionarioPersist.cadastrarFuncionario(funcionarioDTORequest);
    }

    @Override
    public Page<FuncionarioDTOResponse> listarComPaginacao(Pageable pageable) {
        return funcionarioPersist.listarFuncionariosPaginado(pageable);
    }

    @Override
    public FuncionarioDTORequest buscarPorId(Long id) {
        return funcionarioPersist.buscarPorId(id);
    }

    @Override
    public FuncionarioDTORequest atualizar(Long id, FuncionarioUpdateDTO updateDTO) {
        return funcionarioPersist.atualizar(id, updateDTO);
    }

    @Override
    public void deletar(Long id) {
        funcionarioPersist.deletar(id);
    }
}
