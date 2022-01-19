package com.ceiba.venta.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.venta.modelo.dto.DtoVenta;
import com.ceiba.venta.puerto.dao.DaoVenta;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class DaoVentaMysql implements DaoVenta {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "venta", value = "listar")
    private static String sqlListar;

    @SqlStatement(namespace = "venta", value = "obtenerPorId")
    private static String sqlObtenerPorId;

    public DaoVentaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoVenta> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoVentaResultSetExtractor());
    }

    @Override
    public DtoVenta obtenerPorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return Objects.requireNonNull(this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlObtenerPorId, paramSource, new MapeoVentaResultSetExtractor())).get(0);
    }
}
