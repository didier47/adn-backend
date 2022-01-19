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
    private static String sqlCrear;

    @SqlStatement(namespace = "item", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "item", value = "eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace = "item", value = "existe")
    private static String sqlExiste;

    @SqlStatement(namespace = "item", value = "existePorId")
    private static String sqlExistePorId;

    @SqlStatement(namespace = "ventaItems", value = "existeEnVenta")
    private static String sqlExisteEnVenta;

    public RepositorioItemMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Item item) {
        return this.customNamedParameterJdbcTemplate.crear(item, sqlCrear);
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
        paramSource.addValue("referencia", referencia);

        return Boolean.TRUE.equals(
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExiste, paramSource, Boolean.class)
        );
    }

    @Override
    public void actualizar(Item item) {
        this.customNamedParameterJdbcTemplate.actualizar(item, sqlActualizar);
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
        paramSource.addValue("idItem", id);

        return Boolean.TRUE.equals(
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExisteEnVenta, paramSource, Boolean.class)
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
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().batchUpdate(sqlActualizar, itemsAActualizar.toArray(new MapSqlParameterSource[0]));
    }

}
