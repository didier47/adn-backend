package com.ceiba.item.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.item.modelo.dto.DtoItem;
import com.ceiba.item.puerto.dao.DaoItem;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoItemMysql implements DaoItem {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "item", value = "listar")
    private static String sqlListar;

    @SqlStatement(namespace = "item", value = "listarPorIds")
    private static String sqlListarPorIds;

    public DaoItemMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoItem> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoItem());
    }

    @Override
    public List<DtoItem> listarPorIds(List<Long> ids) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("ids", ids);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarPorIds, paramSource, new MapeoItem());
    }
}
