/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.alvarez_valencia_gianfranco_psp03_ejercicio_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para conectar el cliente al servidor
 * @author gianf
 */
public class Cliente {
    //Paráemtros de la conexión
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String servidor = "localhost";
            int puerto = 2000;
            // TODO code application logic here
            //establecer socket para conexión al servidor
            Socket sock = new Socket(servidor, puerto);
            //Entrada de información
            DataInputStream entradaDatos = new DataInputStream(sock.getInputStream());
            //Salida de información
            DataOutputStream salidaDatos = new DataOutputStream(sock.getOutputStream());
            //Para leer la la información que se enviará al servidor
            Scanner entradaCliente = new Scanner(System.in);
            //boleano para gfestionar el bucle
            boolean acierto = false;
            do
            {
                //Leer stream del servidor
                String streamServidor = entradaDatos.readUTF();
                System.out.println(streamServidor);
                //Pasar el número escrito por teclado al serv
                int numTeclado = entradaCliente.nextInt();
                salidaDatos.writeInt(numTeclado);
                //Respuesta evaluada del servidor
                streamServidor = entradaDatos.readUTF();
                System.out.println(streamServidor);
                acierto = entradaDatos.readBoolean();
            }
            while (!acierto);
            //cerrar el socket
            sock.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
