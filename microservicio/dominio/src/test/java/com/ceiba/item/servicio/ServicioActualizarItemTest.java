package com.ceiba.item.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.item.modelo.entidad.Item;
import com.ceiba.item.puerto.repositorio.RepositorioItem;
import com.ceiba.item.servicio.testdatabuilder.ItemTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioActualizarItemTest {

    @Test
    @DisplayName("Deberia validar la existencia previa del item")
    void deberiaValidarLaExistenciaPreviaDelItem() {
        // arrange
        Item item = new ItemTestDataBuilder().conId(1L).build();
        RepositorioItem repositorioItem = Mockito.mock(RepositorioItem.class);
        Mockito.when(repositorioItem.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarItem servicioActualizarItem = new ServicioActualizarItem(repositorioItem);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarItem.ejecutar(item), ExcepcionDuplicidad.class, "El item no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio")
    void deberiaActualizarCorrectamenteEnElRepositorio() {
        // arrange
        Item item = new ItemTestDataBuilder().conId(1L).build();
        RepositorioItem repositorioItem = Mockito.mock(RepositorioItem.class);
        Mockito.when(repositorioItem.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarItem servicioActualizarItem = new ServicioActualizarItem(repositorioItem);
        // act
        servicioActualizarItem.ejecutar(item);
        //assert
        Mockito.verify(repositorioItem, Mockito.times(1)).actualizar(item);
    }
}
