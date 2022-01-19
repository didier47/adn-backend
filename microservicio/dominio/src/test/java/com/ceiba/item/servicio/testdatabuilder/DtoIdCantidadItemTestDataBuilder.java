package com.ceiba.item.servicio.testdatabuilder;

import com.ceiba.item.modelo.dto.DtoIdCantidadItem;
import com.ceiba.item.modelo.entidad.Item;

public class DtoIdCantidadItemTestDataBuilder {

    private Long id;
    private Long cantidad;

    public DtoIdCantidadItemTestDataBuilder() {
        cantidad = 5L;
    }

    public DtoIdCantidadItemTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoIdCantidadItemTestDataBuilder conCantidad(Long cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public DtoIdCantidadItem build() {
        return new DtoIdCantidadItem(id, cantidad);
    }

}
