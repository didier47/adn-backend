package com.ceiba.venta.servicio;

import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioEliminarVentaTest {

    @Test
    @DisplayName("Deberia eliminar la venta llamando al repositorio")
    void deberiaEliminarLaVentaLlamandoAlRepositorio() {
        RepositorioVenta repositorioVenta = Mockito.mock(RepositorioVenta.class);
        ServicioEliminarVenta servicioEliminarVenta = new ServicioEliminarVenta(repositorioVenta);

        servicioEliminarVenta.ejecutar(1L);

        Mockito.verify(repositorioVenta, Mockito.times(1)).eliminar(1L);

    }

}
