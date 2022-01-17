package com.ceiba.repartidor.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.repartidor.modelo.entidad.Repartidor;
import com.ceiba.repartidor.puerto.dao.DaoRepartidor;
import com.ceiba.repartidor.puerto.repositorio.RepositorioRepartidor;
import com.ceiba.repartidor.servicio.testdatabuilder.RepartidorTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioBuscarRepartidorTest {

    @Test
    @DisplayName("Deberia validar la existencia previa del repartidor")
    void deberiaValidarLaExistenciaPreviaDelRepartidor() {
        // arrange
        Long idRepartidor = 1L;
        RepositorioRepartidor repositorioRepartidor = Mockito.mock(RepositorioRepartidor.class);
        DaoRepartidor daoRepartidor = Mockito.mock(DaoRepartidor.class);
        Mockito.when(repositorioRepartidor.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioBuscarRepartidor servicioBuscarRepartidor = new ServicioBuscarRepartidor(daoRepartidor, repositorioRepartidor);
        // act - assert
        BasePrueba.assertThrows(() -> servicioBuscarRepartidor.ejecutar(idRepartidor), ExcepcionDuplicidad.class, "El repartidor no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia buscar correctamente en el repositorio")
    void deberiaBuscarCorrectamenteEnElRepositorio() {
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
