/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alvarez_valencia_gianfranco_psp02_ejercicio_2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que gestiona la mesa de los filosofos
 * @author gianf
 */
public class Mesa
{
    //Atrib
    //Array de semaforos que contendrá el estado de los palillos
    private Semaphore[] palillos;
    /**
     * Constructor de la clase mesa
     * @param nPalillos Se pasa el número de pallillos de la mesa
     */
    public Mesa(int nPalillos)
    {
        this.palillos = new Semaphore[nPalillos];
        for (int i = 0;i < nPalillos;i++)
        {
            this.palillos[i] = new Semaphore(1);//Establecer 1 pr
        }
    }
    /**
     * Obtener el pallilo que esta a la izquierda del filosofo
     * @param i
     * @return Devuelve la posición del palillo izquierdo
     */
    public int palIzq(int i)
    {
        return i;
    }
    /**
     * Método que devuelve la posición del palillo derecho del filosofo
     * @param i
     * @return Devuelve la posición del palillo derecho
     */
    public int palDecho(int i)
    {
        if (i == 0)
        {
            return this.palillos.length - 1;//Caso especial del primer filosofo
        }
        else
            return i - 1;//El resto de casos de la mesa
    }
    /**
     * Método para restringir los palillos que usa el filosofo
     * @param filosofo La posiciñon del filosofo
     */
    public void usarPal(int filosofo)
    {
        try {
            this.palillos[this.palIzq(filosofo)].acquire();//Setear el semaforo para restringir el pallio izq del filosofo
            this.palillos[this.palDecho(filosofo)].acquire();//Setear el semaforo para restringir el pallio dereco del filosofo
        } catch (InterruptedException ex) {
            Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Método para dejar de restringir los palillos que usa el filosofo
     * @param filosofo La posición del filosofo
     */
    public void dejarPal(int filosofo)
    {
        this.palillos[this.palIzq(filosofo)].release();//Setear el semaforo para dejar de restringir el pallio izq del filosofo
        this.palillos[this.palDecho(filosofo)].release();//Setear el semaforo para dejar de restringir el pallio dereco del filosofo
    }
}
