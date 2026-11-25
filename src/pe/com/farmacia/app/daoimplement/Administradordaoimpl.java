package pe.com.farmacia.app.daoimplement;

import java.util.List;
import pe.com.farmacia.app.config.db.Conectadb;
import pe.com.farmacia.app.dao.Administradordao;
import pe.com.farmacia.app.modelo.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Administradordaoimpl implements Administradordao {

    private final Conectadb cn = new Conectadb();

    @Override
    public boolean agregar(Administrador admin) {
        String sql = """
                     INSERT INTO ADMINISTRADOR(DNI, NOMBRE, APELLIDO, TELEFONO, DIRECCION)
                     VALUES(?,?,?,?,?);
                     """;
        String sqlId = "SELECT last_insert_rowid();";
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, admin.getDni());
            pst.setString(2, admin.getNombre());
            pst.setString(3, admin.getApellido());
            pst.setString(4, admin.getTelefono());
            pst.setString(5, admin.getDireccion());
            int filas = pst.executeUpdate();

            if (filas > 0) {
                try (PreparedStatement pstId = c.prepareStatement(sqlId); ResultSet rs = pstId.executeQuery()) {
                    if (rs.next()) {
                        admin.setId_administrador(rs.getInt(1));
                    }
                }
                System.out.println("<< SE AGREGÓ UN NUEVO ADMINISTRADOR >>");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO AGREGAR EL ADMINISTRADOR >>");
        }
        return false;
    }

    @Override
    public boolean eliminar(int id_admin) {
        String sql = """
                     DELETE FROM ADMINISTRADOR
                     WHERE
                        ID_ADMINISTRADOR = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setInt(1, id_admin);
            pst.executeUpdate();
            System.out.println("<< SE ELIMINÓ UN ADMINISTRADOR >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO ELIMINAR EL ADMINISTRADOR >>");
            return false;
        }
        return true;
    }

    @Override
    public boolean editar(Administrador admin) {
        String sql = """
                     UPDATE ADMINISTRADOR
                     SET
                        DNI = ?,
                        NOMBRE = ?,
                        APELLIDO = ?,
                        TELEFONO = ?,
                        DIRECCION = ?
                     WHERE
                        ID_ADMINISTRADOR = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, admin.getDni());
            pst.setString(2, admin.getNombre());
            pst.setString(3, admin.getApellido());
            pst.setString(4, admin.getTelefono());
            pst.setString(5, admin.getDireccion());
            pst.setInt(6, admin.getId_administrador());
            pst.executeUpdate();
            System.out.println("<< SE EDITARON LOS DATOS DEL ADMINISTRADOR >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO EDITAR EL ADMINISTRADOR >>");
            return false;
        }
        return true;
    }

    @Override
    public List<Administrador> listar() {
        List<Administrador> lista = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM ADMINISTRADOR;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql); ResultSet rs = pst.executeQuery();) {
            while (rs.next()) {
                lista.add(new Administrador(
                        rs.getInt("ID_ADMINISTRADOR"),
                        rs.getString("DNI"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO"),
                        rs.getString("TELEFONO"),
                        rs.getString("DIRECCION")
                ));
            }
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO LISTAR ADMINISTRADORES >>");
        }
        return lista;
    }

    @Override
    public List<Administrador> buscar(String dni) {
        List<Administrador> lista = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM ADMINISTRADOR
                     WHERE
                        DNI LIKE ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, "%" + dni + "%");
            try (ResultSet rs = pst.executeQuery();) {
                while (rs.next()) {
                    lista.add(new Administrador(
                            rs.getInt("ID_ADMINISTRADOR"),
                            rs.getString("DNI"),
                            rs.getString("NOMBRE"),
                            rs.getString("APELLIDO"),
                            rs.getString("TELEFONO"),
                            rs.getString("DIRECCION")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("<< ERROR AL BUSCAR ADMINISTRADOR POR DNI >>");
        }
        return lista;
    }
    
    public Administrador obtenerPorId(int id_admin) {
        String sql = "SELECT * FROM ADMINISTRADOR WHERE ID_ADMINISTRADOR = ?;";
        try (Connection c = cn.getConnection();
             PreparedStatement pst = c.prepareStatement(sql)) {
            pst.setInt(1, id_admin);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Administrador(
                            rs.getInt("ID_ADMINISTRADOR"),
                            rs.getString("DNI"),
                            rs.getString("NOMBRE"),
                            rs.getString("APELLIDO"),
                            rs.getString("TELEFONO"),
                            rs.getString("DIRECCION")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("<< ERROR AL OBTENER ADMINISTRADOR POR ID >> " + e.getMessage());
        }
        return null;
    }
}
