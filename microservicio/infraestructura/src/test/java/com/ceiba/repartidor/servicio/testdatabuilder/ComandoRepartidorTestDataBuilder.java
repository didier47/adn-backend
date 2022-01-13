package com.ceiba.repartidor.servicio.testdatabuilder;

import com.ceiba.repartidor.comando.ComandoRepartidor;

import java.util.UUID;

public class ComandoRepartidorTestDataBuilder {

    private Long id;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String telefono;

    public ComandoRepartidorTestDataBuilder() {
        identificacion = UUID.randomUUID().toString();
        nombres = "nombresrepartidor";
        apellidos = "apellidosrepartidor";
        telefono = "telefonorepartidor";
    }

    public ComandoRepartidorTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ComandoRepartidorTestDataBuilder conIdentificacion(String identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public ComandoRepartidorTestDataBuilder conNombres(String nombres) {
        this.nombres = nombres;
        return this;
    }

    public ComandoRepartidorTestDataBuilder conApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public ComandoRepartidorTestDataBuilder conTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public ComandoRepartidor build() {
        return new ComandoRepartidor(id, identificacion, nombres, apellidos, telefono);
    }
}
