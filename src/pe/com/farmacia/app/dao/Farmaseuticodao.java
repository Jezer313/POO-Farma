package pe.com.farmacia.app.dao;

import java.util.List;
import pe.com.farmacia.app.modelo.Farmaseutico;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public interface Farmaseuticodao {
    public boolean agregar(Farmaseutico farmaseutico);
    public boolean eliminar(int id_farmaseutico);
    public boolean editar(Farmaseutico farmaseutico);
    public List<Farmaseutico> listar();
    public List<Farmaseutico> buscar(String codigo);
}
