package pe.com.farmacia.app.dao;

import java.util.List;
import pe.com.farmacia.app.modelo.Farmaceutico;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public interface Farmaceuticodao {
    public boolean agregar(Farmaceutico farmaseutico);
    public boolean eliminar(int id_farmaseutico);
    public boolean editar(Farmaceutico farmaseutico);
    public List<Farmaceutico> listar();
    public List<Farmaceutico> buscar(String dni);
}
