package pe.com.farmacia.app.daoimplement;

import pe.com.farmacia.app.dao.Admindao;
import pe.com.farmacia.app.modelo.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.com.farmacia.app.config.db.Conectadb;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public class Admindaoimplement implements Admindao{
    
    private final Conectadb cn = new Conectadb();
    
    @Override
    public boolean iniciarsesion(Admin admin) {
        String sql = """
                     SELECT 1 FROM ADMINS
                     WHERE
                        USUARIO_ADMIN = ?
                     AND
                        CONTRA_ADMIN = ?
                     LIMIT 1
                     """;
        try (   Connection c = cn.getConnection();
                PreparedStatement pst = c.prepareStatement(sql);){
            pst.setString(1, admin.getUsuario_admin());
            pst.setString(2, admin.getContra_admin());
            try (ResultSet rs = pst.executeQuery()){
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("<< ERROR AL VALIDAR EN LA BD >>" );
            e.printStackTrace();
            return false;
        }
    }
    
}
