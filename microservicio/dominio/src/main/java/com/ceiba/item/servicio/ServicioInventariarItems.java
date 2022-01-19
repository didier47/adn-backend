package com.ceiba.item.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.item.modelo.dto.DtoIdCantidadItem;
import com.ceiba.item.modelo.dto.DtoItem;
import com.ceiba.item.modelo.entidad.Item;
import com.ceiba.item.puerto.dao.DaoItem;
import com.ceiba.item.puerto.repositorio.RepositorioItem;
import com.ceiba.venta.modelo.dto.DtoVenta;
import com.ceiba.venta.puerto.dao.DaoVenta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;

import java.util.*;
import java.util.stream.Collectors;

public class ServicioInventariarItems {

    private static final String EL_ITEM_NO_EXISTE_EN_EL_SISTEMA = "El item no existe en el sistema";

    private static final String ITEMS_PARA_VENTA = "itemsParaVenta";
    private static final String ITEMS_PARA_ACTUALIZAR_INVENTARIO = "itemsParaActualizarInventario";

    private final DaoItem daoItem;
    private final RepositorioItem repositorioItem;
    private final RepositorioVenta repositorioVenta;
    private final DaoVenta daoVenta;

    public ServicioInventariarItems(DaoItem daoItem, RepositorioItem repositorioItem, RepositorioVenta repositorioVenta, DaoVenta daoVenta) {
        this.daoItem = daoItem;
        this.repositorioItem = repositorioItem;
        this.repositorioVenta = repositorioVenta;
        this.daoVenta = daoVenta;
    }

    public List<Item> ejecutar(List<DtoIdCantidadItem> idCantidadItemList, Long idVenta) {
        validarExistenciaPrevia(idCantidadItemList);
        DtoVenta dtoVenta = null;
        if (validarExistenciaPreviaVenta(idVenta)) {
            dtoVenta = obtenerVenta(idVenta);
        }
        List<Long> ids = idCantidadItemList.stream().map(DtoIdCantidadItem::getId).collect(Collectors.toList());
        List<DtoItem> dtoItemList = this.daoItem.listarPorIds(ids);
        Map<String, List<Item>> resultadoItemsMap = convertirListaDtoItemAListaItemConCantidadAVenderEInventario(dtoItemList, idCantidadItemList, dtoVenta);
        this.repositorioItem.actualizarBatch(resultadoItemsMap.get(ITEMS_PARA_ACTUALIZAR_INVENTARIO));
        return resultadoItemsMap.get(ITEMS_PARA_VENTA);
    }

    private void validarExistenciaPrevia(List<DtoIdCantidadItem> idCantidadItemList) {
        for (DtoIdCantidadItem dtoIdCantidadItem : idCantidadItemList) {
            boolean existe = this.repositorioItem.existePorId(dtoIdCantidadItem.getId());
            if (!existe) {
                throw new ExcepcionDuplicidad(EL_ITEM_NO_EXISTE_EN_EL_SISTEMA);
            }
        }
    }

    private boolean validarExistenciaPreviaVenta(Long idVenta) {
        return this.repositorioVenta.existePorId(idVenta);
    }

    private DtoVenta obtenerVenta(Long idVenta) {
        return this.daoVenta.obtenerPorId(idVenta);
    }

    private Map<String, List<Item>> convertirListaDtoItemAListaItemConCantidadAVenderEInventario(List<DtoItem> dtoItemList, List<DtoIdCantidadItem> idCantidadItemList, DtoVenta dtoVenta) {
        List<Item> itemsParaVenta = new ArrayList<>();
        List<Item> itemsParaActualizarInventario = new ArrayList<>();
        for (DtoItem dtoItem : dtoItemList) {
            for (DtoIdCantidadItem dtoIdCantidadItem : idCantidadItemList) {
                if (Objects.equals(dtoItem.getId(), dtoIdCantidadItem.getId())) {
                    itemsParaVenta.add(new Item(dtoItem.getId(), dtoItem.getReferencia(), dtoItem.getNombre(), dtoIdCantidadItem.getCantidad()));
                    itemsParaActualizarInventario.add(calcularInventarioDeItem(dtoVenta, dtoItem, dtoIdCantidadItem));
                    break;
                }
            }
        }
        Map<String, List<Item>> resultadoMapItems = new HashMap<>();
        resultadoMapItems.put(ITEMS_PARA_VENTA, itemsParaVenta);
        resultadoMapItems.put(ITEMS_PARA_ACTUALIZAR_INVENTARIO, itemsParaActualizarInventario);
        return resultadoMapItems;
    }

    private Item calcularInventarioDeItem(DtoVenta dtoVenta, DtoItem dtoItem, DtoIdCantidadItem dtoIdCantidadItem) {
        if (dtoVenta != null) {
            DtoItem dtoItemExistente = dtoVenta.getItems().stream().filter(itemEnVentaExistente -> dtoItem.getId().equals(itemEnVentaExistente.getId())).findAny().orElse(null);
            if (dtoItemExistente != null) {
                return new Item(dtoItem.getId(), dtoItem.getReferencia(), dtoItem.getNombre(), dtoItem.getCantidad() - dtoIdCantidadItem.getCantidad() + dtoItemExistente.getCantidad());
            } else {
                return new Item(dtoItem.getId(), dtoItem.getReferencia(), dtoItem.getNombre(), dtoItem.getCantidad() - dtoIdCantidadItem.getCantidad());
            }
        } else {
            return new Item(dtoItem.getId(), dtoItem.getReferencia(), dtoItem.getNombre(), dtoItem.getCantidad() - dtoIdCantidadItem.getCantidad());
        }
    }

}
