package pe.com.farmacia.app.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author USER
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleFactura {
    private int id_detalle;
    private int id_factura;
    private int id_medicamento;
    private int cantidad;
    private double precio;
}
