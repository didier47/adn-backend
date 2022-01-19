package com.ceiba.item.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.item.modelo.entidad.Item;
import com.ceiba.item.servicio.testdatabuilder.ItemTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    @Test
    @DisplayName("Deberia crear correctamente el item")
    void deberiaCrearCorrectamenteElItem() {
        // arrange
        Long cantidad = 5L;
        //act
        Item item = new ItemTestDataBuilder().conCantidad(cantidad).conId(1L).build();
        //assert
        assertEquals(1, item.getId());
        assertEquals("referenciaitem", item.getReferencia());
        assertEquals("nombreitem", item.getNombre());
        assertEquals(cantidad, item.getCantidad());
    }

    @Test
    void deberiaFallarSinReferencia() {

        //Arrange
        ItemTestDataBuilder itemTestDataBuilder = new ItemTestDataBuilder().conReferencia(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    itemTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la referencia del item");
    }

    @Test
    void deberiaFallarSinNombre() {

        //Arrange
        ItemTestDataBuilder itemTestDataBuilder = new ItemTestDataBuilder().conNombre(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    itemTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el nombre del item");
    }

    @Test
    void deberiaFallarSiEsCantidadNegativa() {

        //Arrange
        ItemTestDataBuilder itemTestDataBuilder = new ItemTestDataBuilder().conCantidad(-1L).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    itemTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El inventario del item debe ser positivo o igual a cero");
    }

    @Test
    void deberiaFallarSinCantidad() {

        //Arrange
        ItemTestDataBuilder itemTestDataBuilder = new ItemTestDataBuilder().conCantidad(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    itemTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la cantidad del item");
    }

}
