package pe.com.farmacia.app.daoimplement;

import java.util.List;
import pe.com.farmacia.app.config.db.Conectadb;
import pe.com.farmacia.app.dao.Facturadao;
import pe.com.farmacia.app.modelo.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Facturadaoimpl implements Facturadao {

    private final Conectadb cn = new Conectadb();

    @Override
    public boolean agregar(Factura factura) {
        String sql = """
                     INSERT INTO FACTURA(CODIGO, DESCRIPCION, FECHA, PRECIO_UNITARIO, TOTAL, CANTIDAD, ID_CLIENTE, ID_FARMACEUTICO)
                     VALUES(?,?,?,?,?,?,?,?);
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, factura.getCodigo());
            pst.setString(2, factura.getDescripcion());
            pst.setString(3, factura.getFecha());
            pst.setDouble(4, factura.getPreciounitario());
            pst.setDouble(5, factura.getCostototal());
            pst.setInt(6, factura.getCantidad());
            pst.setInt(7, factura.getId_cliente());
            pst.setInt(8, factura.getId_farmaceutico());
            pst.executeUpdate();
            System.out.println("<< SE AGREGÓ UNA NUEVA FACTURA >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO AGREGAR LA FACTURA >>");
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminar(int id_factura) {
        String sql = """
                     DELETE FROM FACTURA
                     WHERE
                        ID_FACTURA = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setInt(1, id_factura);
            pst.executeUpdate();
            System.out.println("<< SE ELIMINÓ UNA FACTURA >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO ELIMINAR LA FACTURA >>");
            return false;
        }
        return true;
    }

    @Override
    public boolean editar(Factura factura) {
        String sql = """
                     UPDATE FACTURA
                     SET
                        CODIGO = ?,
                        DESCRIPCION = ?,
                        FECHA = ?,
                        PRECIO_UNITARIO = ?,
                        TOTAL = ?,
                        CANTIDAD = ?,
                        ID_CLIENTE = ?,
                        ID_FARMACEUTICO = ?
                     WHERE
                        ID_FACTURA = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, factura.getCodigo());
            pst.setString(2, factura.getDescripcion());
            pst.setString(3, factura.getFecha());
            pst.setDouble(4, factura.getPreciounitario());
            pst.setDouble(5, factura.getCostototal());
            pst.setInt(6, factura.getCantidad());
            pst.setInt(7, factura.getId_cliente());
            pst.setInt(8, factura.getId_farmaceutico());
            pst.setInt(9, factura.getId_factura());
            pst.executeUpdate();
            System.out.println("<< SE EDITARON LOS DATOS DE LA FACTURA >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO EDITAR LA FACTURA >>");
            return false;
        }
        return true;
    }

    @Override
    public List<Factura> listar() {
        List<Factura> lista = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM FACTURA;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql); ResultSet rs = pst.executeQuery();) {
            while (rs.next()) {
                lista.add(new Factura(
                        rs.getInt("ID_FACTURA"),
                        rs.getString("CODIGO"),
                        rs.getString("DESCRIPCION"),
                        rs.getString("FECHA"),
                        rs.getDouble("PRECIO_UNITARIO"),
                        rs.getDouble("TOTAL"),
                        rs.getInt("CANTIDAD"),
                        rs.getInt("ID_CLIENTE"),
                        rs.getInt("ID_FARMACEUTICO")
                ));
            }
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO LISTAR FACTURAS >>");
        }
        return lista;
    }

    @Override
    public List<Factura> buscar(String codigo) {
        List<Factura> lista = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM FACTURA
                     WHERE
                        CODIGO LIKE ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, "%" + codigo + "%");
            try (ResultSet rs = pst.executeQuery();) {
                while (rs.next()) {
                    lista.add(new Factura(
                            rs.getInt("ID_FACTURA"),
                            rs.getString("CODIGO"),
                            rs.getString("DESCRIPCION"),
                            rs.getString("FECHA"),
                            rs.getDouble("PRECIO_UNITARIO"),
                            rs.getDouble("TOTAL"),
                            rs.getInt("CANTIDAD"),
                            rs.getInt("ID_CLIENTE"),
                            rs.getInt("ID_FARMACEUTICO")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("<< ERROR AL BUSCAR FACTURA POR CÓDIGO >>");
        }
        return lista;
    }
}