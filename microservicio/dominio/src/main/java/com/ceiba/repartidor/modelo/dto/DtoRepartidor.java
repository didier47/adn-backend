package com.ceiba.repartidor.modelo.dto;

public class DtoRepartidor {

    private final Long id;
    private final String identificacion;
    private final String nombres;
    private final String apellidos;
    private final String telefono;

    public DtoRepartidor(Long id, String identificacion, String nombres, String apellidos, String telefono) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }
}
