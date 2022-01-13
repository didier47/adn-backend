package com.ceiba.repartidor.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.repartidor.modelo.entidad.Repartidor;
import com.ceiba.repartidor.puerto.repositorio.RepositorioRepartidor;
import com.ceiba.repartidor.servicio.testdatabuilder.RepartidorTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioEliminarRepartidorTest {

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando se valide la existencia del Repartidor en una venta")
    void deberiaLanzarUnaExcepcionCuandoSeValideLaExistenciaDelRepartidorEnUnaVenta() {
        // arrange
        Repartidor repartidor = new RepartidorTestDataBuilder().conId(2L).build();
        RepositorioRepartidor repositorioRepartidor = Mockito.mock(RepositorioRepartidor.class);
        Mockito.when(repositorioRepartidor.existeEnVenta(repartidor.getId())).thenReturn(true);
        ServicioEliminarRepartidor servicioEliminarRepartidor = new ServicioEliminarRepartidor(repositorioRepartidor);
        // act - assert
        BasePrueba.assertThrows(() -> servicioEliminarRepartidor.ejecutar(repartidor.getId()), ExcepcionValorInvalido.class, "El repartidor está asociado a una venta");
    }

    @Test
    @DisplayName("Deberia eliminar el repartidor llamando al repositorio")
    void deberiaEliminarElRepartidorLlamandoAlRepositorio() {
        RepositorioRepartidor repositorioRepartidor = Mockito.mock(RepositorioRepartidor.class);
        ServicioEliminarRepartidor servicioEliminarRepartidor = new ServicioEliminarRepartidor(repositorioRepartidor);

        servicioEliminarRepartidor.ejecutar(1L);

        Mockito.verify(repositorioRepartidor, Mockito.times(1)).eliminar(1L);

    }

}
