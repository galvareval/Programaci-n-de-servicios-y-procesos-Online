/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaquetePrincipal;

import java.io.IOException;
import java.net.Socket;

/**
 * Clse para contrlar las conexiones al servidor
 * @author gianf
 */
public class Conexiones extends Thread
{
    private Socket socket;
    public Conexiones (Socket socCliente) 
    {
        socket = socCliente;
    }
    
    public void run() 
    {
        try
        {
            //Procesar la petición
            ServidorHTTP.procesaPeticion(socket);
            socket.close();
            System.out.println("cliente "+socket.toString()+ " atendido ");//identificar clientes

        } catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
}
