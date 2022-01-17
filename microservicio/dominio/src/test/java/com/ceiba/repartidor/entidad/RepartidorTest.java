package com.ceiba.repartidor.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.repartidor.modelo.dto.DtoRepartidor;
import com.ceiba.repartidor.modelo.entidad.Repartidor;
import com.ceiba.repartidor.servicio.testdatabuilder.DtoRepartidorTestDataBuilder;
import com.ceiba.repartidor.servicio.testdatabuilder.RepartidorTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepartidorTest {

    @Test
    @DisplayName("Deberia crear correctamente el repartidor")
    void deberiaCrearCorrectamenteElRepartidor() {
        // arrange
        String identificacion = "12345678";
        //act
        Repartidor repartidor = new RepartidorTestDataBuilder().conIdentificacion(identificacion).conId(1L).build();
        //assert
        assertEquals(1, repartidor.getId());
        assertEquals("12345678", identificacion);
        assertEquals("nombresrepartidor", repartidor.getNombres());
        assertEquals("apellidosrepartidor", repartidor.getApellidos());
        assertEquals("telefonorepartidor", repartidor.getTelefono());
    }

    @Test
    @DisplayName("Deberia crear correctamente el repartidor con dto repartidor")
    void deberiaCrearCorrectamenteElRepartidorConDtoRepartidor() {
        // arrange
        DtoRepartidor dtoRepartidor = new DtoRepartidorTestDataBuilder().build();
        //act
        Repartidor repartidor = new Repartidor(dtoRepartidor);
        //assert
        assertEquals(2, repartidor.getId());
        assertEquals("identificacionrepartidor", repartidor.getIdentificacion());
        assertEquals("nombresrepartidor", repartidor.getNombres());
        assertEquals("apellidosrepartidor", repartidor.getApellidos());
        assertEquals("telefonorepartidor", repartidor.getTelefono());
    }

    @Test
    void deberiaFallarSinIdentificacion() {

        //Arrange
        RepartidorTestDataBuilder repartidorTestDataBuilder = new RepartidorTestDataBuilder().conIdentificacion(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    repartidorTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la identificacion del repartidor");
    }

    @Test
    void deberiaFallarSinNombres() {

        //Arrange
        RepartidorTestDataBuilder repartidorTestDataBuilder = new RepartidorTestDataBuilder().conNombres(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    repartidorTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el nombre del repartidor");
    }

    @Test
    void deberiaFallarSinApellidos() {

        //Arrange
        RepartidorTestDataBuilder repartidorTestDataBuilder = new RepartidorTestDataBuilder().conApellidos(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    repartidorTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el apellido del repartidor");
    }

    @Test
    void deberiaFallarSinTelefono() {

        //Arrange
        RepartidorTestDataBuilder repartidorTestDataBuilder = new RepartidorTestDataBuilder().conTelefono(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    repartidorTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el telefono del repartidor");
    }

}
