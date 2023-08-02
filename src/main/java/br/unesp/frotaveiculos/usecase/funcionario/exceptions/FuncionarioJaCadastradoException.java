package br.unesp.frotaveiculos.usecase.funcionario.exceptions;

public class FuncionarioJaCadastradoException extends RuntimeException {
    public FuncionarioJaCadastradoException(String msg) {
        super(msg);
    }
}
