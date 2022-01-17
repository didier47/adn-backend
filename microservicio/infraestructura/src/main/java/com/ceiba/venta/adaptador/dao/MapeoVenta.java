package com.ceiba.venta.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.repartidor.modelo.dto.DtoRepartidor;
import com.ceiba.venta.modelo.dto.DtoVenta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MapeoVenta implements RowMapper<DtoVenta>, MapperResult {

    @Override
    public DtoVenta mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        DtoRepartidor dtoRepartidor = new DtoRepartidor(
                resultSet.getLong("idRepartidor"),
                resultSet.getString("identificacion"),
                resultSet.getString("nombres"),
                resultSet.getString("apellidos"),
                resultSet.getString("telefono")
        );
        String referencia = resultSet.getString("referencia");
        Long distancia = resultSet.getLong("distancia");
        LocalDate fechaEntrega = extraerLocalDate(resultSet, "fechaEntrega");
        Double valorEnvio = resultSet.getDouble("valorEnvio");

        return new DtoVenta(id, dtoRepartidor, referencia, distancia, fechaEntrega, valorEnvio);
    }

}
