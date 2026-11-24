package pe.com.farmacia.app.dao;

import java.util.List;
import pe.com.farmacia.app.modelo.Administrador;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public interface Administradordao {
    public boolean agregar(Administrador admin);
    public boolean eliminar(int id_admin);
    public boolean editar(Administrador admin);
    public List<Administrador> listar();
    public List<Administrador> buscar(String dni);
}
