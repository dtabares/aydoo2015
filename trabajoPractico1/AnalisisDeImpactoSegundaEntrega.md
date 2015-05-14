# Análisis de Impacto de la Segunda Entrega

1. Modificar la clase PerfilDelUsuario para agregar el atributo Domicilio.
2. Crear una clase para modelar la "Promocion Extranjero"
  1. Modificar la clase Aplicador de Promociones, ya que si esta promoción aplica, no se deben aplicar las demás, ya que esta no es acumulable con otras promociones.

3. Crear una clase para modelar la "Promocion Paquete Familiar"
    1. Modificar la clase Atraccion para agregar el atributo cantidadDeEntradas (cosa que no se contemplaba anteriormente).
    2. Modificar la clase Atraccion para modificar el atributo costo por costoUnitario (implica un refactor de atributo y del getter).
    3. Modificar la clase Atraccion para agregar el atributo costoTotal (cantidadDeEntradas * costo unitario).