package com.ceiba.item.puerto.repositorio;

import com.ceiba.item.modelo.entidad.Item;

import java.util.List;

public interface RepositorioItem {

    /**
     * Permite crear un item
     *
     * @param item
     * @return el id generado
     */
    Long crear(Item item);

    /**
     * Permite actualizar un item
     *
     * @param item
     */
    void actualizar(Item item);

    /**
     * Permite eliminar un item
     *
     * @param id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe un item con una referencia
     *
     * @param referencia
     * @return si existe o no
     */
    boolean existe(String referencia);

    /**
     * Permite validar si existe un item con una referencia excluyendo un id
     *
     * @return si existe o no
     */
    boolean existePorId(Long id);

    /**
     * Permite validar si existe un item en una venta
     *
     * @return si existe o no
     */
    boolean existeEnVenta(Long id);

    /**
     * Permite actualizar una cantidad de items
     *
     * @param items
     */
    void actualizarBatch(List<Item> items);

}
