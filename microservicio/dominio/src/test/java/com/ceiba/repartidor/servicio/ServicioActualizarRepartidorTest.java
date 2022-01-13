package com.ceiba.repartidor.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.repartidor.modelo.entidad.Repartidor;
import com.ceiba.repartidor.puerto.repositorio.RepositorioRepartidor;
import com.ceiba.repartidor.servicio.testdatabuilder.RepartidorTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioActualizarRepartidorTest {

    @Test
    @DisplayName("Deberia validar la existencia previa del repartidor")
    void deberiaValidarLaExistenciaPreviaDelRepartidor() {
        // arrange
        Repartidor repartidor = new RepartidorTestDataBuilder().conId(1L).build();
        RepositorioRepartidor repositorioRepartidor = Mockito.mock(RepositorioRepartidor.class);
        Mockito.when(repositorioRepartidor.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarRepartidor servicioActualizarRepartidor = new ServicioActualizarRepartidor(repositorioRepartidor);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarRepartidor.ejecutar(repartidor), ExcepcionDuplicidad.class, "El repartidor no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio")
    void deberiaActualizarCorrectamenteEnElRepositorio() {
        // arrange
        Repartidor repartidor = new RepartidorTestDataBuilder().conId(1L).build();
        RepositorioRepartidor repositorioRepartidor = Mockito.mock(RepositorioRepartidor.class);
        Mockito.when(repositorioRepartidor.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarRepartidor servicioActualizarRepartidor = new ServicioActualizarRepartidor(repositorioRepartidor);
        // act
        servicioActualizarRepartidor.ejecutar(repartidor);
        //assert
        Mockito.verify(repositorioRepartidor, Mockito.times(1)).actualizar(repartidor);
    }
}
