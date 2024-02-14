/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.alvarez_valencia_gianfranco_psp02_ejercicio_2;

/**
 * Manjea el programa principal
 * @author gianf
 */
public class Ejercicio2Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Mesa mesa = new Mesa(5);//Mesa con los 5 filosofos
        for (int i = 1; i <= 5; i++)
        {
            Filosofo filosofo = new Filosofo(mesa, i);
            filosofo.start();
        }
    }
}
