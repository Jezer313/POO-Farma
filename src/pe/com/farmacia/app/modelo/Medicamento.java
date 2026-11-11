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
public class Medicamento {
    private String codigo;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private String cura;
    private double preciounitario;
}
