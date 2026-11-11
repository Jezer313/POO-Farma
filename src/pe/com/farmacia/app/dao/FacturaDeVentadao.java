package pe.com.farmacia.app.dao;

import java.util.List;
import pe.com.farmacia.app.modelo.FacturaDeVenta;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public interface FacturaDeVentadao {
    public boolean agregar(FacturaDeVenta factura);
    public boolean eliminar(int id_factura);
    public boolean editar(FacturaDeVenta factura);
    public List<FacturaDeVenta> listar();
    public List<FacturaDeVenta> buscar(String codigo);
}
