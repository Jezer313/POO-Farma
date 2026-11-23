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
public class Proveedor{
    private int id_proveedor;
    private String nombre;
    private String telefono;
    private String ruc;
    private String direccion;
}
