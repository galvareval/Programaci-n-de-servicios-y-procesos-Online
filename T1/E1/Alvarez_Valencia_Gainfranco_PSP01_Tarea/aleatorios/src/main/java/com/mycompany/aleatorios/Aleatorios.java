/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aleatorios;

/**
 * Esta clase genera 40 números aleatorios entra 0 y 100
 * @author gianf
 */
public class Aleatorios {
    public static void main(String[] args) 
    {
        for (int i = 0; i< 40; i++)
        {
            System.out.print(getAletorio(0,100) +" ");
        }
    
    }
    /**
     * Método para obterner un número aletorio impar en un rango de números
     * @param minimo Rango superior
     * @param maximo Rango inferior
     * @return Devuelve el número aleatorio impar
     */
    public static int getAletorio(int minimo,int maximo)
    {
        int num;
       do//Generar numeros aletorios sienmpre que no sea impar
       {
         num =(int)(Math.random()*(maximo-minimo+1)+(minimo));
       }
        while(esImpar(num) == false);
       
       return num;
    }
    /**
    * Método para compriobar si un número es par o impar
    * @param Inumero para pasar el número a evaluar
    * @return devuelve un boleano con el resutlado de la comprobación 
    */
    public static boolean esImpar(int iNumero) 
    {
      if (iNumero%2!=0)
        return true;
      else
        return false;
    }
}
