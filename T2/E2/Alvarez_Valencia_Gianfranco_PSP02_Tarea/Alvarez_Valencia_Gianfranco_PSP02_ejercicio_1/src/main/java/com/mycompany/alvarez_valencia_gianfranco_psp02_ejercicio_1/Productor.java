/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alvarez_valencia_gianfranco_psp02_ejercicio_1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que maneja el productor
 * @author gianf
 */
public class Productor extends Thread
{
    private Buffer buffer;
    private final String letras = "abcdefghijklmnopqrstuvxyz";
    private int enBuf;
    private final int MAX = 15;
    
    public Productor(Buffer buffer)
    {
        this.enBuf = 0;
        this.buffer = buffer;
    }
    /**
     * Hilo productor    private final int MAX = 15;
     */
    public void run()
    {
        while(enBuf < MAX)//Limite de caracters --15
        {
            try {
                char c = letras.charAt((int) (Math.random() * letras.length()));//obtener la letra aleatorioa
                buffer.producir(c);//Al buffer
                enBuf++;//Aumentar contador
                System.out.println("Depositado el carácter " + Character.toUpperCase(c) + " en el buffer");
                sleep((long) (Math.random() * 5000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
