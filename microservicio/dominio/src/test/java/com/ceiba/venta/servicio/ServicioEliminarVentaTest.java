package com.ceiba.venta.servicio;

import com.ceiba.item.modelo.dto.DtoItem;
import com.ceiba.item.puerto.dao.DaoItem;
import com.ceiba.item.puerto.repositorio.RepositorioItem;
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

class ServicioEliminarVentaTest {

    @Test
    @DisplayName("Deberia eliminar la venta llamando al repositorio")
    void deberiaEliminarLaVentaLlamandoAlRepositorio() {
        // arrange
        List<Long> ids = Collections.singletonList(1L);
        DtoVenta dtoVenta = new DtoVentaTestDataBuilder().conId(1L).build();
        List<DtoItem> dtoItemsExistentes = Collections.singletonList(new DtoItemTestDataBuilder().conId(1L).build());
        RepositorioVenta repositorioVenta = Mockito.mock(RepositorioVenta.class);
        RepositorioItem repositorioItem = Mockito.mock(RepositorioItem.class);
        DaoVenta daoVenta = Mockito.mock(DaoVenta.class);
        DaoItem daoItem = Mockito.mock(DaoItem.class);
        Mockito.when(repositorioVenta.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoVenta.obtenerPorId(Mockito.anyLong())).thenReturn(dtoVenta);
        Mockito.when(daoItem.listarPorIds(Mockito.anyList())).thenReturn(dtoItemsExistentes);
        ServicioEliminarVenta servicioEliminarVenta = new ServicioEliminarVenta(repositorioVenta, repositorioItem, daoVenta, daoItem);
        // act
        servicioEliminarVenta.ejecutar(1L);
        // assert
        Mockito.verify(repositorioItem, Mockito.times(1)).actualizarBatch(Mockito.anyList());
        Mockito.verify(repositorioVenta, Mockito.times(1)).eliminar(1L);

    }

}
