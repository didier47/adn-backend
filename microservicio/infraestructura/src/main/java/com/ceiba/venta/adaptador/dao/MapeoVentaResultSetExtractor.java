package com.ceiba.venta.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.item.modelo.dto.DtoItem;
import com.ceiba.repartidor.modelo.dto.DtoRepartidor;
import com.ceiba.venta.modelo.dto.DtoVenta;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapeoVentaResultSetExtractor implements ResultSetExtractor<List<DtoVenta>>, MapperResult {

    private static final String CAMPO_ID = "id";
    private static final String CAMPO_ID_REPARTIDOR = "idRepartidor";
    private static final String CAMPO_IDENTIFICACION = "identificacion";
    private static final String CAMPO_NOMBRES = "nombres";
    private static final String CAMPO_APELLIDOS = "apellidos";
    private static final String CAMPO_TELEFONO = "telefono";
    private static final String CAMPO_ID_ITEM = "idItem";
    private static final String CAMPO_REFERENCIA_ITEM = "referenciaItem";
    private static final String CAMPO_NOMBRE = "nombre";
    private static final String CAMPO_CANTIDAD = "cantidad";
    private static final String CAMPO_REFERENCIA_VENTA = "referenciaVenta";
    private static final String CAMPO_DISTANCIA = "distancia";
    private static final String CAMPO_FECHA_ENTREGA = "fechaEntrega";
    private static final String CAMPO_VALOR_ENVIO = "valorEnvio";

    @Override
    public List<DtoVenta> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Long, DtoVenta> mapResultadoDtoVenta = new HashMap<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong(CAMPO_ID);

            DtoRepartidor dtoRepartidor = new DtoRepartidor(
                    resultSet.getLong(CAMPO_ID_REPARTIDOR),
                    resultSet.getString(CAMPO_IDENTIFICACION),
                    resultSet.getString(CAMPO_NOMBRES),
                    resultSet.getString(CAMPO_APELLIDOS),
                    resultSet.getString(CAMPO_TELEFONO)
            );

            List<DtoItem> dtoItems = new ArrayList<>();

            DtoItem dtoItem = new DtoItem(
                    resultSet.getLong(CAMPO_ID_ITEM),
                    resultSet.getString(CAMPO_REFERENCIA_ITEM),
                    resultSet.getString(CAMPO_NOMBRE),
                    resultSet.getLong(CAMPO_CANTIDAD)
            );

            dtoItems.add(dtoItem);

            String referencia = resultSet.getString(CAMPO_REFERENCIA_VENTA);
            Long distancia = resultSet.getLong(CAMPO_DISTANCIA);
            LocalDate fechaEntrega = extraerLocalDate(resultSet, CAMPO_FECHA_ENTREGA);
            Double valorEnvio = resultSet.getDouble(CAMPO_VALOR_ENVIO);

            DtoVenta dtoVenta = mapResultadoDtoVenta.get(id);

            if (dtoVenta == null) {
                dtoVenta = new DtoVenta(id, dtoRepartidor, dtoItems, referencia, distancia, fechaEntrega, valorEnvio);
            } else {
                dtoVenta.getItems().addAll(dtoItems);
            }

            mapResultadoDtoVenta.put(id, dtoVenta);
        }
        return new ArrayList<>(mapResultadoDtoVenta.values());
    }
}
