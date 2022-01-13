package com.ceiba.item.modelo.dto;

public class DtoItem {

    private final Long id;
    private final String referencia;
    private final String nombre;
    private final Long cantidad;

    public DtoItem(Long id, String referencia, String nombre, Long cantidad) {
        this.id = id;
        this.referencia = referencia;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getCantidad() {
        return cantidad;
    }
}
