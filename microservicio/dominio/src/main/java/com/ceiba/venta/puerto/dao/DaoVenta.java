package com.ceiba.venta.puerto.dao;

import com.ceiba.venta.modelo.dto.DtoVenta;

import java.util.List;

public interface DaoVenta {
    /**
     * Permite listar ventas
     *
     * @return los ventas
     */
    List<DtoVenta> listar();

}
