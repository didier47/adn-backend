package com.ceiba.item.consulta;

import com.ceiba.item.modelo.dto.DtoItem;
import com.ceiba.item.puerto.dao.DaoItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarItems {

    private final DaoItem daoItem;

    public ManejadorListarItems(DaoItem daoItem) {
        this.daoItem = daoItem;
    }

    public List<DtoItem> ejecutar() {
        return this.daoItem.listar();
    }
}
