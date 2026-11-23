package pe.com.farmacia.app.modelo;

import lombok.AllArgsConstructor;
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
public class Administrador extends Persona {
    private int id_administrador;
    
    public Administrador(int id_administrador, String dni, String nombre,
                         String apellido, String telefono, String direccion) {
        super(dni, nombre, apellido, telefono, direccion);
        this.id_administrador = id_administrador;
    }
}
