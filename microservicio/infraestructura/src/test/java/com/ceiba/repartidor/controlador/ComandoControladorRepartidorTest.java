package com.ceiba.repartidor.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.repartidor.comando.ComandoRepartidor;
import com.ceiba.repartidor.servicio.testdatabuilder.ComandoRepartidorTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorRepartidor.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorRepartidorTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia crear un repartidor")
    void deberiaCrearUnRepartidor() throws Exception {
        // arrange
        ComandoRepartidor repartidor = new ComandoRepartidorTestDataBuilder().build();
        // act - assert
        mocMvc.perform(post("/repartidores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(repartidor)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 3}"));
    }

    @Test
    @DisplayName("Deberia actualizar un repartidor")
    void deberiaActualizarUnRepartidor() throws Exception {
        // arrange
        Long id = 1L;
        ComandoRepartidor repartidor = new ComandoRepartidorTestDataBuilder().conId(id).build();
        // act - assert
        mocMvc.perform(put("/repartidores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(repartidor)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deberia eliminar un repartidor")
    void deberiaEliminarUnRepartidor() throws Exception {
        // arrange
        Long id = 1L;
        // act - assert
        mocMvc.perform(delete("/repartidores/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mocMvc.perform(get("/repartidores")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

}
