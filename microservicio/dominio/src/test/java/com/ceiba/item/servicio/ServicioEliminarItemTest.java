package com.ceiba.item.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.item.modelo.entidad.Item;
import com.ceiba.item.puerto.repositorio.RepositorioItem;
import com.ceiba.item.servicio.testdatabuilder.ItemTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioEliminarItemTest {

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando se valide la existencia del Item en una venta")
    void deberiaLanzarUnaExcepcionCuandoSeValideLaExistenciaDelItemEnUnaVenta() {
        // arrange
        Item item = new ItemTestDataBuilder().conId(2L).build();
        RepositorioItem repositorioItem = Mockito.mock(RepositorioItem.class);
        Mockito.when(repositorioItem.existeEnVenta(item.getId())).thenReturn(true);
        ServicioEliminarItem servicioEliminarItem = new ServicioEliminarItem(repositorioItem);
        // act - assert
        BasePrueba.assertThrows(() -> servicioEliminarItem.ejecutar(item.getId()), ExcepcionValorInvalido.class, "El item está asociado a una venta");
    }

    @Test
    @DisplayName("Deberia eliminar el item llamando al repositorio")
    void deberiaEliminarElItemLlamandoAlRepositorio() {
        RepositorioItem repositorioItem = Mockito.mock(RepositorioItem.class);
        ServicioEliminarItem servicioEliminarItem = new ServicioEliminarItem(repositorioItem);

        servicioEliminarItem.ejecutar(1L);

        Mockito.verify(repositorioItem, Mockito.times(1)).eliminar(1L);

    }

}
