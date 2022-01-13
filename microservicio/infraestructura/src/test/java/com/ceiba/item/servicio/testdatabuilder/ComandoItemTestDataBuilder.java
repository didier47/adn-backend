package com.ceiba.item.servicio.testdatabuilder;

import com.ceiba.item.comando.ComandoItem;

import java.util.UUID;

public class ComandoItemTestDataBuilder {

    private Long id;
    private String referencia;
    private String nombre;
    private Long cantidad;

    public ComandoItemTestDataBuilder() {
        referencia = UUID.randomUUID().toString();
        nombre = "nombreitem";
        cantidad = 5L;
    }

    public ComandoItemTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ComandoItemTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ComandoItemTestDataBuilder conReferencia(String referencia) {
        this.referencia = referencia;
        return this;
    }

    public ComandoItemTestDataBuilder conCantidad(Long cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public ComandoItem build() {
        return new ComandoItem(id, referencia, nombre, cantidad);
    }
}
