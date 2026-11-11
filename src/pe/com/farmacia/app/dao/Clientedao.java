package pe.com.farmacia.app.dao;

import java.util.List;
import pe.com.farmacia.app.modelo.Cliente;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public interface Clientedao {
    public boolean agregar(Cliente cliente);
    public boolean eliminar(int id_cliente);
    public boolean editar(Cliente cliente);
    public List<Cliente> listar();
    public List<Cliente> buscar(String codigo);
}
