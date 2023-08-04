package br.unesp.frotaveiculos.adapters.controller.handler;

import br.unesp.frotaveiculos.adapters.db.exceptions.FuncionarioDBInexistenteException;
import br.unesp.frotaveiculos.adapters.db.exceptions.PerfilInvalidoDBException;
import br.unesp.frotaveiculos.dto.ErroPadraoDTO;
import br.unesp.frotaveiculos.usecase.funcionario.exceptions.FuncionarioJaCadastradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(FuncionarioJaCadastradoException.class)
    public ResponseEntity<ErroPadraoDTO> funcionarioJaCadastrado(FuncionarioJaCadastradoException ex) {
        HttpStatus status = HttpStatus.CONFLICT;

        ErroPadraoDTO dto = ErroPadraoDTO.builder()
                .dataHoraErro(LocalDateTime.now())
                .status(status.value())
                .error("Funcionário já cadastrado em nosso Sistema")
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(status).body(dto);
    }

    @ExceptionHandler(FuncionarioDBInexistenteException.class)
    public ResponseEntity<ErroPadraoDTO> funcionarioNaoEncontradoPorId(FuncionarioDBInexistenteException ex) {
        HttpStatus status = HttpStatus.NO_CONTENT;

        return ResponseEntity.status(status).build();
    }

    @ExceptionHandler(PerfilInvalidoDBException.class)
    public ResponseEntity<ErroPadraoDTO> funcionarioPerfilInvalido(PerfilInvalidoDBException ex) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ErroPadraoDTO dto = ErroPadraoDTO.builder()
                .dataHoraErro(LocalDateTime.now())
                .status(status.value())
                .error("Atribuição inválida de Perfil")
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(status).body(dto);
    }
}
