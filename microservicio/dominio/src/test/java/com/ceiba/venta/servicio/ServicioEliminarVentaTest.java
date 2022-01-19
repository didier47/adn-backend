package com.ceiba.venta.servicio;

import com.ceiba.item.puerto.dao.DaoItem;
import com.ceiba.item.puerto.repositorio.RepositorioItem;
import com.ceiba.venta.modelo.dto.DtoVenta;
import com.ceiba.venta.puerto.dao.DaoVenta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
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
        RepositorioVenta repositorioVenta = Mockito.mock(RepositorioVenta.class);
        RepositorioItem repositorioItem = Mockito.mock(RepositorioItem.class);
        DaoVenta daoVenta = Mockito.mock(DaoVenta.class);
        DaoItem daoItem = Mockito.mock(DaoItem.class);
        Mockito.when(repositorioItem.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoVenta.obtenerPorId(1L)).thenReturn(Mockito.any(DtoVenta.class));
        Mockito.when(daoItem.listarPorIds(ids)).thenReturn(Mockito.anyList());
        ServicioEliminarVenta servicioEliminarVenta = new ServicioEliminarVenta(repositorioVenta, repositorioItem, daoVenta, daoItem);
        // act
        servicioEliminarVenta.ejecutar(1L);
        // assert
        Mockito.verify(repositorioVenta, Mockito.times(1)).eliminar(1L);

    }

}
