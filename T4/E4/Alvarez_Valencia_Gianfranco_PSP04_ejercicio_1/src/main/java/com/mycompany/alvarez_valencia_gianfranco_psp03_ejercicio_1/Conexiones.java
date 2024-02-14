/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alvarez_valencia_gianfranco_psp03_ejercicio_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase gestiona las conexiones al servidor
 * @author gianf
 */
public class Conexiones extends Thread{
    private Socket socket;
    /**
     * Çonstructor de la clse
     * @param socket socket pasado por el servidor 
     */
    public Conexiones (Socket socket)
    {
        this.socket = socket;
    }
    
    public void run()
    {
        System.out.println("Conexion ok");
        //Entrada y salida de información
        DataInputStream entradaDatos = null;
        DataOutputStream salidaDatos = null;
        try {
            //entrada
            entradaDatos = new DataInputStream(socket.getInputStream());
            //Para enviar la información 
            salidaDatos = new DataOutputStream(socket.getOutputStream());
            //inicializar, recogerá el numero que pase el usuario por teclado
            int numUsuario = 0;
            //generar número secreto entre 1 y 200
            int numSecreto = (int)(Math.random()*(200-1+1)+(1));
            //Mostrar en el serv el número a adivinar
            System.out.println("Número a advinar: " + numSecreto);
            //booleano para acabar el bucle en caso de acertar
            boolean acierto = false;
            do
            { 
            //Mensaje al usuario
            salidaDatos.writeUTF("Adivina el número entre 1 y 200: ");
            //Leer el número pasado por el cliente
            numUsuario = entradaDatos.readInt();
            System.out.println("Numero tecleado: " + numUsuario);
            //Evaluar el número usuario
               
                if (numSecreto == numUsuario)
                {
                    salidaDatos.writeUTF("El número es correcto");
                    acierto = true;
                }
                else
                    if (numSecreto > numUsuario)
                        salidaDatos.writeUTF("El número secreto es mayor");
                    else 
                        salidaDatos.writeUTF("El número secreto es menor");
                //enviar resultado de comprobación
                salidaDatos.writeBoolean(acierto);
            } 
            while(acierto == false);
            //cerrar scoket
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {//Cerrar streams
                entradaDatos.close();
                salidaDatos.close();
            } catch (IOException ex) {
                Logger.getLogger(Conexiones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
