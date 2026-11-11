package pe.com.farmacia.app.dao;

import java.util.List;
import pe.com.farmacia.app.modelo.Pedido;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public interface Pedidodao {
    public boolean agregar(Pedido pedido);
    public boolean eliminar(int id_pedido);
    public boolean editar(Pedido pedido);
    public List<Pedido> listar();
    public List<Pedido> buscar(String codigo);
}
