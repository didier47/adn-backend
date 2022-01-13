package com.ceiba.repartidor.puerto.repositorio;

import com.ceiba.repartidor.modelo.entidad.Repartidor;

public interface RepositorioRepartidor {

    /**
     * Permite crear un repartidor
     *
     * @param repartidor
     * @return el id generado
     */
    Long crear(Repartidor repartidor);

    /**
     * Permite actualizar un repartidor
     *
     * @param repartidor
     */
    void actualizar(Repartidor repartidor);

    /**
     * Permite eliminar un repartidor
     *
     * @param id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe un repartidor con una identificacion
     *
     * @param referencia
     * @return si existe o no
     */
    boolean existe(String referencia);

    /**
     * Permite validar si existe un repartidor con una referencia excluyendo un id
     *
     * @return si existe o no
     */
    boolean existePorId(Long id);

    /**
     * Permite validar si existe un repartidor en una venta
     *
     * @return si existe o no
     */
    boolean existeEnVenta(Long id);

}
