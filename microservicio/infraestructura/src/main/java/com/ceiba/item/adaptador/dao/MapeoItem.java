package com.ceiba.item.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.item.modelo.dto.DtoItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoItem implements RowMapper<DtoItem>, MapperResult {

    @Override
    public DtoItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String referencia = resultSet.getString("referencia");
        String nombre = resultSet.getString("nombre");
        Long cantidad = resultSet.getLong("cantidad");

        return new DtoItem(id, referencia, nombre, cantidad);
    }

}
