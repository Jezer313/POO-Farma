package pe.com.farmacia.app.dao;

import java.util.List;
import pe.com.farmacia.app.modelo.Medicamento;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public interface Medicamentodao {
    public boolean agregar(Medicamento medicamento);
    public boolean eliminar(int id_medicamento);
    public boolean editar(Medicamento medicamento);
    public List<Medicamento> listar();
    public List<Medicamento> buscar(String codigo);
}
