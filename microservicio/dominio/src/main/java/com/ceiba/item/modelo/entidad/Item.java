package com.ceiba.item.modelo.entidad;


import com.ceiba.item.modelo.dto.DtoItem;
import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarPositivoOIgualACero;

@Getter
public class Item {

    private static final String SE_DEBE_INGRESAR_LA_REFERENCIA = "Se debe ingresar la referencia del item";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE = "Se debe ingresar el nombre del item";
    private static final String SE_DEBE_INGRESAR_LA_CANTIDAD = "Se debe ingresar la cantidad del item";
    private static final String LA_CANTIDAD_DEBE_SER_POSITIVA = "El inventario del item debe ser positivo o igual a cero";

    private Long id;
    private String referencia;
    private String nombre;
    private Long cantidad;

    public Item(Long id, String referencia, String nombre, Long cantidad) {

        validarObligatorio(referencia, SE_DEBE_INGRESAR_LA_REFERENCIA);
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE);
        validarObligatorio(cantidad, SE_DEBE_INGRESAR_LA_CANTIDAD);
        validarPositivoOIgualACero(cantidad, LA_CANTIDAD_DEBE_SER_POSITIVA);

        this.id = id;
        this.referencia = referencia;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Item(DtoItem dtoItem) {
        this.id = dtoItem.getId();
        this.referencia = dtoItem.getReferencia();
        this.nombre = dtoItem.getNombre();
        this.cantidad = dtoItem.getCantidad();
    }

}
