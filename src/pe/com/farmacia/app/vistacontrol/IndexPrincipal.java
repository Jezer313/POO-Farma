package pe.com.farmacia.app.vistacontrol;

import pe.com.farmacia.app.dao.Admindao;
import pe.com.farmacia.app.daoimplement.Admindaoimplement;
import pe.com.farmacia.app.modelo.Admin;
import pe.com.farmacia.app.utils.Lectura;
import pe.com.farmacia.app.utils.Utilitarios;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
public class IndexPrincipal {
    
    private static final Admindao admindao = new Admindaoimplement();
    private static Lectura leer = new Lectura();
    
    public static boolean ingreso_admin(){
        int intentosMax = 3;
        
        for (int i = 1; i <= intentosMax; i++) {
            String usuario = leer.cadena("Usuario: ");
            String contra = leer.cadena("Contraseña: ");
            
            Admin admin = new Admin(usuario,contra);
            
            if(admindao.iniciarsesion(admin)){
                System.out.println("<< ACCESO CONCEDIDO >>");
                return true;
            }
            int restantes = intentosMax - i;
            if(restantes > 0){
                System.out.println("<< Usuario o contraseña incorrectos. Intentos restantes: " + restantes + " >>");
            }
        }
        System.out.println("\nSin más intentos. Acceso denegado.");
        return false;
    }
    
    public static void menu() {
        System.out.println("""
                           ====== BASE DE DATOS FARMACIA ======
                           """);
    }

    public static void inicio() {
        
        int opcion;
        do {
            Utilitarios.lineasenblanco(3);
            menu();
            opcion = leer.entero("Ingrese una opcion: ");
            switch (opcion) {
                case 1 -> {
                    if(ingreso_admin()){
                        IndexAdmin.inicio();
                    }
                }
                case 2 -> IndexCliente.inicio();
                case 3 -> IndexFactura.inicio();
                case 4 -> IndexFarmaseutico.inicio();
                case 5 -> IndexMedicamento.inicio();
                case 6 -> IndexPedido.inicio();
                case 7 -> IndexProveedor.inicio();
                case 8 -> Utilitarios.salir();
                default -> Utilitarios.error(1);
            }
        } while (opcion != 7);
    }
}
