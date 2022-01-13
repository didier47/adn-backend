package com.ceiba.venta.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.servicio.testdatabuilder.VentaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VentaTest {

    static final long DISTANCIA_LIMITE_PARA_ENVIO_PRIMER_NIVEL = 10L;
    static final long DISTANCIA_LIMITE_PARA_ENVIO_SEGUNDO_NIVEL = 30L;
    static final long DISTANCIA_LIMITE_PARA_ENVIO_TERCER_NIVEL = 70L;

    static final double VALOR_PARA_ENVIO_PRIMER_NIVEL = 10000;
    static final double VALOR_PARA_ENVIO_SEGUNDO_NIVEL = 20000;
    static final double VALOR_PARA_ENVIO_TERCER_NIVEL = 40000;
    static final double VALOR_PARA_ENVIO_CUARTO_NIVEL = 80000;

    static final double VALOR_ADICIONAL_ENVIO_DIA_NO_HABIL = 10000;

    @Test
    @DisplayName("Deberia crear correctamente la venta")
    void deberiaCrearCorrectamenteLaVenta() {
        // arrange
        Long idRepartidor = 2L;
        Long distancia = 5L;
        LocalDate fechaEntrega = LocalDate.now().plusDays(1);
        Double valorEnvio = 10000.0;
        //act
        Venta venta = new VentaTestDataBuilder().conIdRepartidor(idRepartidor).conDistancia(distancia).conFechaEntrega(fechaEntrega).conValorEnvio(valorEnvio).conId(1L).build();
        //assert
        assertEquals(1, venta.getId());
        assertEquals(idRepartidor, venta.getIdRepartidor());
        assertEquals("referenciaventa", venta.getReferencia());
        assertEquals(distancia, venta.getDistancia());
        assertEquals(fechaEntrega, venta.getFechaEntrega());
        assertEquals(valorEnvio, venta.getValorEnvio());
    }

    @Test
    @DisplayName("Deberia crear correctamente la venta con valor de envio de primer nivel en dia habil")
    void deberiaCrearCorrectamenteLaVentaConValorEnvioPrimerNivelEnDiaHabil() {
        // arrange
        Long distancia = 5L;
        LocalDate fechaEntrega = LocalDate.now().plusDays(8).with(DayOfWeek.MONDAY);
        Double valorEnvio = VALOR_PARA_ENVIO_PRIMER_NIVEL;
        //act
        Venta venta = new VentaTestDataBuilder().conDistancia(distancia).conFechaEntrega(fechaEntrega).conId(1L).build();
        //assert
        assertEquals(1, venta.getId());
        assertEquals(2, venta.getIdRepartidor());
        assertEquals("referenciaventa", venta.getReferencia());
        assertEquals(distancia, venta.getDistancia());
        assertEquals(fechaEntrega, venta.getFechaEntrega());
        assertEquals(valorEnvio, venta.getValorEnvio());
    }

    @Test
    @DisplayName("Deberia crear correctamente la venta con valor de envio de segundo nivel en dia habil")
    void deberiaCrearCorrectamenteLaVentaConValorEnvioSegundoNivelEnDiaHabil() {
        // arrange
        Long distancia = 15L;
        LocalDate fechaEntrega = LocalDate.now().plusDays(8).with(DayOfWeek.MONDAY);
        Double valorEnvio = VALOR_PARA_ENVIO_SEGUNDO_NIVEL;
        //act
        Venta venta = new VentaTestDataBuilder().conDistancia(distancia).conFechaEntrega(fechaEntrega).conId(1L).build();
        //assert
        assertEquals(1, venta.getId());
        assertEquals(2, venta.getIdRepartidor());
        assertEquals("referenciaventa", venta.getReferencia());
        assertEquals(distancia, venta.getDistancia());
        assertEquals(fechaEntrega, venta.getFechaEntrega());
        assertEquals(valorEnvio, venta.getValorEnvio());
    }

    @Test
    @DisplayName("Deberia crear correctamente la venta con valor de envio de tercer nivel en dia habil")
    void deberiaCrearCorrectamenteLaVentaConValorEnvioTercerNivelEnDiaHabil() {
        // arrange
        Long distancia = 35L;
        LocalDate fechaEntrega = LocalDate.now().plusDays(8).with(DayOfWeek.MONDAY);
        Double valorEnvio = VALOR_PARA_ENVIO_TERCER_NIVEL;
        //act
        Venta venta = new VentaTestDataBuilder().conDistancia(distancia).conFechaEntrega(fechaEntrega).conId(1L).build();
        //assert
        assertEquals(1, venta.getId());
        assertEquals(2, venta.getIdRepartidor());
        assertEquals("referenciaventa", venta.getReferencia());
        assertEquals(distancia, venta.getDistancia());
        assertEquals(fechaEntrega, venta.getFechaEntrega());
        assertEquals(valorEnvio, venta.getValorEnvio());
    }

    @Test
    @DisplayName("Deberia crear correctamente la venta con valor de envio de cuarto nivel en dia habil")
    void deberiaCrearCorrectamenteLaVentaConValorEnvioCuartoNivelEnDiaHabil() {
        // arrange
        Long distancia = 75L;
        LocalDate fechaEntrega = LocalDate.now().plusDays(8).with(DayOfWeek.MONDAY);
        Double valorEnvio = VALOR_PARA_ENVIO_CUARTO_NIVEL;
        //act
        Venta venta = new VentaTestDataBuilder().conDistancia(distancia).conFechaEntrega(fechaEntrega).conId(1L).build();
        //assert
        assertEquals(1, venta.getId());
        assertEquals(2, venta.getIdRepartidor());
        assertEquals("referenciaventa", venta.getReferencia());
        assertEquals(distancia, venta.getDistancia());
        assertEquals(fechaEntrega, venta.getFechaEntrega());
        assertEquals(valorEnvio, venta.getValorEnvio());
    }

    @Test
    @DisplayName("Deberia crear correctamente la venta con valor de envio de primer nivel en dia no habil")
    void deberiaCrearCorrectamenteLaVentaConValorEnvioPrimerNivelEnDiaNoHabil() {
        // arrange
        Long distancia = 5L;
        LocalDate fechaEntrega = LocalDate.now().plusDays(8).with(DayOfWeek.SUNDAY);
        Double valorEnvio = VALOR_PARA_ENVIO_PRIMER_NIVEL + VALOR_ADICIONAL_ENVIO_DIA_NO_HABIL;
        //act
        Venta venta = new VentaTestDataBuilder().conDistancia(distancia).conFechaEntrega(fechaEntrega).conId(1L).build();
        //assert
        assertEquals(1, venta.getId());
        assertEquals(2, venta.getIdRepartidor());
        assertEquals("referenciaventa", venta.getReferencia());
        assertEquals(distancia, venta.getDistancia());
        assertEquals(fechaEntrega, venta.getFechaEntrega());
        assertEquals(valorEnvio, venta.getValorEnvio());
    }

    @Test
    @DisplayName("Deberia crear correctamente la venta con valor de envio de segundo nivel en dia no habil")
    void deberiaCrearCorrectamenteLaVentaConValorEnvioSegundoNivelEnDiaNoHabil() {
        // arrange
        Long distancia = 15L;
        LocalDate fechaEntrega = LocalDate.now().plusDays(8).with(DayOfWeek.SUNDAY);
        Double valorEnvio = VALOR_PARA_ENVIO_SEGUNDO_NIVEL + VALOR_ADICIONAL_ENVIO_DIA_NO_HABIL;
        //act
        Venta venta = new VentaTestDataBuilder().conDistancia(distancia).conFechaEntrega(fechaEntrega).conId(1L).build();
        //assert
        assertEquals(1, venta.getId());
        assertEquals(2, venta.getIdRepartidor());
        assertEquals("referenciaventa", venta.getReferencia());
        assertEquals(distancia, venta.getDistancia());
        assertEquals(fechaEntrega, venta.getFechaEntrega());
        assertEquals(valorEnvio, venta.getValorEnvio());
    }

    @Test
    @DisplayName("Deberia crear correctamente la venta con valor de envio de tercer nivel en dia no habil")
    void deberiaCrearCorrectamenteLaVentaConValorEnvioTercerNivelEnDiaNoHabil() {
        // arrange
        Long distancia = 35L;
        LocalDate fechaEntrega = LocalDate.now().plusDays(8).with(DayOfWeek.SUNDAY);
        Double valorEnvio = VALOR_PARA_ENVIO_TERCER_NIVEL + VALOR_ADICIONAL_ENVIO_DIA_NO_HABIL;
        //act
        Venta venta = new VentaTestDataBuilder().conDistancia(distancia).conFechaEntrega(fechaEntrega).conId(1L).build();
        //assert
        assertEquals(1, venta.getId());
        assertEquals(2, venta.getIdRepartidor());
        assertEquals("referenciaventa", venta.getReferencia());
        assertEquals(distancia, venta.getDistancia());
        assertEquals(fechaEntrega, venta.getFechaEntrega());
        assertEquals(valorEnvio, venta.getValorEnvio());
    }

    @Test
    @DisplayName("Deberia crear correctamente la venta con valor de envio de cuarto nivel en dia no habil")
    void deberiaCrearCorrectamenteLaVentaConValorEnvioCuartoNivelEnDiaNoHabil() {
        // arrange
        Long distancia = 75L;
        LocalDate fechaEntrega = LocalDate.now().plusDays(8).with(DayOfWeek.SUNDAY);
        Double valorEnvio = VALOR_PARA_ENVIO_CUARTO_NIVEL + VALOR_ADICIONAL_ENVIO_DIA_NO_HABIL;
        //act
        Venta venta = new VentaTestDataBuilder().conDistancia(distancia).conFechaEntrega(fechaEntrega).conId(1L).build();
        //assert
        assertEquals(1, venta.getId());
        assertEquals(2, venta.getIdRepartidor());
        assertEquals("referenciaventa", venta.getReferencia());
        assertEquals(distancia, venta.getDistancia());
        assertEquals(fechaEntrega, venta.getFechaEntrega());
        assertEquals(valorEnvio, venta.getValorEnvio());
    }

    @Test
    void deberiaFallarSinIdRepartidor() {

        //Arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conIdRepartidor(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    ventaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el repartidor");
    }

    @Test
    void deberiaFallarSinReferencia() {

        //Arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conReferencia(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    ventaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la referencia de la venta");
    }

    @Test
    void deberiaFallarSinDistancia() {

        //Arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conDistancia(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    ventaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la distancia de la venta");
    }

    @Test
    void deberiaFallarSiEsDistanciaNegativa() {

        //Arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conDistancia(-1L).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    ventaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La distancia debe ser positiva");
    }

    @Test
    void deberiaFallarSinFechaEntrega() {

        //Arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conFechaEntrega(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    ventaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de entrega de la venta");
    }

    @Test
    void deberiaFallarSiEsFechaEntregaMenorAHoy() {

        //Arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conFechaEntrega(LocalDate.now().minusDays(1)).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    ventaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La fecha de entrega debe ser a partir de mañana");
    }

    @Test
    void deberiaFallarSiEsFechaEntregaIgualAHoy() {

        //Arrange
        VentaTestDataBuilder ventaTestDataBuilder = new VentaTestDataBuilder().conFechaEntrega(LocalDate.now()).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    ventaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La fecha de entrega debe ser a partir de mañana");
    }

}
