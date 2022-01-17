package com.ceiba.repartidor.servicio.testdatabuilder;

import com.ceiba.repartidor.modelo.dto.DtoRepartidor;
import com.ceiba.repartidor.modelo.entidad.Repartidor;

public class DtoRepartidorTestDataBuilder {

    private Long id;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String telefono;

    public DtoRepartidorTestDataBuilder() {
        id = 2L;
        identificacion = "identificacionrepartidor";
        nombres = "nombresrepartidor";
        apellidos = "apellidosrepartidor";
        telefono = "telefonorepartidor";
    }

    public DtoRepartidorTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoRepartidorTestDataBuilder conIdentificacion(String identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public DtoRepartidorTestDataBuilder conNombres(String nombres) {
        this.nombres = nombres;
        return this;
    }

    public DtoRepartidorTestDataBuilder conApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public DtoRepartidorTestDataBuilder conTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public DtoRepartidor build() {
        return new DtoRepartidor(id, identificacion, nombres, apellidos, telefono);
    }
}
