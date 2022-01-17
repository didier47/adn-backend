package com.ceiba.repartidor.puerto.dao;

import com.ceiba.repartidor.modelo.dto.DtoRepartidor;

import java.util.List;

public interface DaoRepartidor {
    /**
     * Permite listar repartidores
     *
     * @return los repartidores
     */
    List<DtoRepartidor> listar();

    /**
     * Permite buscar un repartidor dado un id
     *
     * @return el repartidor
     */
    DtoRepartidor buscarPorId(Long id);

}
