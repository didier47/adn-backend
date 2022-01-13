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
    private static String sqlCrear;

    @SqlStatement(namespace = "repartidor", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "repartidor", value = "eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace = "repartidor", value = "existe")
    private static String sqlExiste;

    @SqlStatement(namespace = "repartidor", value = "existePorId")
    private static String sqlExistePorId;

    @SqlStatement(namespace = "venta", value = "existeEnVenta")
    private static String sqlExisteEnVenta;

    public RepositorioRepartidorMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Repartidor repartidor) {
        return this.customNamedParameterJdbcTemplate.crear(repartidor, sqlCrear);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public boolean existe(String referencia) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("identificacion", referencia);

        return Boolean.TRUE.equals(
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExiste, paramSource, Boolean.class)
        );
    }

    @Override
    public void actualizar(Repartidor repartidor) {
        this.customNamedParameterJdbcTemplate.actualizar(repartidor, sqlActualizar);
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

    @Override
    public boolean existeEnVenta(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idRepartidor", id);

        return Boolean.TRUE.equals(
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExisteEnVenta, paramSource, Boolean.class)
        );
    }
}
