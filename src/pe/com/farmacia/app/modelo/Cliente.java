package pe.com.farmacia.app.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Cliente extends Persona {
    private int id_cliente;
    
    public Cliente(int id_cliente, String dni, String nombre,
                   String apellido, String telefono, String direccion) {
        super(dni, nombre, apellido, telefono, direccion);
        this.id_cliente = id_cliente;
    }
}
