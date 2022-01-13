insert into item(id, referencia, nombre, cantidad)
values (1, 'referenciaitem', 'nombreitem', 5);
insert into item(id, referencia, nombre, cantidad)
values (2, 'referenciaitemdos', 'nombreitemdos', 10);
insert into repartidor(id, identificacion, nombres, apellidos, telefono)
values (1, 'identificacionrepartidor', 'nombresrepartidor', 'apellidosrepartidor', '12345678');
insert into repartidor(id, identificacion, nombres, apellidos, telefono)
values (2, 'identificacionrepartidordos', 'nombresrepartidordos', 'apellidosrepartidordos', '1234567891');
insert into venta(id, idRepartidor, referencia, distancia, fechaEntrega, valorEnvio)
values (1, 2, 'referenciaventa', 50, date '2022-01-13', 40000);
insert into ventaItems(id, idVenta, idItem, cantidad)
values (1, 1, 2, 2);