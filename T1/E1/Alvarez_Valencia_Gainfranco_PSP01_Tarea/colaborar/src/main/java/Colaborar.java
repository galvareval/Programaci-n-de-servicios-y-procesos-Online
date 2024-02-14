
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Esta clase ejecuta 5 instancias de la aplicacion frases en un fichero txt pasado como parámetro
 * @author gianf
 */
public class Colaborar {
    public static void main(String[] args) 
    {
        String instruccion ="";
        if (args.length ==1)// Comporbar el número de argumentoos pasados
        {
            for (int i = 1; i <= 5; i++)
            {
                System.out.println("Proceso: " + i);
                instruccion = "java -jar frases.jar " + i*5 + " " + args[0];//La isntruccion a ejecutar con las lineas y el archivo txt
                try {
                    Runtime.getRuntime().exec(instruccion);//Ejecutar la intrucción
                } catch (IOException ex) {
                    Logger.getLogger(Colaborar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            System.out.println("Faltan parámetros de entrada");
        }
    }
}
