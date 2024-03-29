package com.ceiba.item.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.item.modelo.dto.DtoIdCantidadItem;
import com.ceiba.item.modelo.dto.DtoItem;
import com.ceiba.item.puerto.dao.DaoItem;
import com.ceiba.item.puerto.repositorio.RepositorioItem;
import com.ceiba.item.servicio.testdatabuilder.DtoIdCantidadItemTestDataBuilder;
import com.ceiba.item.servicio.testdatabuilder.DtoItemTestDataBuilder;
import com.ceiba.venta.modelo.dto.DtoVenta;
import com.ceiba.venta.puerto.dao.DaoVenta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
import com.ceiba.venta.servicio.testdatabuilder.DtoVentaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

class ServicioInventariarItemTest {

    @Test
    @DisplayName("Deberia validar la existencia previa del item al inventariar")
    void deberiaValidarLaExistenciaPreviaDeLosItemsAlInventariar() {
        // arrange
        List<DtoIdCantidadItem> idCantidadItemList = Collections.singletonList(new DtoIdCantidadItemTestDataBuilder().conId(1L).build());
        Long idVenta = 1L;

        DaoItem daoItem = Mockito.mock(DaoItem.class);
        RepositorioItem repositorioItem = Mockito.mock(RepositorioItem.class);
        RepositorioVenta repositorioVenta = Mockito.mock(RepositorioVenta.class);
        DaoVenta daoVenta = Mockito.mock(DaoVenta.class);
        Mockito.when(repositorioItem.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioInventariarItems servicioInventariarItems = new ServicioInventariarItems(daoItem, repositorioItem, repositorioVenta, daoVenta);
        // act - assert
        BasePrueba.assertThrows(() -> servicioInventariarItems.ejecutar(idCantidadItemList, idVenta), ExcepcionDuplicidad.class, "El item no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia inventariar correctamente en el repositorio")
    void deberiaInventariarCorrectamenteEnElRepositorio() {
        // arrange
        List<DtoIdCantidadItem> idCantidadItemList = Collections.singletonList(new DtoIdCantidadItemTestDataBuilder().conId(1L).build());
        Long idVenta = 1L;
        List<DtoItem> dtoItemList = Collections.singletonList(new DtoItemTestDataBuilder().conId(1L).build());
        DtoVenta dtoVenta = new DtoVentaTestDataBuilder().conId(1L).build();

        DaoItem daoItem = Mockito.mock(DaoItem.class);
        RepositorioItem repositorioItem = Mockito.mock(RepositorioItem.class);
        RepositorioVenta repositorioVenta = Mockito.mock(RepositorioVenta.class);
        DaoVenta daoVenta = Mockito.mock(DaoVenta.class);
        Mockito.when(repositorioItem.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioVenta.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoVenta.obtenerPorId(Mockito.anyLong())).thenReturn(dtoVenta);
        Mockito.when(daoItem.listarPorIds(Mockito.anyList())).thenReturn(dtoItemList);
        ServicioInventariarItems servicioInventariarItems = new ServicioInventariarItems(daoItem, repositorioItem, repositorioVenta, daoVenta);
        // act
        servicioInventariarItems.ejecutar(idCantidadItemList, idVenta);
        //assert
        Mockito.verify(repositorioItem, Mockito.times(1)).actualizarBatch(Mockito.anyList());
    }

    @Test
    @DisplayName("Deberia inventariar correctamente en el repositorio")
    void deberiaInventariarCorrectamenteEnElRepositorioConIdVentaNulo() {
        // arrange
        List<DtoIdCantidadItem> idCantidadItemList = Collections.singletonList(new DtoIdCantidadItemTestDataBuilder().conId(1L).build());
        Long idVenta = null;
        List<DtoItem> dtoItemList = Collections.singletonList(new DtoItemTestDataBuilder().conId(1L).build());
        DtoVenta dtoVenta = new DtoVentaTestDataBuilder().conId(1L).build();

        DaoItem daoItem = Mockito.mock(DaoItem.class);
        RepositorioItem repositorioItem = Mockito.mock(RepositorioItem.class);
        RepositorioVenta repositorioVenta = Mockito.mock(RepositorioVenta.class);
        DaoVenta daoVenta = Mockito.mock(DaoVenta.class);
        Mockito.when(repositorioItem.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioVenta.existePorId(idVenta)).thenReturn(false);
        Mockito.when(daoVenta.obtenerPorId(Mockito.anyLong())).thenReturn(dtoVenta);
        Mockito.when(daoItem.listarPorIds(Mockito.anyList())).thenReturn(dtoItemList);
        ServicioInventariarItems servicioInventariarItems = new ServicioInventariarItems(daoItem, repositorioItem, repositorioVenta, daoVenta);
        // act
        servicioInventariarItems.ejecutar(idCantidadItemList, idVenta);
        //assert
        Mockito.verify(repositorioItem, Mockito.times(1)).actualizarBatch(Mockito.anyList());
    }
}
