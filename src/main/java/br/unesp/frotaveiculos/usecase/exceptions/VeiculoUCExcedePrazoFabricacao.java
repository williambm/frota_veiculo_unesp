package br.unesp.frotaveiculos.usecase.exceptions;

public class VeiculoUCExcedePrazoFabricacao extends RuntimeException {
    public VeiculoUCExcedePrazoFabricacao(String msg) {
        super(msg);
    }
}
