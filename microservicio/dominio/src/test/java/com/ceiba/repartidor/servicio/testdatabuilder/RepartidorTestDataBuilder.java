package com.ceiba.repartidor.servicio.testdatabuilder;

import com.ceiba.repartidor.modelo.entidad.Repartidor;

public class RepartidorTestDataBuilder {

    private Long id;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String telefono;

    public RepartidorTestDataBuilder() {
        identificacion = "identificacionrepartidor";
        nombres = "nombresrepartidor";
        apellidos = "apellidosrepartidor";
        telefono = "telefonorepartidor";
    }

    public RepartidorTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public RepartidorTestDataBuilder conIdentificacion(String identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public RepartidorTestDataBuilder conNombres(String nombres) {
        this.nombres = nombres;
        return this;
    }

    public RepartidorTestDataBuilder conApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public RepartidorTestDataBuilder conTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public Repartidor build() {
        return new Repartidor(id, identificacion, nombres, apellidos, telefono);
    }
}
