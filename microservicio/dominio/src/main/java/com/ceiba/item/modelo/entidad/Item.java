package com.ceiba.item.modelo.entidad;


import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarPositivo;

@Getter
public class Item {

    private static final String SE_DEBE_INGRESAR_LA_REFERENCIA = "Se debe ingresar la referencia del item";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE = "Se debe ingresar el nombre del item";
    private static final String SE_DEBE_INGRESAR_LA_CANTIDAD = "Se debe ingresar la cantidad del item";
    private static final String LA_CANTIDAD_DEBE_SER_POSITIVA = "La cantidad debe ser positiva";

    private Long id;
    private String referencia;
    private String nombre;
    private Long cantidad;

    public Item(Long id, String referencia, String nombre, Long cantidad) {

        validarObligatorio(referencia, SE_DEBE_INGRESAR_LA_REFERENCIA);
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE);
        validarObligatorio(cantidad, SE_DEBE_INGRESAR_LA_CANTIDAD);
        validarPositivo(cantidad, LA_CANTIDAD_DEBE_SER_POSITIVA);

        this.id = id;
        this.referencia = referencia;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

}
