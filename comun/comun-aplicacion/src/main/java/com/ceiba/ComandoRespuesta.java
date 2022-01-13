package com.ceiba;

public class ComandoRespuesta<T> {

    private final T valor;

    public ComandoRespuesta(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }
}
