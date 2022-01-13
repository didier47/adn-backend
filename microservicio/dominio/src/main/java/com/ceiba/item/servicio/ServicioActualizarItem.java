package com.ceiba.item.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.item.modelo.entidad.Item;
import com.ceiba.item.puerto.repositorio.RepositorioItem;

public class ServicioActualizarItem {

    private static final String EL_ITEM_NO_EXISTE_EN_EL_SISTEMA = "El item no existe en el sistema";

    private final RepositorioItem repositorioItem;

    public ServicioActualizarItem(RepositorioItem repositorioItem) {
        this.repositorioItem = repositorioItem;
    }

    public void ejecutar(Item item) {
        validarExistenciaPrevia(item);
        this.repositorioItem.actualizar(item);
    }

    private void validarExistenciaPrevia(Item item) {
        boolean existe = this.repositorioItem.existePorId(item.getId());
        if (!existe) {
            throw new ExcepcionDuplicidad(EL_ITEM_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
