package com.ceiba.repartidor.modelo.entidad;


import com.ceiba.repartidor.modelo.dto.DtoRepartidor;
import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Repartidor {

    private static final String SE_DEBE_INGRESAR_LA_IDENTIFICACION = "Se debe ingresar la identificacion del repartidor";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE = "Se debe ingresar el nombre del repartidor";
    private static final String SE_DEBE_INGRESAR_EL_APELLIDO = "Se debe ingresar el apellido del repartidor";
    private static final String SE_DEBE_INGRESAR_EL_TELEFONO = "Se debe ingresar el telefono del repartidor";

    private Long id;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String telefono;

    public Repartidor(Long id, String identificacion, String nombres, String apellidos, String telefono) {
        validarObligatorio(identificacion, SE_DEBE_INGRESAR_LA_IDENTIFICACION);
        validarObligatorio(nombres, SE_DEBE_INGRESAR_EL_NOMBRE);
        validarObligatorio(apellidos, SE_DEBE_INGRESAR_EL_APELLIDO);
        validarObligatorio(telefono, SE_DEBE_INGRESAR_EL_TELEFONO);

        this.id = id;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public Repartidor(DtoRepartidor dtoRepartidor) {
        this.id = dtoRepartidor.getId();
        this.identificacion = dtoRepartidor.getIdentificacion();
        this.nombres = dtoRepartidor.getNombres();
        this.apellidos = dtoRepartidor.getApellidos();
        this.telefono = dtoRepartidor.getTelefono();
    }

}
