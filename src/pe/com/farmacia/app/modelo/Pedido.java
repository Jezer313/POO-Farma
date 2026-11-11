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
public class Pedido {
    private String codigo;
    private String descripcion;
    private String fecha;
    private double preciounitario;
    private double costototal;
    private int cantidad;
}
