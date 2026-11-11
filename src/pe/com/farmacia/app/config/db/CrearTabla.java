package pe.com.farmacia.app.config.db;

import java.sql.*;
/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public class CrearTabla {
    
    public static void inicio() {
        Conectadb cn = new Conectadb();
        String sql = """
                     CREATE TABLE IF NOT EXISTS MEDICAMENTOS(
                        ID_MEDICAMENTO      INTEGER PRIMARY KEY AUTOINCREMENT,
                        NOMBRE_COMERCIAL    TXT NOT NULL,
                        NOMBRE_GENERICO     TXT NOT NULL,
                        CANTIDAD            INTEGER,
                        PRECIO              REAL
                     );
                     """;
        
        try (   Connection c = cn.getConnection();
                PreparedStatement pst = c.prepareStatement(sql);
                ){
            pst.executeUpdate();
            System.out.println(">Se creo la tabla Medicamentos");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println(">Porblemas al crear la tabla");
        }
    }
    
    public static void main(String[] args) {
        inicio();
    }
}
