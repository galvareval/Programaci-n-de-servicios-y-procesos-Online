/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alvarez_valencia_gianfranco_psp02_ejercicio_1;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase que maneja el buffer
 * @author gianf
 */
public class Buffer
{
    //Atributos
    private char [] buffer;
    private int posLetra;
    private boolean lleno;
    private boolean vacio;          
   
    public Buffer(int dimension)
    {
        this.buffer = new char[dimension];
        this.posLetra = 0;
        this.lleno = false;
        this.vacio = true;
    }

    public synchronized void producir (char c)
    {
        while(this.lleno)//Si el buffer esta lleno, no producir
        {
            try {
                wait();//esperar
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer[this.posLetra] = c ;//agregar caracter al buff
        this.posLetra++;//Aumentar contador posletra
        this.vacio = false;//El buffer ya no está vacio
        if(this.posLetra == this.buffer.length)//comprobar si se llena
        {
            this.lleno = true;//buffer lleno
        }   
        notifyAll();
    }
    
    public synchronized char consumir()
    {
        while(this.vacio)//Si el buffer esta vacio,  no consumir
        {
            try {
                wait();//Eseprar
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.posLetra--;//1 menos
        this.lleno = false;//establecer como no lleno
        if (this.posLetra == 0)
        {
            this.vacio = true;
        }
        notifyAll();
        return this.buffer[this.posLetra];
    }
    
    
}