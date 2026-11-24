package pe.com.farmacia.app.dao;

import java.util.List;
import pe.com.farmacia.app.modelo.Proveedor;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public interface Proveedordao {
    public boolean agregar(Proveedor proveedor);
    public boolean eliminar(int id_proveedor);
    public boolean editar(Proveedor proveedor);
    public List<Proveedor> listar();
    public List<Proveedor> buscar(String ruc);
}
