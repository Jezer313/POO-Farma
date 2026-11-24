package pe.com.farmacia.app.config.db;

import java.sql.*;

public class CrearTabla {

    public static void inicio() {

        Conectadb cn = new Conectadb();

        String[] sql = {

            //tabla Medicamentos chipis
            """
            CREATE TABLE IF NOT EXISTS MEDICAMENTO (
                ID_MEDICAMENTO      INTEGER PRIMARY KEY AUTOINCREMENT,
                CODIGO              TEXT NOT NULL,
                NOMBRE_COMERCIAL    TEXT NOT NULL,
                NOMBRE_GENERICO     TEXT NOT NULL,
                DESCRIPCION         TEXT NOT NULL,
                CANTIDAD            INTEGER NOT NULL,
                CURA                TEXT NOT NULL,
                PRECIO_UNITARIO     REAL NOT NULL
            );
            """,

            // tabla Farmaceuticos :v
            """
            CREATE TABLE IF NOT EXISTS FARMACEUTICO (
                ID_FARMACEUTICO INTEGER PRIMARY KEY AUTOINCREMENT,
                DNI             TEXT NOT NULL,
                NOMBRE          TEXT NOT NULL,
                APELLIDO        TEXT NOT NULL,
                TELEFONO        TEXT,
                DIRECCION       TEXT
            );
            """,

            //tabla Administradores seran 3 o uno nose
            """
            CREATE TABLE IF NOT EXISTS ADMINISTRADOR (
                ID_ADMINISTRADOR    INTEGER PRIMARY KEY AUTOINCREMENT,
                DNI                 TEXT NOT NULL,
                NOMBRE              TEXT NOT NULL,
                APELLIDO            TEXT NOT NULL,
                TELEFONO            TEXT,
                DIRECCION           TEXT
            );
            """,

            //tabla Clientes toco inventar
            """
            CREATE TABLE IF NOT EXISTS CLIENTE (
                ID_CLIENTE  INTEGER PRIMARY KEY AUTOINCREMENT,
                DNI         TEXT NOT NULL,
                NOMBRE      TEXT NOT NULL,
                APELLIDO    TEXT NOT NULL,
                TELEFONO    TEXT,
                DIRECCION   TEXT
            );
            """,

            //tabla Proveedores a buscar para rellenar
            """
            CREATE TABLE IF NOT EXISTS PROVEEDOR (
                ID_PROVEEDOR    INTEGER PRIMARY KEY AUTOINCREMENT,
                NOMBRE          TEXT NOT NULL,
                TELEFONO        TEXT,
                RUC             TEXT NOT NULL,
                DIRECCION       TEXT NOT NULL
            );
            """,

            //tabla Facturas oño
            """
            CREATE TABLE IF NOT EXISTS FACTURA (
                ID_FACTURA      INTEGER PRIMARY KEY AUTOINCREMENT,
                CODIGO          TEXT NOT NULL,
                DESCRIPCION     TEXT NOT NULL,
                FECHA           TEXT NOT NULL,
                PRECIO_UNITARIO REAL NOT NULL,
                TOTAL           REAL NOT NULL,
                CANTIDAD        INTEGER NOT NULL,
                ID_CLIENTE      INTEGER NOT NULL,
                ID_FARMACEUTICO INTEGER NOT NULL,
                FOREIGN KEY (ID_CLIENTE)
                    REFERENCES CLIENTE(ID_CLIENTE),
                FOREIGN KEY (ID_FARMACEUTICO)
                    REFERENCES FARMACEUTICO(ID_FARMACEUTICO)
            );
            """,

            //detalle de factura
            """
            CREATE TABLE IF NOT EXISTS DETALLE_FACTURA (
                ID_DETALLE      INTEGER PRIMARY KEY AUTOINCREMENT,
                ID_FACTURA      INTEGER NOT NULL,
                ID_MEDICAMENTO  INTEGER NOT NULL,
                CANTIDAD        INTEGER NOT NULL,
                PRECIO          REAL NOT NULL,
                FOREIGN KEY (ID_FACTURA)
                    REFERENCES FACTURA(ID_FACTURA),
                FOREIGN KEY (ID_MEDICAMENTO)
                    REFERENCES MEDICAMENTO(ID_MEDICAMENTO)
            );
            """,

            //detalle de pedido toco provar
            """
            CREATE TABLE IF NOT EXISTS DETALLE_PEDIDO (
                ID_DETALLE      INTEGER PRIMARY KEY AUTOINCREMENT,
                ID_PEDIDO       INTEGER NOT NULL,
                ID_MEDICAMENTO  INTEGER NOT NULL,
                CANTIDAD        INTEGER NOT NULL,
                PRECIO          REAL NOT NULL,
                FOREIGN KEY (ID_PEDIDO)
                    REFERENCES PEDIDO(ID_PEDIDO),
                FOREIGN KEY (ID_MEDICAMENTO)
                    REFERENCES MEDICAMENTO(ID_MEDICAMENTO)
            );
            """,
            
            //Usuario y contraseña de admin
            """
            CREATE TABLE IF NOT EXISTS ADMIN (
                USUARIO_ADMIN   TEXT NOT NULL,
                CONTRA_ADMIN    TEXT NOT NULL
            );
            """,
            
            //Usuario y contraseña de usuario
            """
            CREATE TABLE IF NOT EXISTS USER (
                USUARIO_USER    TEXT NOT NULL,
                CONTRA_USER     TEXT NOT NULL
            );
            """
        };

        try (Connection c = cn.getConnection();
             Statement st = c.createStatement()) {

            //activar las claves foraneas es para que se conecten las tablas entre si, le estamos diciendo 
            //a SQLite que compruebe las relaciones entre las tablas
            st.execute("PRAGMA foreign_keys = ON;");

            for (String consulta : sql) {
                st.executeUpdate(consulta);
            }

            System.out.println("> Todas las tablas fueron creadas correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("> Problemas al crear las tablas.");
        }
    }

    public static void main(String[] args) {
        inicio();
    }
}

