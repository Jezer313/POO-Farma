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
                System.out.println(">No se aceptan respuestas vacias. Reintenta");
            }
        }
        return entrada;
    }
    
    public Integer entero(String mensaje) {
        Integer numero = 0;
        do {            
         
            try {
            numero = Integer.parseInt(cadena(mensaje));
            
            } catch (NumberFormatException e) {
                System.out.println(">Solo se aceptan numeros. Reintenta");
            }
            
        } while (numero == 0);
        return numero;
    }
    
    public Double decimal(String mensaje) {
        Double decimal = 0.0;
        do {            
            try {
                decimal = Double.parseDouble(cadena(mensaje));
            } catch (NumberFormatException e) {
                System.out.println(">Solo se aceptan Numeros. Reintenta");
            }
        } while (decimal == 0.0);
        return decimal;
    }
    
    
}
