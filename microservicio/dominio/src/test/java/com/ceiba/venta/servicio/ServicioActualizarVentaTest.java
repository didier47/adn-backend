package com.ceiba.venta.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
import com.ceiba.venta.servicio.testdatabuilder.VentaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioActualizarVentaTest {

    @Test
    @DisplayName("Deberia validar la existencia previa de la venta")
    void deberiaValidarLaExistenciaPreviaDeLaVenta() {
        // arrange
        Venta venta = new VentaTestDataBuilder().conId(1L).build();
        RepositorioVenta repositorioVenta = Mockito.mock(RepositorioVenta.class);
        Mockito.when(repositorioVenta.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarVenta servicioActualizarVenta = new ServicioActualizarVenta(repositorioVenta);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarVenta.ejecutar(venta), ExcepcionDuplicidad.class, "La venta no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia actualizar correctamente la venta en el repositorio")
    void deberiaActualizarCorrectamenteEnElRepositorio() {
        // arrange
        Venta venta = new VentaTestDataBuilder().conId(1L).build();
        RepositorioVenta repositorioVenta = Mockito.mock(RepositorioVenta.class);
        Mockito.when(repositorioVenta.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarVenta servicioActualizarVenta = new ServicioActualizarVenta(repositorioVenta);
        // act
        servicioActualizarVenta.ejecutar(venta);
        //assert
        Mockito.verify(repositorioVenta, Mockito.times(1)).actualizar(venta);
    }
}
