package pe.com.farmacia.app.config.db;

import java.sql.*;
/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public class Conectadb {
    private static Connection c = null;
    private static final String DB = "BaseDeDatosFarmacia.db";
    private static final String DRIVER = "org.sqlite.JDBC"; 
    private static final String URL = "jdbc:sqlite:";
    
    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            if (c == null || c.isClosed()) {
                c = DriverManager.getConnection(URL + DB);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println(">Conexion problematica");
            System.exit(1);
        }
        System.out.println("<< " + DB + " >> OK");
        
        return c;
    }
}
