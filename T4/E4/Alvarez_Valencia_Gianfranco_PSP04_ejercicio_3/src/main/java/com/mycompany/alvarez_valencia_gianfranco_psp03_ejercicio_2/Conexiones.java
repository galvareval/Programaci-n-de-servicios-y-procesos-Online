/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.alvarez_valencia_gianfranco_psp03_ejercicio_2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;
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
            //Solicitar usuario al cliente
            //String para recibir el usuairo
            String user ="";
            salidaDatos.writeUTF("Introduce usuario: ");
            //recibir usuario
            user = entradaDatos.readUTF().trim();
            //Solicitar psw al cliente
            //String para recibir el usuairo
            String psw ="";
            salidaDatos.writeUTF("Introduce password: ");
            //recibir password
            psw = entradaDatos.readUTF().trim();
            //Validación de usuario y password
            if(user.equals("gianfranco") && psw.equals("gianfranco"))//Cso validación ok
            {
                salidaDatos.writeBoolean(true);
                
                boolean salir = false;
                while(!salir)
                {
                    //Pasar solicitud de opción al cliente
                    salidaDatos.writeUTF("Introce un opción dir/mostrar/salir: ");
                    //Obeter la opcion elegida del servidor
                    String opcion = entradaDatos.readUTF().trim();
                    //Depende la opción recibida            
                    switch(opcion)
                    {
                        
                        case "dir"://Dir actual
                            File directorio = new File("./test");//Carpeta test para la prueba
                            ArrayList<String> fichDir;
                            File [] ficheros = directorio.listFiles();//Devuelve los archivos del directorio
                            fichDir = new ArrayList<>();
                            for(int i = 0; i < ficheros.length; i++)//Recorrer el array que contiene el directorio
                            {
                                if(ficheros[i].isFile())
                                {
                                    fichDir.add(ficheros[i].getName());//añadir a la lista si es un fichero
                                }
                            }
                            salidaDatos.writeInt(fichDir.size());//Pasar cuantos ficheros hay al cliente
                            //Pasar los nombres
                            for (String f : fichDir)
                            {
                                salidaDatos.writeUTF(f);//Pasar el nombre al cliente
                            }
                            break;

                        case "mostrar"://Mostrar archivo
                            //Recibir la ruta
                            String ruta = entradaDatos.readUTF();
                            //Crear un objfile con la ruta pasada
                            File fichEvaluar = new File("./test/" + ruta);
                            //comperobaer  si existe
                            if (fichEvaluar.exists()) {
                                //Si existe devolver boleano true al servidor
                                salidaDatos.writeBoolean(true);
                                //Para leer el fichero
                                BufferedReader br = new BufferedReader(new FileReader("./test/" + ruta));
                                //Aux para leer la linea del fichero
                                String linea = "";
                                //Para almacenar todas las lineas del fichero
                                String datosLinea = "";
                                while ((linea = br.readLine()) != null) {
                                    //Formatear el string de salida
                                    datosLinea += linea + "\r\n";
                                }
                                //Pasar el string con el contenido del fichero
                                salidaDatos.writeUTF(datosLinea);
                                //cerrar el bufferreader
                                br.close();

                            } else
                                //Si no existe devuelve boleano falso al saervidor
                                salidaDatos.writeBoolean(false);
                            break;

                        case "salir"://Salir
                            salir = true;
                            break;
                    }
                }
            }
            else
            {
                salidaDatos.writeBoolean(false);//Caso validación erronea
            }
            
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