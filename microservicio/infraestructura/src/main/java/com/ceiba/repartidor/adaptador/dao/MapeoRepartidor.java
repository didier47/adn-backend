package com.ceiba.repartidor.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.repartidor.modelo.dto.DtoRepartidor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoRepartidor implements RowMapper<DtoRepartidor>, MapperResult {

    @Override
    public DtoRepartidor mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String identificacion = resultSet.getString("identificacion");
        String nombres = resultSet.getString("nombres");
        String apellidos = resultSet.getString("apellidos");
        String telefono = resultSet.getString("telefono");

        return new DtoRepartidor(id, identificacion, nombres, apellidos, telefono);
    }

}
