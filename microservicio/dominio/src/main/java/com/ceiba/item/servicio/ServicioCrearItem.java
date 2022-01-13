package com.ceiba.item.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.item.modelo.entidad.Item;
import com.ceiba.item.puerto.repositorio.RepositorioItem;


public class ServicioCrearItem {

    private static final String EL_ITEM_YA_EXISTE_EN_EL_SISTEMA = "El item ya existe en el sistema";

    private final RepositorioItem repositorioItem;

    public ServicioCrearItem(RepositorioItem repositorioItem) {
        this.repositorioItem = repositorioItem;
    }

    public Long ejecutar(Item item) {
        validarExistenciaPrevia(item);
        return this.repositorioItem.crear(item);
    }

    private void validarExistenciaPrevia(Item item) {
        boolean existe = this.repositorioItem.existe(item.getReferencia());
        if (existe) {
            throw new ExcepcionDuplicidad(EL_ITEM_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
