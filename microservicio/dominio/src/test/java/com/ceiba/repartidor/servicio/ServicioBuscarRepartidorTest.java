package com.ceiba.repartidor.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.repartidor.puerto.dao.DaoRepartidor;
import com.ceiba.repartidor.puerto.repositorio.RepositorioRepartidor;
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
        Long idRepartidor = 1L;
        RepositorioRepartidor repositorioRepartidor = Mockito.mock(RepositorioRepartidor.class);
        DaoRepartidor daoRepartidor = Mockito.mock(DaoRepartidor.class);
        Mockito.when(repositorioRepartidor.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioBuscarRepartidor servicioBuscarRepartidor = new ServicioBuscarRepartidor(daoRepartidor, repositorioRepartidor);
        // act
        servicioBuscarRepartidor.ejecutar(idRepartidor);
        //assert
        Mockito.verify(daoRepartidor, Mockito.times(1)).buscarPorId(idRepartidor);
    }
}
