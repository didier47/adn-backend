package com.ceiba.configuracion;

import com.ceiba.item.puerto.repositorio.RepositorioItem;
import com.ceiba.item.servicio.ServicioActualizarItem;
import com.ceiba.item.servicio.ServicioCrearItem;
import com.ceiba.item.servicio.ServicioEliminarItem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearItem servicioCrearItem(RepositorioItem repositorioItem) {
        return new ServicioCrearItem(repositorioItem);
    }

    @Bean
    public ServicioEliminarItem servicioEliminarItem(RepositorioItem repositorioItem) {
        return new ServicioEliminarItem(repositorioItem);
    }

    @Bean
    public ServicioActualizarItem servicioActualizarItem(RepositorioItem repositorioItem) {
        return new ServicioActualizarItem(repositorioItem);
    }

}
