package com.ceiba.venta.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioVentaMysql implements RepositorioVenta {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "venta", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "venta", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "venta", value = "eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace = "venta", value = "existe")
    private static String sqlExiste;

    @SqlStatement(namespace = "venta", value = "existePorId")
    private static String sqlExistePorId;

    public RepositorioVentaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Venta venta) {
        return this.customNamedParameterJdbcTemplate.crear(venta, sqlCrear);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        paramSource.addValue("idVenta", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public boolean existe(String referencia) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("referencia", referencia);

        return Boolean.TRUE.equals(
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExiste, paramSource, Boolean.class)
        );
    }

    @Override
    public void actualizar(Venta venta) {
        this.customNamedParameterJdbcTemplate.actualizar(venta, sqlActualizar);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return Boolean.TRUE.equals(
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExistePorId, paramSource, Boolean.class)
        );
    }

}
