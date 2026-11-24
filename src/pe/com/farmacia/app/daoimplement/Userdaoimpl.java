package pe.com.farmacia.app.daoimplement;

import pe.com.farmacia.app.config.db.Conectadb;
import pe.com.farmacia.app.dao.Userdao;
import pe.com.farmacia.app.modelo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class Userdaoimpl implements Userdao {

    private final Conectadb cn = new Conectadb();

    @Override
    public boolean iniciarsesion(User user) {
        String sql = """
                     SELECT *
                     FROM USER
                     WHERE
                        USUARIO_USER = ? AND CONTRA_USER = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setString(1, user.getUsuario_user());
            pst.setString(2, user.getContra_user());
            try (ResultSet rs = pst.executeQuery();) {
                if (rs.next()) {
                    System.out.println("<< INICIO DE SESIÓN USER EXITOSO >>");
                    return true;
                }
            }
            System.out.println("<< CREDENCIALES DE USER INVÁLIDAS >>");
        } catch (SQLException e) {
            System.out.println("<< ERROR AL INICIAR SESIÓN USER >>");
        }
        return false;
    }
}