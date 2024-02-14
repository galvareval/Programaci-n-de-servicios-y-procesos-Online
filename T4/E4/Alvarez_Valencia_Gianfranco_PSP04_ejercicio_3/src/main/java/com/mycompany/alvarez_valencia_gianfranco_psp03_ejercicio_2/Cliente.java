/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.alvarez_valencia_gianfranco_psp03_ejercicio_2;

import com.sun.jmx.snmp.IPAcl.SnmpAcl;
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
            int puerto = 1600;
            // TODO code application logic here
            //establecer socket para conexión al servidor
            Socket sock = new Socket(servidor, puerto);
            //Entrada de información
            DataInputStream entradaDatos = new DataInputStream(sock.getInputStream());
            //Salida de información
            DataOutputStream salidaDatos = new DataOutputStream(sock.getOutputStream());
            //Para leer la la información que se enviará al servidor
            Scanner entradaCliente = new Scanner(System.in);
            entradaCliente.useDelimiter("\n");
            //Usuario a verificar
            boolean exito;
            String user = "";
            String mensajeU = "";
            ////Recibir solicitud de usuario del serv
            mensajeU = entradaDatos.readUTF().trim();
            //Mostrar petición del servidor
            System.out.println(mensajeU);
            //Recibir el usuario tecleado por el cliente
            user = entradaCliente.next();
            //Enviar el usuario al servidor
            salidaDatos.writeUTF(user);
            //Contraseña a verificar
            String psw = "";
            String mensajeP = "";
            ////Recibir solicitud de contraseña del serv
            mensajeP = entradaDatos.readUTF().trim();
            //Mostrar petición del servidor
            System.out.println(mensajeP);
            //Recibir la contraseña tecleada por el cliente
            psw = entradaCliente.next();
            //Enviar el psw al servidor
            salidaDatos.writeUTF(psw);
            //Resultado de la validación
            exito = entradaDatos.readBoolean();
            if(exito)
            {
                //Menu
                boolean salir = false;
                String mensajeSalir ="";
                while(!salir)
                {
                    mensajeSalir = entradaDatos.readUTF().trim();
                    //Recibir soliciut de opcion del menu
                    System.out.println(mensajeSalir);
                    //opcion leegida por el cliente
                    String opcion = entradaCliente.next();
                    //enviar al serv
                    salidaDatos.writeUTF(opcion);
                    System.out.println(opcion);
                    //Depende la opción recibida            
                    switch(opcion)
                    {
                        case "dir"://Dir actual
                            //Recibir tamaño
                            int nFicheros = entradaDatos.readInt();
                            for (int i = 0; i < nFicheros; i++) //Recibir el nombre de los ficheros
                            {
                                String nombreFich = entradaDatos.readUTF().trim();
                                System.out.println(nombreFich);
                            }
                            break;

                        case "mostrar"://Mostrar archivo
                            //Obtener ruta del cliente
                            System.out.println("Nombre del fichero(ruta): ");//probar con test.txt
                            String ruta = entradaCliente.next();
                            //Pasar la ruta al servidor
                            salidaDatos.writeUTF(ruta);
                            //Boleano para recibir la respuesta del servidor de si existe o no el fichero evaluado
                            boolean existeFichero = entradaDatos.readBoolean();
                            if (existeFichero)//En caso de que exista el fichero
                            {
                                //Leer el string que contiene los datos del fichero
                                String datosFcihero = entradaDatos.readUTF();
                                //Mostrar el contenido del fichero por pantalla
                                System.out.println(datosFcihero);
                            } else
                                System.out.println("No existe el fichero pasado!");
                            break;

                        case "salir"://Salir
                            salir = true;
                            break;
                    }
                }
            }
            else
            {
                System.out.println("Validación incorrecta");
            }
            
            
            //cerrar el socket
            sock.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
