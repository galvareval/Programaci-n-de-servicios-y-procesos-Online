/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alvarez_valencia_gianfranco_psp02_ejercicio_2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que maneja el comportamiento de los filosofos
 * @author gianf
 */

public class Filosofo extends Thread
{
    //Atributos
    private Mesa mesa;
    private int filosofo;
    private int indFilosofo;
    /**
     * Método contructor de la clase filosofo
     * @param mesa
     * @param filosofo 
     */
    public Filosofo(Mesa mesa, int filosofo) 
    {
        this.filosofo = filosofo;
        this.indFilosofo = filosofo -1;
        this.mesa = mesa;
    }
    /**
     * 
     */
    public void run()
    {
        while(true)//Para que no finalice hasta que se fuerze la finalización
        {
            try 
            {
                this.pensando();
                System.out.println("Filosofo " + this.filosofo + " Hambriento");
                mesa.usarPal(this.indFilosofo);
                this.comiendo();
                System.out.println("Filosofo " + this.filosofo + " Termina de comer, Libres palillos: " + (this.mesa.palIzq(indFilosofo) + 1) + ", " + (this.mesa.palDecho(indFilosofo) + 1));//+1 para Cuadrar la salida con los indices
                mesa.dejarPal(this.indFilosofo);
            } catch (InterruptedException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /**
     * Metodo para setear los filosofos en modo pensar
     * @throws InterruptedException 
     */
    public void pensando() throws InterruptedException
    {
        System.out.println("Filosofo " + this.filosofo +" Pensado");
        Thread.sleep(3000);
    }
    /**
     * Metodo para setear los filosofos en modo comer
     * @throws InterruptedException 
     */
    public void comiendo() throws InterruptedException
    {
        System.out.println("Filosofo " + this.filosofo +" Comiendo");
        Thread.sleep(3000);
    } 
}
