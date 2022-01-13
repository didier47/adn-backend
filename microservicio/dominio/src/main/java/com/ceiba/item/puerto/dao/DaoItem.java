package com.ceiba.item.puerto.dao;

import com.ceiba.item.modelo.dto.DtoItem;

import java.util.List;

public interface DaoItem {
    /**
     * Permite listar items
     *
     * @return los items
     */
    List<DtoItem> listar();

}
