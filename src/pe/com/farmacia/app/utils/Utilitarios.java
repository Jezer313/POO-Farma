/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.farmacia.app.utils;

/**
 *
 * @author crai.alumno
 */
public class Utilitarios {
    public static void salir() {
        System.out.println("gracias por su visista");
    }
    
    public static void error(int tipo) {
        System.out.print("Error: ");
        switch (tipo) {
            case 1 ->
                System.out.println("Opcion invalida");
            default ->
                System.out.println("-------");
        }
    }

    public static void lineasenblanco(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            System.out.println("");
        }
    }
}
