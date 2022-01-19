package com.ceiba.item.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.item.modelo.entidad.Item;
import com.ceiba.item.puerto.repositorio.RepositorioItem;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioItemMysql implements RepositorioItem {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "item", value = "crear")
    private static String sqlCrearItem;

    @SqlStatement(namespace = "item", value = "actualizar")
    private static String sqlActualizarItem;

    @SqlStatement(namespace = "item", value = "eliminar")
    private static String sqlEliminarItem;

    @SqlStatement(namespace = "item", value = "existe")
    private static String sqlExisteItem;

    @SqlStatement(namespace = "item", value = "existePorId")
    private static String sqlExisteItemPorId;

    @SqlStatement(namespace = "ventaItems", value = "existeEnVenta")
    private static String sqlExisteItemEnVenta;

    public RepositorioItemMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Item item) {
        return this.customNamedParameterJdbcTemplate.crear(item, sqlCrearItem);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarItem, paramSource);
    }

    @Override
    public boolean existe(String referencia) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("referencia", referencia);

        return Boolean.TRUE.equals(
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExisteItem, paramSource, Boolean.class)
        );
    }

    @Override
    public void actualizar(Item item) {
        this.customNamedParameterJdbcTemplate.actualizar(item, sqlActualizarItem);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return Boolean.TRUE.equals(
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExisteItemPorId, paramSource, Boolean.class)
        );
    }

    @Override
    public boolean existeEnVenta(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idItem", id);

        return Boolean.TRUE.equals(
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExisteItemEnVenta, paramSource, Boolean.class)
        );
    }

    @Override
    public void actualizarBatch(List<Item> items) {
        List<MapSqlParameterSource> itemsAActualizar = new ArrayList<>();
        MapSqlParameterSource paramSource;
        for (Item item : items) {
            paramSource = new MapSqlParameterSource();
            paramSource.addValue("id", item.getId());
            paramSource.addValue("referencia", item.getReferencia());
            paramSource.addValue("nombre", item.getNombre());
            paramSource.addValue("cantidad", item.getCantidad());
            itemsAActualizar.add(paramSource);
        }
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().batchUpdate(sqlActualizarItem, itemsAActualizar.toArray(new MapSqlParameterSource[0]));
    }

}
