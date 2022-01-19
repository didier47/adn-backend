package com.ceiba.venta.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.item.modelo.entidad.Item;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class RepositorioVentaMysql implements RepositorioVenta {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private static final String CAMPO_ID = "id";
    private static final String CAMPO_ID_VENTA = "idVenta";
    private static final String CAMPO_ID_REPARTIDOR = "idRepartidor";
    private static final String CAMPO_REFERENCIA = "referencia";
    private static final String CAMPO_DISTANCIA = "distancia";
    private static final String CAMPO_FECHA_ENTREGA = "fechaEntrega";
    private static final String CAMPO_VALOR_ENVIO = "valorEnvio";
    private static final String CAMPO_ID_ITEM = "idItem";
    private static final String CAMPO_CANTIDAD = "cantidad";

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

    @SqlStatement(namespace = "ventaItems", value = "crear")
    private static String sqlCrearVentaItem;

    @SqlStatement(namespace = "ventaItems", value = "eliminar")
    private static String sqlEliminarVentaItem;

    public RepositorioVentaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Venta venta) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(CAMPO_ID_REPARTIDOR, venta.getRepartidor().getId());
        paramSource.addValue(CAMPO_REFERENCIA, venta.getReferencia());
        paramSource.addValue(CAMPO_DISTANCIA, venta.getDistancia());
        paramSource.addValue(CAMPO_FECHA_ENTREGA, venta.getFechaEntrega());
        paramSource.addValue(CAMPO_VALOR_ENVIO, venta.getValorEnvio());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlCrear, paramSource, keyHolder, new String[]{"id"});
        Long idVenta = Objects.requireNonNull(keyHolder.getKey()).longValue();

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().batchUpdate(
                sqlCrearVentaItem,
                convertirItemsAParametrosDeInsercionBatchVentaItems(venta.getItems(), idVenta)
        );
        return idVenta;
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(CAMPO_ID, id);
        paramSource.addValue(CAMPO_ID_VENTA, id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarVentaItem, paramSource);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public boolean existe(String referencia) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(CAMPO_REFERENCIA, referencia);

        return Boolean.TRUE.equals(
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExiste, paramSource, Boolean.class)
        );
    }

    @Override
    public void actualizar(Venta venta) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(CAMPO_ID, venta.getId());
        paramSource.addValue(CAMPO_ID_VENTA, venta.getId());
        paramSource.addValue(CAMPO_ID_REPARTIDOR, venta.getRepartidor().getId());
        paramSource.addValue(CAMPO_REFERENCIA, venta.getReferencia());
        paramSource.addValue(CAMPO_DISTANCIA, venta.getDistancia());
        paramSource.addValue(CAMPO_FECHA_ENTREGA, venta.getFechaEntrega());
        paramSource.addValue(CAMPO_VALOR_ENVIO, venta.getValorEnvio());

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizar, paramSource);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarVentaItem, paramSource);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().batchUpdate(sqlCrearVentaItem, convertirItemsAParametrosDeInsercionBatchVentaItems(venta.getItems(), venta.getId()));
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(CAMPO_ID, id);

        return Boolean.TRUE.equals(
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExistePorId, paramSource, Boolean.class)
        );
    }

    private MapSqlParameterSource[] convertirItemsAParametrosDeInsercionBatchVentaItems(List<Item> items, Long idVenta) {
        List<MapSqlParameterSource> itemsAInsertar = new ArrayList<>();
        MapSqlParameterSource paramSource;
        for (Item item : items) {
            paramSource = new MapSqlParameterSource();
            paramSource.addValue(CAMPO_ID_VENTA, idVenta);
            paramSource.addValue(CAMPO_ID_ITEM, item.getId());
            paramSource.addValue(CAMPO_CANTIDAD, item.getCantidad());
            itemsAInsertar.add(paramSource);
        }
        return itemsAInsertar.toArray(new MapSqlParameterSource[0]);
    }

}
