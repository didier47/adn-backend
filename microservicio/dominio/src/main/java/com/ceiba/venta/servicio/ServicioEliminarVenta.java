package com.ceiba.venta.servicio;

import com.ceiba.item.modelo.dto.DtoItem;
import com.ceiba.item.modelo.entidad.Item;
import com.ceiba.item.puerto.dao.DaoItem;
import com.ceiba.item.puerto.repositorio.RepositorioItem;
import com.ceiba.venta.modelo.dto.DtoVenta;
import com.ceiba.venta.puerto.dao.DaoVenta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ServicioEliminarVenta {

    private final RepositorioVenta repositorioVenta;
    private final RepositorioItem repositorioItem;
    private final DaoVenta daoVenta;
    private final DaoItem daoItem;

    public ServicioEliminarVenta(RepositorioVenta repositorioVenta, RepositorioItem repositorioItem, DaoVenta daoVenta, DaoItem daoItem) {
        this.repositorioVenta = repositorioVenta;
        this.repositorioItem = repositorioItem;
        this.daoVenta = daoVenta;
        this.daoItem = daoItem;
    }

    public void ejecutar(Long id) {
        if (validarExistenciaPrevia(id)) {
            DtoVenta dtoVenta = this.daoVenta.obtenerPorId(id);
            List<Long> itemIds = dtoVenta.getItems().stream().map(DtoItem::getId).collect(Collectors.toList());
            List<DtoItem> itemsEnVenta = this.daoItem.listarPorIds(itemIds);
            List<Item> itemsAInventariar = calcularInventarioDeItems(itemsEnVenta, dtoVenta);
            this.repositorioItem.actualizarBatch(itemsAInventariar);
        }
        this.repositorioVenta.eliminar(id);
    }

    private List<Item> calcularInventarioDeItems(List<DtoItem> dtoItemsExistentes, DtoVenta dtoVenta) {
        List<Item> itemsAInventariar = new ArrayList<>();
        for (DtoItem dtoItemExistente : dtoItemsExistentes) {
            for (DtoItem dtoItemEnVenta : dtoVenta.getItems()) {
                if (Objects.equals(dtoItemExistente.getId(), dtoItemEnVenta.getId())) {
                    itemsAInventariar.add(new Item(
                            dtoItemExistente.getId(),
                            dtoItemExistente.getReferencia(),
                            dtoItemExistente.getNombre(),
                            dtoItemExistente.getCantidad() + dtoItemEnVenta.getCantidad()
                    ));
                }
            }
        }
        return itemsAInventariar;
    }

    private boolean validarExistenciaPrevia(Long id) {
        return this.repositorioVenta.existePorId(id);
    }

}
