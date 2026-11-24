package pe.com.farmacia.app.daoimplement;

import java.util.List;
import pe.com.farmacia.app.config.db.Conectadb;
import pe.com.farmacia.app.dao.Medicamentodao;
import pe.com.farmacia.app.modelo.Medicamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Medicamentodaoimpl implements Medicamentodao {

    private final Conectadb cn = new Conectadb();

    @Override
    public boolean agregar(Medicamento medicamento) {
        String sql = """
                     INSERT INTO MEDICAMENTO(CODIGO, NOMBRE_COMERCIAL, NOMBRE_GENERICO, DESCRIPCION, CANTIDAD, CURA, PRECIO_UNITARIO, PRECIO_CAJA)
                     VALUES(?,?,?,?,?,?,?,?);
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, medicamento.getCodigo());
            pst.setString(2, medicamento.getNombre_comercial());
            pst.setString(3, medicamento.getNombre_generico());
            pst.setString(4, medicamento.getDescripcion());
            pst.setInt(5, medicamento.getCantidad());
            pst.setString(6, medicamento.getCura());
            pst.setDouble(7, medicamento.getPreciounitario());
            pst.setDouble(8, medicamento.getPreciocaja());
            pst.executeUpdate();
            System.out.println("<< SE AGREGÓ UN NUEVO MEDICAMENTO >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO AGREGAR EL MEDICAMENTO >>");
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminar(int id_medicamento) {
        String sql = """
                     DELETE FROM MEDICAMENTO
                     WHERE
                        ID_MEDICAMENTO = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setInt(1, id_medicamento);
            pst.executeUpdate();
            System.out.println("<< SE ELIMINÓ UN MEDICAMENTO >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO ELIMINAR EL MEDICAMENTO >>");
            return false;
        }
        return true;
    }

    @Override
    public boolean editar(Medicamento medicamento) {
        String sql = """
                     UPDATE MEDICAMENTO
                     SET
                        CODIGO = ?,
                        NOMBRE_COMERCIAL = ?,
                        NOMBRE_GENERICO = ?,
                        DESCRIPCION = ?,
                        CANTIDAD = ?,
                        CURA = ?,
                        PRECIO_UNITARIO = ?,
                        PRECIO_CAJA = ?
                     WHERE
                        ID_MEDICAMENTO = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, medicamento.getCodigo());
            pst.setString(2, medicamento.getNombre_comercial());
            pst.setString(3, medicamento.getNombre_generico());
            pst.setString(4, medicamento.getDescripcion());
            pst.setInt(5, medicamento.getCantidad());
            pst.setString(6, medicamento.getCura());
            pst.setDouble(7, medicamento.getPreciounitario());
            pst.setDouble(8, medicamento.getPreciocaja());
            pst.setInt(9, medicamento.getId_medicamento());
            pst.executeUpdate();
            System.out.println("<< SE EDITARON LOS DATOS DEL MEDICAMENTO >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO EDITAR EL MEDICAMENTO >>");
            return false;
        }
        return true;
    }

    @Override
    public List<Medicamento> listar() {
        List<Medicamento> lista = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM MEDICAMENTO;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql); ResultSet rs = pst.executeQuery();) {
            while (rs.next()) {
                lista.add(new Medicamento(
                        rs.getInt("ID_MEDICAMENTO"),
                        rs.getString("CODIGO"),
                        rs.getString("NOMBRE_COMERCIAL"),
                        rs.getString("NOMBRE_GENERICO"),
                        rs.getString("DESCRIPCION"),
                        rs.getInt("CANTIDAD"),
                        rs.getString("CURA"),
                        rs.getDouble("PRECIO_UNITARIO"),
                        rs.getDouble("PRECIO_CAJA")
                ));
            }
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO LISTAR MEDICAMENTOS >>");
        }
        return lista;
    }

    @Override
    public List<Medicamento> buscar(String codigo) {
        List<Medicamento> lista = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM MEDICAMENTO
                     WHERE
                        CODIGO LIKE ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, "%" + codigo + "%");
            try (ResultSet rs = pst.executeQuery();) {
                while (rs.next()) {
                    lista.add(new Medicamento(
                            rs.getInt("ID_MEDICAMENTO"),
                            rs.getString("CODIGO"),
                            rs.getString("NOMBRE_COMERCIAL"),
                            rs.getString("NOMBRE_GENERICO"),
                            rs.getString("DESCRIPCION"),
                            rs.getInt("CANTIDAD"),
                            rs.getString("CURA"),
                            rs.getDouble("PRECIO_UNITARIO"),
                            rs.getDouble("PRECIO_CAJA")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("<< ERROR AL BUSCAR MEDICAMENTO POR CÓDIGO >>");
        }
        return lista;
    }
}
