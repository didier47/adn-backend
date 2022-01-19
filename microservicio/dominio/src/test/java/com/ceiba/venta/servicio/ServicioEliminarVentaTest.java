package com.ceiba.venta.servicio;

import com.ceiba.item.puerto.dao.DaoItem;
import com.ceiba.item.puerto.repositorio.RepositorioItem;
import com.ceiba.venta.puerto.dao.DaoVenta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioEliminarVentaTest {

    @Test
    @DisplayName("Deberia eliminar la venta llamando al repositorio")
    void deberiaEliminarLaVentaLlamandoAlRepositorio() {
        RepositorioVenta repositorioVenta = Mockito.mock(RepositorioVenta.class);
        RepositorioItem repositorioItem = Mockito.mock(RepositorioItem.class);
        DaoVenta daoVenta = Mockito.mock(DaoVenta.class);
        DaoItem daoItem = Mockito.mock(DaoItem.class);
        ServicioEliminarVenta servicioEliminarVenta = new ServicioEliminarVenta(repositorioVenta, repositorioItem, daoVenta, daoItem);

        servicioEliminarVenta.ejecutar(1L);

        Mockito.verify(repositorioVenta, Mockito.times(1)).eliminar(1L);

    }

}
