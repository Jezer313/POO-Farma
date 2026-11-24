package pe.com.farmacia.app.daoimplement;

import java.util.List;
import pe.com.farmacia.app.config.db.Conectadb;
import pe.com.farmacia.app.dao.Farmaceuticodao;
import pe.com.farmacia.app.modelo.Farmaceutico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Farmaceuticodaoimpl implements Farmaceuticodao {

    private final Conectadb cn = new Conectadb();

    @Override
    public boolean agregar(Farmaceutico farmaseutico) {
        String sql = """
                     INSERT INTO FARMACEUTICO(DNI, NOMBRE, APELLIDO, TELEFONO, DIRECCION)
                     VALUES(?,?,?,?,?);
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, farmaseutico.getDni());
            pst.setString(2, farmaseutico.getNombre());
            pst.setString(3, farmaseutico.getApellido());
            pst.setString(4, farmaseutico.getTelefono());
            pst.setString(5, farmaseutico.getDireccion());
            pst.executeUpdate();
            System.out.println("<< SE AGREGÓ UN NUEVO FARMACÉUTICO >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO AGREGAR EL FARMACÉUTICO >>");
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminar(int id_farmaseutico) {
        String sql = """
                     DELETE FROM FARMACEUTICO
                     WHERE
                        ID_FARMACEUTICO = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setInt(1, id_farmaseutico);
            pst.executeUpdate();
            System.out.println("<< SE ELIMINÓ UN FARMACÉUTICO >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO ELIMINAR EL FARMACÉUTICO >>");
            return false;
        }
        return true;
    }

    @Override
    public boolean editar(Farmaceutico farmaseutico) {
        String sql = """
                     UPDATE FARMACEUTICO
                     SET
                        DNI = ?,
                        NOMBRE = ?,
                        APELLIDO = ?,
                        TELEFONO = ?,
                        DIRECCION = ?
                     WHERE
                        ID_FARMACEUTICO = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, farmaseutico.getDni());
            pst.setString(2, farmaseutico.getNombre());
            pst.setString(3, farmaseutico.getApellido());
            pst.setString(4, farmaseutico.getTelefono());
            pst.setString(5, farmaseutico.getDireccion());
            pst.setInt(6, farmaseutico.getId_farmaceutico());
            pst.executeUpdate();
            System.out.println("<< SE EDITARON LOS DATOS DEL FARMACÉUTICO >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO EDITAR EL FARMACÉUTICO >>");
            return false;
        }
        return true;
    }

    @Override
    public List<Farmaceutico> listar() {
        List<Farmaceutico> lista = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM FARMACEUTICO;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql); ResultSet rs = pst.executeQuery();) {
            while (rs.next()) {
                lista.add(new Farmaceutico(
                        rs.getInt("ID_FARMACEUTICO"),
                        rs.getString("DNI"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO"),
                        rs.getString("TELEFONO"),
                        rs.getString("DIRECCION")
                ));
            }
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO LISTAR FARMACÉUTICOS >>");
        }
        return lista;
    }

    @Override
    public List<Farmaceutico> buscar(String dni) {
        List<Farmaceutico> lista = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM FARMACEUTICO
                     WHERE
                        DNI LIKE ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, "%" + dni + "%");
            try (ResultSet rs = pst.executeQuery();) {
                while (rs.next()) {
                    lista.add(new Farmaceutico(
                            rs.getInt("ID_FARMACEUTICO"),
                            rs.getString("DNI"),
                            rs.getString("NOMBRE"),
                            rs.getString("APELLIDO"),
                            rs.getString("TELEFONO"),
                            rs.getString("DIRECCION")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("<< ERROR AL BUSCAR FARMACÉUTICO POR DNI >>");
        }
        return lista;
    }
}