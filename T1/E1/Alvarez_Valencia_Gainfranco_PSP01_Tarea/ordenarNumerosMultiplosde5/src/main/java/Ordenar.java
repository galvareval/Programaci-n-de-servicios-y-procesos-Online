/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *  Esta calse ordena de menor a mayor los numeros multiplos de 5 de un conjunto de números recibidos
 * @author gianf
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Ordenar 
{
    public static void main(String[] args)
    {
        try {
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                String stNumeros = null;
                System.out.println("Números");
                //Leer los números y almacenarlos en un array, pasarlo a arraylist para trabajarlo mas facilmente si son múltiplos de 5
                while ((stNumeros = br.readLine()) != null) 
                {
                    String[] datos = stNumeros.split(" ");
                    
                    ArrayList<String> listaDatos = new ArrayList<String>();
                    ArrayList<Integer> listaNumeros = new ArrayList<Integer>();
                    Collections.addAll(listaDatos, datos);
                    for (String num: listaDatos)
                    {
                         if (Integer.parseInt(num)%5 ==0 )
                            listaNumeros.add(Integer.parseInt(num));
                    }
                    
                    Collections.sort(listaNumeros);                 
                    for (int i: listaNumeros)
                    {
                        System.out.println(i);
                    }
                }
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
}
