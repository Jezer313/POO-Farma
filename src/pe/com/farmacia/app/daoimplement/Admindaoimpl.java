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

    @Override
    public boolean registrar(Admin admin) {
        String sql = """
                     INSERT INTO ADMIN(ID_ADMINISTRADOR, USUARIO_ADMIN, CONTRA_ADMIN)
                     VALUES(?,?,?);
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setInt(1, admin.getId_administrador());
            pst.setString(2, admin.getUsuario_admin());
            pst.setString(3, admin.getContra_admin());
            pst.executeUpdate();
            System.out.println("<< SE REGISTRÓ CREDENCIAL DE ADMIN >>");
            return true;
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO REGISTRAR CREDENCIAL DE ADMIN >> " + e.getMessage());
            return false;
        }
    }

    @Override
    public Admin buscarPorIdAdmin(int id_administrador) {
        String sql = """
                     SELECT ID_ADMINISTRADOR, USUARIO_ADMIN, CONTRA_ADMIN
                     FROM ADMIN
                     WHERE ID_ADMINISTRADOR = ?;
                     """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql);) {
            pst.setInt(1, id_administrador);
            try (ResultSet rs = pst.executeQuery();) {
                if (rs.next()) {
                    return new Admin(
                            rs.getInt("ID_ADMINISTRADOR"),
                            rs.getString("USUARIO_ADMIN"),
                            rs.getString("CONTRA_ADMIN")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("<< ERROR AL BUSCAR CREDENCIAL DE ADMIN >>");
        }
        return null;
    }

    @Override
    public boolean actualizar(Admin admin) {
        String sqlUpdate = """
                           UPDATE ADMIN
                           SET USUARIO_ADMIN = ?, CONTRA_ADMIN = ?
                           WHERE ID_ADMINISTRADOR = ?;
                           """;
        String sqlInsert = """
                           INSERT INTO ADMIN(ID_ADMINISTRADOR, USUARIO_ADMIN, CONTRA_ADMIN)
                           VALUES(?,?,?);
                           """;
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sqlUpdate)) {
            pst.setString(1, admin.getUsuario_admin());
            pst.setString(2, admin.getContra_admin());
            pst.setInt(3, admin.getId_administrador());
            int filas = pst.executeUpdate();

            if (filas == 0) {
                try (PreparedStatement pstIns = c.prepareStatement(sqlInsert)) {
                    pstIns.setInt(1, admin.getId_administrador());
                    pstIns.setString(2, admin.getUsuario_admin());
                    pstIns.setString(3, admin.getContra_admin());
                    pstIns.executeUpdate();
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDIERON ACTUALIZAR CREDENCIALES DE ADMIN >> " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id_administrador) {
        String sql = "DELETE FROM ADMIN WHERE ID_ADMINISTRADOR = ?;";
        try (Connection c = cn.getConnection(); PreparedStatement pst = c.prepareStatement(sql)) {
            pst.setInt(1, id_administrador);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("<< NO SE PUDO ELIMINAR CREDENCIAL DE ADMIN >> " + e.getMessage());
            return false;
        }
    }
}
