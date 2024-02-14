/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.alvarez_valencia_gianfranco_psp02_ejercicio_1;

/**
 * Programa principal
 * @author gianf
 */
public class Ejercicio1_Main {

    /**Metodo main
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Buffer buffer = new Buffer(6);//dimension del buffer 6
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);
        productor.start();
        consumidor.start();
    }
    
}
