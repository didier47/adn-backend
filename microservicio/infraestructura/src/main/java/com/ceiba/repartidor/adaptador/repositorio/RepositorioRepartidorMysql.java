package com.ceiba.repartidor.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.repartidor.modelo.entidad.Repartidor;
import com.ceiba.repartidor.puerto.repositorio.RepositorioRepartidor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioRepartidorMysql implements RepositorioRepartidor {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "repartidor", value = "crear")
    private static String sqlCrearRepartidor;

    @SqlStatement(namespace = "repartidor", value = "actualizar")
    private static String sqlActualizarRepartidor;

    @SqlStatement(namespace = "repartidor", value = "eliminar")
    private static String sqlEliminarRepartidor;

    @SqlStatement(namespace = "repartidor", value = "existe")
    private static String sqlExisteRepartidor;

    @SqlStatement(namespace = "repartidor", value = "existePorId")
    private static String sqlExisteRepartidorPorId;

    @SqlStatement(namespace = "venta", value = "existeEnVenta")
    private static String sqlExisteRepartidorEnVenta;

    public RepositorioRepartidorMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Repartidor repartidor) {
        return this.customNamedParameterJdbcTemplate.crear(repartidor, sqlCrearRepartidor);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarRepartidor, paramSource);
    }

    @Override
    public boolean existe(String referencia) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("identificacion", referencia);

        return Boolean.TRUE.equals(
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExisteRepartidor, paramSource, Boolean.class)
        );
    }

    @Override
    public void actualizar(Repartidor repartidor) {
        this.customNamedParameterJdbcTemplate.actualizar(repartidor, sqlActualizarRepartidor);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return Boolean.TRUE.equals(
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExisteRepartidorPorId, paramSource, Boolean.class)
        );
    }

    @Override
    public boolean existeEnVenta(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idRepartidor", id);

        return Boolean.TRUE.equals(
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExisteRepartidorEnVenta, paramSource, Boolean.class)
        );
    }
}
