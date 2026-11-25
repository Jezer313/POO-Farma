package pe.com.farmacia.app.dao;

import pe.com.farmacia.app.modelo.Admin;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public interface Admindao {
    public boolean iniciarsesion(Admin admin);
    public boolean registrar(Admin admin);
    public boolean actualizar(Admin admin);
    public boolean eliminar(int id_administrador);
    public Admin buscarPorIdAdmin(int id_administrador);
}
