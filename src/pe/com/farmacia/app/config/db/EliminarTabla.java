package pe.com.farmacia.app.config.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author USER
 */
public class EliminarTabla {
    public static void inicio() {
        Conectadb cn = new Conectadb();
        String sql = """
                     DROP TABLE MEDICAMENTOS;
                     """;
        
        try (   Connection c = cn.getConnection();
                PreparedStatement pst = c.prepareStatement(sql);
                ){
            pst.executeUpdate();
            System.out.println(">Se elimino la tabla Medicamentos");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println(">Porblemas al eliminar la tabla");
        }
    }
    
    public static void main(String[] args) {
        inicio();
    }
}
