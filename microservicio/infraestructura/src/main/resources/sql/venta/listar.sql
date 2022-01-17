select venta.id, idRepartidor, referencia, distancia, fechaEntrega, valorEnvio, identificacion, nombres, apellidos, telefono
from venta join repartidor on venta.idRepartidor = repartidor.id;