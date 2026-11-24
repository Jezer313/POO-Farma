package pe.com.farmacia.app.dao;

import java.util.List;
import pe.com.farmacia.app.modelo.DetalleFactura;

/**
 *
 * @author USER
 */
public interface DetalleFacturadao {
    public boolean agregar(DetalleFactura detalle);
    public boolean eliminar(int id_detalle);
    public List<DetalleFactura> listarPorFactura(int id_factura);
}
