package com.ceiba.configuracion;

import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
import com.ceiba.venta.servicio.ServicioActualizarVenta;
import com.ceiba.venta.servicio.ServicioCrearVenta;
import com.ceiba.venta.servicio.ServicioEliminarVenta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioVenta {

    @Bean
    public ServicioCrearVenta servicioCrearVenta(RepositorioVenta repositorioVenta) {
        return new ServicioCrearVenta(repositorioVenta);
    }

    @Bean
    public ServicioEliminarVenta servicioEliminarVenta(RepositorioVenta repositorioVenta) {
        return new ServicioEliminarVenta(repositorioVenta);
    }

    @Bean
    public ServicioActualizarVenta servicioActualizarVenta(RepositorioVenta repositorioVenta) {
        return new ServicioActualizarVenta(repositorioVenta);
    }

}
