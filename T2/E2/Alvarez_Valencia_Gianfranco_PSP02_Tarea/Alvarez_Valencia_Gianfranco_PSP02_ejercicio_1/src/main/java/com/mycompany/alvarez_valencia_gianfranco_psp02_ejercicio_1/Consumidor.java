/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alvarez_valencia_gianfranco_psp02_ejercicio_1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que manjea el consumidor
 * @author gianf
 */
public class Consumidor extends Thread
{
    private Buffer buffer;
    private int enBufe;
    private final int MAX = 15;
    
    public Consumidor(Buffer buffer)
    {
        this.enBufe = 0;
        this.buffer = buffer;
    }
    /**
     * Hilo productor    private final int MAX = 15;
     */
    public void run()
    {
        while(enBufe < MAX)//Limite de caracters --15
        {
            try {
                char c = buffer.consumir();//Obtener char del buffer
                enBufe++;//Aumentar contador
                System.out.println("Recogido  el carácter " + Character.toUpperCase(c) + " del buffer");
                sleep((long) (Math.random() * 5000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}