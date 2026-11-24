package pe.com.farmacia.app.daoimplement;

import java.util.List;
import pe.com.farmacia.app.config.db.Conectadb;
import pe.com.farmacia.app.dao.Proveedordao;
import pe.com.farmacia.app.modelo.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Proveedordaoimpl implements Proveedordao {

    private final Conectadb cn = new Conectadb();

    @Override
    public boolean agregar(Proveedor proveedor) {
        String sql = """
                     INSERT INTO PROVEEDOR(NOMBRE, TELEFONO, RUC, DIRECCION)
                     VALUES(?,?,?,?);
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, proveedor.getNombre());
            pst.setString(2, proveedor.getTelefono());
            pst.setString(3, proveedor.getRuc());
            pst.setString(4, proveedor.getDireccion());
            pst.executeUpdate();
            System.out.println("<< SE AGREGÓ UN NUEVO PROVEEDOR >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO AGREGAR EL PROVEEDOR >>");
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminar(int id_proveedor) {
        String sql = """
                     DELETE FROM PROVEEDOR
                     WHERE
                        ID_PROVEEDOR = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setInt(1, id_proveedor);
            pst.executeUpdate();
            System.out.println("<< SE ELIMINÓ UN PROVEEDOR >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO ELIMINAR EL PROVEEDOR >>");
            return false;
        }
        return true;
    }

    @Override
    public boolean editar(Proveedor proveedor) {
        String sql = """
                     UPDATE PROVEEDOR
                     SET
                        NOMBRE = ?,
                        TELEFONO = ?,
                        RUC = ?,
                        DIRECCION = ?
                     WHERE
                        ID_PROVEEDOR = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, proveedor.getNombre());
            pst.setString(2, proveedor.getTelefono());
            pst.setString(3, proveedor.getRuc());
            pst.setString(4, proveedor.getDireccion());
            pst.setInt(5, proveedor.getId_proveedor());
            pst.executeUpdate();
            System.out.println("<< SE EDITARON LOS DATOS DEL PROVEEDOR >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO EDITAR EL PROVEEDOR >>");
            return false;
        }
        return true;
    }

    @Override
    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM PROVEEDOR;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql); ResultSet rs = pst.executeQuery();) {
            while (rs.next()) {
                lista.add(new Proveedor(
                        rs.getInt("ID_PROVEEDOR"),
                        rs.getString("NOMBRE"),
                        rs.getString("TELEFONO"),
                        rs.getString("RUC"),
                        rs.getString("DIRECCION")
                ));
            }
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO LISTAR PROVEEDORES >>");
        }
        return lista;
    }

    @Override
    public List<Proveedor> buscar(String ruc) {
        List<Proveedor> lista = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM PROVEEDOR
                     WHERE
                        RUC LIKE ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, "%" + ruc + "%");
            try (ResultSet rs = pst.executeQuery();) {
                while (rs.next()) {
                    lista.add(new Proveedor(
                            rs.getInt("ID_PROVEEDOR"),
                            rs.getString("NOMBRE"),
                            rs.getString("TELEFONO"),
                            rs.getString("RUC"),
                            rs.getString("DIRECCION")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("<< ERROR AL BUSCAR PROVEEDOR POR RUC >>");
        }
        return lista;
    }
}