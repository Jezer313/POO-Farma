package pe.com.farmacia.app.dao;

import java.util.List;
import pe.com.farmacia.app.modelo.Factura;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public interface FacturaDeVentadao {
    public boolean agregar(Factura factura);
    public boolean eliminar(int id_factura);
    public boolean editar(Factura factura);
    public List<Factura> listar();
    public List<Factura> buscar(String codigo);
}
