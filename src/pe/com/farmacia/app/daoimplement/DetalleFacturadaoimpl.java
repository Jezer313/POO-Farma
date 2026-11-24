package pe.com.farmacia.app.daoimplement;

import java.util.List;
import pe.com.farmacia.app.config.db.Conectadb;
import pe.com.farmacia.app.dao.DetalleFacturadao;
import pe.com.farmacia.app.modelo.DetalleFactura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class DetalleFacturadaoimpl implements DetalleFacturadao {

    private final Conectadb cn = new Conectadb();

    @Override
    public boolean agregar(DetalleFactura detalle) {
        String sql = """
                     INSERT INTO DETALLE_FACTURA(ID_FACTURA, ID_MEDICAMENTO, CANTIDAD, PRECIO)
                     VALUES(?,?,?,?);
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setInt(1, detalle.getId_factura());
            pst.setInt(2, detalle.getId_medicamento());
            pst.setInt(3, detalle.getCantidad());
            pst.setDouble(4, detalle.getPrecio());
            pst.executeUpdate();
            System.out.println("<< SE AGREGÓ UN DETALLE DE FACTURA >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO AGREGAR EL DETALLE DE FACTURA >>");
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminar(int id_detalle) {
        String sql = """
                     DELETE FROM DETALLE_FACTURA
                     WHERE
                        ID_DETALLE = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setInt(1, id_detalle);
            pst.executeUpdate();
            System.out.println("<< SE ELIMINÓ UN DETALLE DE FACTURA >>");
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO ELIMINAR EL DETALLE DE FACTURA >>");
            return false;
        }
        return true;
    }

    @Override
    public List<DetalleFactura> listarPorFactura(int id_factura) {
        List<DetalleFactura> lista = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM DETALLE_FACTURA
                     WHERE
                        ID_FACTURA = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setInt(1, id_factura);
            try (ResultSet rs = pst.executeQuery();) {
                while (rs.next()) {
                    lista.add(new DetalleFactura(
                            rs.getInt("ID_DETALLE"),
                            rs.getInt("ID_FACTURA"),
                            rs.getInt("ID_MEDICAMENTO"),
                            rs.getInt("CANTIDAD"),
                            rs.getDouble("PRECIO")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO LISTAR DETALLES DE FACTURA >>");
        }
        return lista;
    }
}