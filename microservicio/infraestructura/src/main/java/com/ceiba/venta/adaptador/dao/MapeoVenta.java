package com.ceiba.venta.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.venta.modelo.dto.DtoVenta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MapeoVenta implements RowMapper<DtoVenta>, MapperResult {

    @Override
    public DtoVenta mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        Long idRepartidor = resultSet.getLong("idRepartidor");
        String referencia = resultSet.getString("referencia");
        Long distancia = resultSet.getLong("distancia");
        LocalDate fechaEntrega = extraerLocalDate(resultSet, "fechaEntrega");
        Double valorEnvio = resultSet.getDouble("valorEnvio");

        return new DtoVenta(id, idRepartidor, referencia, distancia, fechaEntrega, valorEnvio);
    }

}
