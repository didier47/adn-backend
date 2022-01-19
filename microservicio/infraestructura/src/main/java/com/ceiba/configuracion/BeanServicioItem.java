package com.ceiba.configuracion;

import com.ceiba.item.puerto.dao.DaoItem;
import com.ceiba.item.puerto.repositorio.RepositorioItem;
import com.ceiba.item.servicio.ServicioActualizarItem;
import com.ceiba.item.servicio.ServicioCrearItem;
import com.ceiba.item.servicio.ServicioEliminarItem;
import com.ceiba.item.servicio.ServicioInventariarItems;
import com.ceiba.venta.puerto.dao.DaoVenta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioItem {

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

    @Bean
    public ServicioInventariarItems servicioInventariarItems(DaoItem daoItem, RepositorioItem repositorioItem, RepositorioVenta repositorioVenta, DaoVenta daoVenta) {
        return new ServicioInventariarItems(daoItem, repositorioItem, repositorioVenta, daoVenta);
    }

}
