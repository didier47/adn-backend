package com.ceiba.repartidor.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.repartidor.modelo.dto.DtoRepartidor;
import com.ceiba.repartidor.puerto.dao.DaoRepartidor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoRepartidorMysql implements DaoRepartidor {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "repartidor", value = "listar")
    private static String sqlListar;

    public DaoRepartidorMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoRepartidor> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoRepartidor());
    }
}
