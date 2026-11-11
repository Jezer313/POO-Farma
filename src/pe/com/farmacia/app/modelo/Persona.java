package pe.com.farmacia.app.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    private int id_persona;
    private String codigo;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
}
