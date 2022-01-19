select venta.id         as id,
       venta.idRepartidor,
       venta.referencia as referenciaVenta,
       venta.distancia,
       venta.fechaEntrega,
       venta.valorEnvio,
       repartidor.identificacion,
       repartidor.nombres,
       repartidor.apellidos,
       repartidor.telefono,
       ventaItems.idItem,
       ventaItems.cantidad,
       item.referencia  as referenciaItem,
       item.nombre
from venta
         join repartidor on venta.idRepartidor = repartidor.id
         join ventaItems on venta.id = ventaItems.idVenta
         join item on item.id = ventaItems.idItem
where venta.id = :id;