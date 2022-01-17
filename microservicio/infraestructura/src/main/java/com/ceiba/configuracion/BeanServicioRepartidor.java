package com.ceiba.configuracion;

import com.ceiba.repartidor.puerto.dao.DaoRepartidor;
import com.ceiba.repartidor.puerto.repositorio.RepositorioRepartidor;
import com.ceiba.repartidor.servicio.ServicioActualizarRepartidor;
import com.ceiba.repartidor.servicio.ServicioBuscarRepartidor;
import com.ceiba.repartidor.servicio.ServicioCrearRepartidor;
import com.ceiba.repartidor.servicio.ServicioEliminarRepartidor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioRepartidor {

    @Bean
    public ServicioCrearRepartidor servicioCrearRepartidor(RepositorioRepartidor repositorioRepartidor) {
        return new ServicioCrearRepartidor(repositorioRepartidor);
    }

    @Bean
    public ServicioEliminarRepartidor servicioEliminarRepartidor(RepositorioRepartidor repositorioRepartidor) {
        return new ServicioEliminarRepartidor(repositorioRepartidor);
    }

    @Bean
    public ServicioActualizarRepartidor servicioActualizarRepartidor(RepositorioRepartidor repositorioRepartidor) {
        return new ServicioActualizarRepartidor(repositorioRepartidor);
    }

    @Bean
    public ServicioBuscarRepartidor servicioBuscarRepartidor(DaoRepartidor daoRepartidor, RepositorioRepartidor repositorioRepartidor) {
        return new ServicioBuscarRepartidor(daoRepartidor, repositorioRepartidor);
    }

}
