package com.ceiba.item.servicio.testdatabuilder;

import com.ceiba.item.modelo.entidad.Item;

public class ItemTestDataBuilder {

    private Long id;
    private String referencia;
    private String nombre;
    private Long cantidad;

    public ItemTestDataBuilder() {
        referencia = "referenciaitem";
        nombre = "nombreitem";
        cantidad = 5L;
    }

    public ItemTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ItemTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ItemTestDataBuilder conReferencia(String referencia) {
        this.referencia = referencia;
        return this;
    }

    public ItemTestDataBuilder conCantidad(Long cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public Item build() {
        return new Item(id, referencia, nombre, cantidad);
    }
}
