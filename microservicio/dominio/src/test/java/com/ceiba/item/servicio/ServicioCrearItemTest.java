package com.ceiba.item.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.item.modelo.entidad.Item;
import com.ceiba.item.puerto.repositorio.RepositorioItem;
import com.ceiba.item.servicio.testdatabuilder.ItemTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioCrearItemTest {

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando la cantidad sea negativa")
    void deberiaLanzarUnaExcepcionCuandoLaCantidadSeaNegativa() {
        // arrange
        ItemTestDataBuilder itemTestDataBuilder = new ItemTestDataBuilder().conCantidad(-1L);
        // act - assert
        BasePrueba.assertThrows(itemTestDataBuilder::build, ExcepcionValorInvalido.class, "La cantidad debe ser positiva");
    }

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando se valide la existencia del Item")
    void deberiaLanzarUnaExcepcionCuandoSeValideLaExistenciaDelItem() {
        // arrange
        Item item = new ItemTestDataBuilder().build();
        RepositorioItem repositorioItem = Mockito.mock(RepositorioItem.class);
        Mockito.when(repositorioItem.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearItem servicioCrearItem = new ServicioCrearItem(repositorioItem);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearItem.ejecutar(item), ExcepcionDuplicidad.class, "El item ya existe en el sistema");
    }

    @Test
    @DisplayName("Deberia crear el item de manera correcta")
    void deberiaCrearElItemDeManeraCorrecta() {
        // arrange
        Item item = new ItemTestDataBuilder().build();
        RepositorioItem repositorioItem = Mockito.mock(RepositorioItem.class);
        Mockito.when(repositorioItem.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioItem.crear(item)).thenReturn(10L);
        ServicioCrearItem servicioCrearItem = new ServicioCrearItem(repositorioItem);
        // act
        Long idItem = servicioCrearItem.ejecutar(item);
        //- assert
        assertEquals(10L, idItem);
        Mockito.verify(repositorioItem, Mockito.times(1)).crear(item);
    }
}
