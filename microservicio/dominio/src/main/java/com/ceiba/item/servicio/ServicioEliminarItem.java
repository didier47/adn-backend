package com.ceiba.item.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.item.puerto.repositorio.RepositorioItem;

public class ServicioEliminarItem {

    private static final String EL_ITEM_EXISTE_EN_UNA_VENTA = "El item está asociado a una venta";

    private final RepositorioItem repositorioItem;

    public ServicioEliminarItem(RepositorioItem repositorioItem) {
        this.repositorioItem = repositorioItem;
    }

    public void ejecutar(Long id) {
        validarExistenciaEnVenta(id);
        this.repositorioItem.eliminar(id);

    }

    private void validarExistenciaEnVenta(Long id) {
        boolean existe = this.repositorioItem.existeEnVenta(id);
        if (existe) {
            throw new ExcepcionValorInvalido(EL_ITEM_EXISTE_EN_UNA_VENTA);
        }
    }
}
