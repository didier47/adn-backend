package com.ceiba.item.servicio.testdatabuilder;

import com.ceiba.item.modelo.dto.DtoItem;

public class DtoItemTestDataBuilder {

    private Long id;
    private String referencia;
    private String nombre;
    private Long cantidad;

    public DtoItemTestDataBuilder() {
        referencia = "referenciaitem";
        nombre = "nombreitem";
        cantidad = 5L;
    }

    public DtoItemTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoItemTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public DtoItemTestDataBuilder conReferencia(String referencia) {
        this.referencia = referencia;
        return this;
    }

    public DtoItemTestDataBuilder conCantidad(Long cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public DtoItem build() {
        return new DtoItem(id, referencia, nombre, cantidad);
    }
}
