package br.unesp.frotaveiculos.adapters.controller.handler;

import br.unesp.frotaveiculos.adapters.db.exceptions.FabricanteInvalidoDBException;
import br.unesp.frotaveiculos.adapters.db.exceptions.FuncionarioDBInexistenteException;
import br.unesp.frotaveiculos.adapters.db.exceptions.PerfilInvalidoDBException;
import br.unesp.frotaveiculos.adapters.db.exceptions.VeiculoDBInexistenteException;
import br.unesp.frotaveiculos.dto.ErroPadraoDTO;
import br.unesp.frotaveiculos.usecase.exceptions.VeiculoUCExcedePrazoFabricacao;
import br.unesp.frotaveiculos.usecase.exceptions.ViagemUCExedePrazoSolicitacao;
import br.unesp.frotaveiculos.usecase.funcionario.exceptions.FuncionarioJaCadastradoException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
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
                .error("Erro de Negócio - Funcionário")
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

    @ExceptionHandler(VeiculoUCExcedePrazoFabricacao.class)
    public ResponseEntity<ErroPadraoDTO> veiculoExcedePrazoFabricacao(VeiculoUCExcedePrazoFabricacao ex) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ErroPadraoDTO dto = ErroPadraoDTO.builder()
                .dataHoraErro(LocalDateTime.now())
                .status(status.value())
                .error("Erro de Negócio - Veículos")
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(status).body(dto);
    }

    @ExceptionHandler(VeiculoDBInexistenteException.class)
    public ResponseEntity<ErroPadraoDTO> veiculoNaoEncontradoPorId(VeiculoDBInexistenteException ex) {
        HttpStatus status = HttpStatus.NO_CONTENT;

        return ResponseEntity.status(status).build();
    }

    @ExceptionHandler(FabricanteInvalidoDBException.class)
    public ResponseEntity<ErroPadraoDTO> veiculoModeloInvalido(FabricanteInvalidoDBException ex) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ErroPadraoDTO dto = ErroPadraoDTO.builder()
                .dataHoraErro(LocalDateTime.now())
                .status(status.value())
                .error("Atribuição inválida de Modelo de Veículo")
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(status).body(dto);
    }


    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<ErroPadraoDTO> tokenInvalido(SignatureVerificationException ex) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        ErroPadraoDTO dto = ErroPadraoDTO.builder()
                .dataHoraErro(LocalDateTime.now())
                .status(status.value())
                .error("Token Inválido")
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(status).body(dto);
    }

    @ExceptionHandler(ViagemUCExedePrazoSolicitacao.class)
    public ResponseEntity<ErroPadraoDTO> viagemExcedePrazoSolicitacao(ViagemUCExedePrazoSolicitacao ex) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ErroPadraoDTO dto = ErroPadraoDTO.builder()
                .dataHoraErro(LocalDateTime.now())
                .status(status.value())
                .error("Erro de Negócio - Viagem")
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(status).body(dto);
    }
}
