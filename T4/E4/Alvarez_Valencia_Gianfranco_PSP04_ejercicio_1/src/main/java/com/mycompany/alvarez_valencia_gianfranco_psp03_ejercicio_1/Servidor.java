/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.alvarez_valencia_gianfranco_psp03_ejercicio_1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que maneja las conexiones en el servidor
 * @author gianf
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            ServerSocket servidor = new ServerSocket(2000);
            System.out.println("Server up");
            while(true)
            {
                //se recibe una conexión del cliente
                Socket sock = servidor.accept();
                //Gestionar lo que hacer con esa conexion
                Conexiones con = new Conexiones(sock);
                //Iniciar 
                con.start();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
