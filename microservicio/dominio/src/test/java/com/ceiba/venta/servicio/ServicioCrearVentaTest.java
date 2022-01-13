package com.ceiba.venta.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
import com.ceiba.venta.servicio.testdatabuilder.VentaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioCrearVentaTest {

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando la distancia sea negativa")
    void deberiaLanzarUnaExcepcionCuandoLaDistanciaSeaNegativa() {
        // arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conDistancia(-1L);
        // act - assert
        BasePrueba.assertThrows(ventaTestDataBuilder::build, ExcepcionValorInvalido.class, "La distancia debe ser positiva");
    }

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando la fecha de entrega sea menor que hoy")
    void deberiaLanzarUnaExcepcionCuandoLaFechaEntregaSeaMenorAHoy() {
        // arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conFechaEntrega(LocalDate.now().minusDays(1));
        // act - assert
        BasePrueba.assertThrows(ventaTestDataBuilder::build, ExcepcionValorInvalido.class, "La fecha de entrega debe ser a partir de mañana");
    }

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando la fecha de entrega sea igual a hoy")
    void deberiaLanzarUnaExcepcionCuandoLaFechaEntregaSeaIgualAHoy() {
        // arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conFechaEntrega(LocalDate.now());
        // act - assert
        BasePrueba.assertThrows(ventaTestDataBuilder::build, ExcepcionValorInvalido.class, "La fecha de entrega debe ser a partir de mañana");
    }

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando se valide la existencia de la Venta")
    void deberiaLanzarUnaExcepcionCuandoSeValideLaExistenciaDeLaVenta() {
        // arrange
        Venta venta = new VentaTestDataBuilder().build();
        RepositorioVenta repositorioVenta = Mockito.mock(RepositorioVenta.class);
        Mockito.when(repositorioVenta.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearVenta servicioCrearVenta = new ServicioCrearVenta(repositorioVenta);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearVenta.ejecutar(venta), ExcepcionDuplicidad.class, "La venta ya existe en el sistema");
    }

    @Test
    @DisplayName("Deberia crear la venta de manera correcta")
    void deberiaCrearLaVentaDeManeraCorrecta() {
        // arrange
        Venta venta = new VentaTestDataBuilder().build();
        RepositorioVenta repositorioVenta = Mockito.mock(RepositorioVenta.class);
        Mockito.when(repositorioVenta.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioVenta.crear(venta)).thenReturn(10L);
        ServicioCrearVenta servicioCrearVenta = new ServicioCrearVenta(repositorioVenta);
        // act
        Long idVenta = servicioCrearVenta.ejecutar(venta);
        //- assert
        assertEquals(10L, idVenta);
        Mockito.verify(repositorioVenta, Mockito.times(1)).crear(venta);
    }
}
