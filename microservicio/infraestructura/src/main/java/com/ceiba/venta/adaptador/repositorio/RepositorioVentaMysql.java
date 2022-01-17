package com.ceiba.venta.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;

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
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idRepartidor", venta.getRepartidor().getId());
        paramSource.addValue("referencia", venta.getReferencia());
        paramSource.addValue("distancia", venta.getDistancia());
        paramSource.addValue("fechaEntrega", venta.getFechaEntrega());
        paramSource.addValue("valorEnvio", venta.getValorEnvio());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlCrear, paramSource, keyHolder, new String[]{"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
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
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", venta.getId());
        paramSource.addValue("idRepartidor", venta.getRepartidor().getId());
        paramSource.addValue("referencia", venta.getReferencia());
        paramSource.addValue("distancia", venta.getDistancia());
        paramSource.addValue("fechaEntrega", venta.getFechaEntrega());
        paramSource.addValue("valorEnvio", venta.getValorEnvio());

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizar, paramSource);
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
