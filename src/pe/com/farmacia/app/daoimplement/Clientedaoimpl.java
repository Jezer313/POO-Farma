package pe.com.farmacia.app.daoimplement;

import java.util.List;
import pe.com.farmacia.app.config.db.Conectadb;
import pe.com.farmacia.app.dao.Clientedao;
import pe.com.farmacia.app.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Clientedaoimpl implements Clientedao {

    private final Conectadb cn = new Conectadb();

    @Override
    public boolean agregar(Cliente cliente) {
        String sql = """
                     INSERT INTO CLIENTE(DNI, NOMBRE, APELLIDO, CORREO, TELEFONO, DIRECCION)
                     VALUES(?,?,?,?,?);
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, cliente.getDni());
            pst.setString(2, cliente.getNombre());
            pst.setString(3, cliente.getApellido());
            pst.setString(4, cliente.getTelefono());
            pst.setString(5, cliente.getDireccion());
            pst.executeUpdate();
            System.out.println("<< SE AGREGÓ UN NUEVO CLIENTE >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO AGREGAR EL CLIENTE >>");
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminar(int id_cliente) {
        String sql = """
                     DELETE FROM CLIENTE
                     WHERE
                        ID_CLIENTE = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setInt(1, id_cliente);
            pst.executeUpdate();
            System.out.println("<< SE ELIMINÓ UN CLIENTE >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO ELIMINAR EL CLIENTE >>");
            return false;
        }
        return true;
    }

    @Override
    public boolean editar(Cliente cliente) {
        String sql = """
                     UPDATE CLIENTE
                     SET
                        DNI = ?,
                        NOMBRE = ?,
                        APELLIDO = ?,
                        TELEFONO = ?,
                        DIRECCION = ?
                     WHERE
                        ID_CLIENTE = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, cliente.getDni());
            pst.setString(2, cliente.getNombre());
            pst.setString(3, cliente.getApellido());
            pst.setString(4, cliente.getTelefono());
            pst.setString(5, cliente.getDireccion());
            pst.setInt(6, cliente.getId_cliente());
            pst.executeUpdate();
            System.out.println("<< SE EDITARON LOS DATOS DEL CLIENTE >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO EDITAR EL CLIENTE >>");
            return false;
        }
        return true;
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM CLIENTE;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql); ResultSet rs = pst.executeQuery();) {
            while (rs.next()) {
                lista.add(new Cliente(
                        rs.getInt("ID_CLIENTE"),
                        rs.getString("DNI"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO"),
                        rs.getString("TELEFONO"),
                        rs.getString("DIRECCION")
                ));
            }
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO LISTAR CLIENTES >>");
        }
        return lista;
    }

    @Override
    public List<Cliente> buscar(String dni) {
        List<Cliente> lista = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM CLIENTE
                     WHERE
                        DNI LIKE ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, "%" + dni + "%");
            try (ResultSet rs = pst.executeQuery();) {
                while (rs.next()) {
                    lista.add(new Cliente(
                            rs.getInt("ID_CLIENTE"),
                            rs.getString("DNI"),
                            rs.getString("NOMBRE"),
                            rs.getString("APELLIDO"),
                            rs.getString("TELEFONO"),
                            rs.getString("DIRECCION")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("<< ERROR AL BUSCAR CLIENTE POR DNI >>");
        }
        return lista;
    }
}