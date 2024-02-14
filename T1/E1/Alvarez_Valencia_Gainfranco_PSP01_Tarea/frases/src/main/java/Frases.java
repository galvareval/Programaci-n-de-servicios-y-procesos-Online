
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *  Esta clase genera en un fichero txt pasado por parámetro tantas palabras aleatorias como se hayan solicitado
 * @author gianf
 */
public class Frases {
    public static void main(String[] args) {
        String letras ="abcdefghijklmnñopqrstuvwxyz";
        String nombreFichero;
        int numPalabras =0; 
        BufferedWriter writer = null;
        if (args.length ==2)// Comporbar el número de argumentoos pasados
        {
            numPalabras = Integer.parseInt(args[0]);
            nombreFichero = args[1];
            File archivo = new File(nombreFichero);
            try {
                writer = new BufferedWriter(new FileWriter(archivo, true));
           
            if (!archivo.exists())
            {
                try {
                    archivo.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(Frases.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Repetir por tantas palbras se hayan solicitado
            for (int i = 0; i < numPalabras; i++)
            {
                String palabra = "";
                int numCaracteres = getAletorio(1, 12);
                for (int j = 0; j < numCaracteres; j++)//Generar las pabras aleatorias
                {
                    palabra += letras.charAt(getAletorio(0, letras.length()-1));
                }
                try {
                        writer.write(palabra + "\n");// escribir la palabra en el fichero
                    } catch (IOException ex) {
                        Logger.getLogger(Frases.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(Frases.class.getName()).log(Level.SEVERE, null, ex);
                }
			}
        }
        else
        {
            System.out.println("Faltan parámetros de entrada");
        }
    }
     public static int getAletorio(int minimo,int maximo)
    {
        int num = (int) (Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }
}
