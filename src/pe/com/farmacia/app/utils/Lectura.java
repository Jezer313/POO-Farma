/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.farmacia.app.utils;

import java.util.Scanner;

/**
 *
 * @author Alumno Cisco B6
 */
public class Lectura {
    private static Scanner teclado = new Scanner(System.in);
    
    public String cadena(String mensaje) {
        String entrada = "";
        
        while (entrada.isEmpty()) {
            System.out.print("- " + mensaje);
            entrada = teclado.nextLine().trim();
            
            if (entrada.isEmpty()) {
                System.out.println("\n>No se aceptan respuestas vacias. Reintenta\n");
            }
        }
        return entrada;
    }
    
    public Integer entero(String mensaje) {
        Integer numero = null;
        do {            
         
            try {
            numero = Integer.parseInt(cadena(mensaje));
            
            } catch (NumberFormatException e) {
                System.out.println("\n>Solo se aceptan numeros. Reintenta\n");
            }
            
        } while (numero == null);
        return numero;
    }
    
    public Double decimal(String mensaje) {
        Double decimal = null;
        do {            
            try {
                decimal = Double.parseDouble(cadena(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("\n>Solo se aceptan Numeros. Reintenta\n");
            }
        } while (decimal == null);
        return decimal;
    }
    public void cerrar() {
        teclado.close();
    }
    
}
