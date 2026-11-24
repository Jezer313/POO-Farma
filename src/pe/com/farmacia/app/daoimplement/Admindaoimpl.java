package pe.com.farmacia.app.daoimplement;

import pe.com.farmacia.app.config.db.Conectadb;
import pe.com.farmacia.app.dao.Admindao;
import pe.com.farmacia.app.modelo.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class Admindaoimpl implements Admindao {

    private final Conectadb cn = new Conectadb();

    @Override
    public boolean iniciarsesion(Admin admin) {
        String sql = """
                     SELECT *
                     FROM ADMIN
                     WHERE
                        USUARIO_ADMIN = ? AND CONTRA_ADMIN = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, admin.getUsuario_admin());
            pst.setString(2, admin.getContra_admin());
            try (ResultSet rs = pst.executeQuery();) {
                if (rs.next()) {
                    System.out.println("<< INICIO DE SESIÓN ADMIN EXITOSO >>");
                    return true;
                }
            }
            System.out.println("<< CREDENCIALES DE ADMIN INVÁLIDAS >>");
        } catch (SQLException e) {
            System.out.println("<< ERROR AL INICIAR SESIÓN ADMIN >>");
        }
        return false;
    }
}