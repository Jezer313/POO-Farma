package pe.com.farmacia.app.config.db;

import java.sql.*;

public class CrearTabla {

    public static void inicio() {

        Conectadb cn = new Conectadb();

        String[] sql = {

            //tabla Medicamentos chipis
            """
            CREATE TABLE IF NOT EXISTS MEDICAMENTOS (
                ID_MEDICAMENTO INTEGER PRIMARY KEY AUTOINCREMENT,
                NOMBRE_COMERCIAL TEXT NOT NULL,
                NOMBRE_GENERICO TEXT NOT NULL,
                CANTIDAD INTEGER NOT NULL,
                PRECIO REAL NOT NULL
            );
            """,

            // tabla Farmaceuticos :v
            """
            CREATE TABLE IF NOT EXISTS FARMACEUTICOS (
                ID_FARMACEUTICO INTEGER PRIMARY KEY AUTOINCREMENT,
                NOMBRE TEXT NOT NULL,
                APELLIDO TEXT NOT NULL,
                TELEFONO TEXT,
                DIRECCION TEXT
            );
            """,

            //tabla Administradores seran 3 o uno nose
            """
            CREATE TABLE IF NOT EXISTS ADMINISTRADORES (
                ID_ADMINISTRADOR INTEGER PRIMARY KEY AUTOINCREMENT,
                NOMBRE TEXT NOT NULL,
                APELLIDO TEXT NOT NULL,
                TELEFONO TEXT,
                DIRECCION TEXT
            );
            """,

            //tabla Clientes toco inventar
            """
            CREATE TABLE IF NOT EXISTS CLIENTES (
                ID_CLIENTE INTEGER PRIMARY KEY AUTOINCREMENT,
                NOMBRE TEXT NOT NULL,
                APELLIDO TEXT NOT NULL,
                TELEFONO TEXT,
                DIRECCION TEXT
            );
            """,

            //tabla Proveedores a buscar para rellenar
            """
            CREATE TABLE IF NOT EXISTS PROVEEDORES (
                ID_PROVEEDOR INTEGER PRIMARY KEY AUTOINCREMENT,
                NOMBRE TEXT NOT NULL,
                TELEFONO TEXT,
                DIRECCION TEXT
            );
            """,

            //tabla Facturas oño
            """
            CREATE TABLE IF NOT EXISTS FACTURAS (
                ID_FACTURA INTEGER PRIMARY KEY AUTOINCREMENT,
                DESCRIPCION TEXT,
                FECHA TEXT NOT NULL,
                PRECIO_UNITARIO REAL,
                TOTAL REAL NOT NULL,
                CANTIDAD INTEGER NOT NULL,
                ID_CLIENTE INTEGER NOT NULL,
                ID_FARMACEUTICO INTEGER NOT NULL,
                FOREIGN KEY (ID_CLIENTE)
                    REFERENCES CLIENTES(ID_CLIENTE),
                FOREIGN KEY (ID_FARMACEUTICO)
                    REFERENCES FARMACEUTICOS(ID_FARMACEUTICO)
            );
            """,

            //tabla Pedidos
            """
            CREATE TABLE IF NOT EXISTS PEDIDOS (
                ID_PEDIDO INTEGER PRIMARY KEY AUTOINCREMENT,
                DESCRIPCION TEXT,
                FECHA TEXT NOT NULL,
                PRECIO_UNITARIO REAL,
                TOTAL REAL NOT NULL,
                CANTIDAD INTEGER NOT NULL,
                ID_PROVEEDOR INTEGER NOT NULL,
                ID_ADMINISTRADOR INTEGER NOT NULL,
                FOREIGN KEY (ID_PROVEEDOR)
                    REFERENCES PROVEEDORES(ID_PROVEEDOR),
                FOREIGN KEY (ID_ADMINISTRADOR)
                    REFERENCES ADMINISTRADORES(ID_ADMINISTRADOR)
            );
            """,

            //detalle de factura
            """
            CREATE TABLE IF NOT EXISTS DETALLE_FACTURA (
                ID_DETALLE INTEGER PRIMARY KEY AUTOINCREMENT,
                ID_FACTURA INTEGER NOT NULL,
                ID_MEDICAMENTO INTEGER NOT NULL,
                CANTIDAD INTEGER NOT NULL,
                PRECIO REAL NOT NULL,
                FOREIGN KEY (ID_FACTURA)
                    REFERENCES FACTURAS(ID_FACTURA),
                FOREIGN KEY (ID_MEDICAMENTO)
                    REFERENCES MEDICAMENTOS(ID_MEDICAMENTO)
            );
            """,

            //detalle de pedido toco provar
            """
            CREATE TABLE IF NOT EXISTS DETALLE_PEDIDO (
                ID_DETALLE INTEGER PRIMARY KEY AUTOINCREMENT,
                ID_PEDIDO INTEGER NOT NULL,
                ID_MEDICAMENTO INTEGER NOT NULL,
                CANTIDAD INTEGER NOT NULL,
                PRECIO REAL NOT NULL,
                FOREIGN KEY (ID_PEDIDO)
                    REFERENCES PEDIDOS(ID_PEDIDO),
                FOREIGN KEY (ID_MEDICAMENTO)
                    REFERENCES MEDICAMENTOS(ID_MEDICAMENTO)
            );
            """
        };

        try (Connection c = cn.getConnection();
             Statement st = c.createStatement()) {

            //activar las claves foraneas es para que se conecten las tablas entre si, le estamos diciendo a SQLite que compruebe las relaciones entre las tablas
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

