create table item
(
    id         int(11)      not null auto_increment,
    referencia varchar(100) not null,
    nombre     varchar(100) not null,
    cantidad   int(11)      not null,
    primary key (id)
);
create table repartidor
(
    id             int(11)      not null auto_increment,
    identificacion varchar(45)  not null,
    nombres        varchar(100) not null,
    apellidos      varchar(100) not null,
    telefono       varchar(45)  not null,
    primary key (id)
);
create table venta
(
    id           int(11)      not null auto_increment,
    idRepartidor int(11)      not null,
    referencia   varchar(100) not null,
    distancia    int(11)      not null,
    fechaEntrega date         not null,
    valorEnvio   double       not null,
    primary key (id),
    foreign key (idRepartidor) references repartidor (id)
);
create table ventaItems
(
    id       int(11) not null auto_increment,
    idVenta  int(11) not null,
    idItem   int(11) not null,
    cantidad int(11) not null,
    primary key (id),
    foreign key (idVenta) references venta (id),
    foreign key (idItem) references item (id)
);