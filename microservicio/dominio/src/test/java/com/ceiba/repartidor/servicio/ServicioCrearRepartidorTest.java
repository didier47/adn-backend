package com.ceiba.repartidor.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.repartidor.modelo.entidad.Repartidor;
import com.ceiba.repartidor.puerto.repositorio.RepositorioRepartidor;
import com.ceiba.repartidor.servicio.testdatabuilder.RepartidorTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioCrearRepartidorTest {

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando se valide la existencia del Repartidor")
    void deberiaLanzarUnaExcepcionCuandoSeValideLaExistenciaDelRepartidor() {
        // arrange
        Repartidor repartidor = new RepartidorTestDataBuilder().build();
        RepositorioRepartidor repositorioRepartidor = Mockito.mock(RepositorioRepartidor.class);
        Mockito.when(repositorioRepartidor.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearRepartidor servicioCrearRepartidor = new ServicioCrearRepartidor(repositorioRepartidor);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearRepartidor.ejecutar(repartidor), ExcepcionDuplicidad.class, "El repartidor ya existe en el sistema");
    }

    @Test
    @DisplayName("Deberia crear el repartidor de manera correcta")
    void deberiaCrearElRepartidorDeManeraCorrecta() {
        // arrange
        Repartidor repartidor = new RepartidorTestDataBuilder().build();
        RepositorioRepartidor repositorioRepartidor = Mockito.mock(RepositorioRepartidor.class);
        Mockito.when(repositorioRepartidor.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioRepartidor.crear(repartidor)).thenReturn(10L);
        ServicioCrearRepartidor servicioCrearRepartidor = new ServicioCrearRepartidor(repositorioRepartidor);
        // act
        Long idRepartidor = servicioCrearRepartidor.ejecutar(repartidor);
        //- assert
        assertEquals(10L, idRepartidor);
        Mockito.verify(repositorioRepartidor, Mockito.times(1)).crear(repartidor);
    }
}
