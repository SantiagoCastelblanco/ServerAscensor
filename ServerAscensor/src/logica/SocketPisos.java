package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketPisos extends Thread{     
    //Manejadores del socket
    private ServerSocket servidor;
    private Socket cliente;
    private int puerto;
    
    private DataInputStream datosEntrada;
    private DataOutputStream datosSalida;
    
    private boolean activo;
    private int pisoID;
    
    private String mensajeLlegada;
    
    public void setActivo(boolean b){
        activo = b;
    }
    
    //Constructor de la clase, el id hace referencia a cual piso es determinado
    public SocketPisos(int id){
        this.pisoID = id;
        activo = false;
        puerto = 5000+id;
    }
    
    public void escucharCliente() throws IOException{
        
        byte mensaje[] = new byte[100];
        
        //Creart
        if(activo){
            servidor = new ServerSocket(puerto);
            //Espera de conexion
            System.out.println("Socket piso "+ pisoID + " esperando conexion");
            cliente = servidor.accept();
            
            System.out.println("Se ha conexctado "+ cliente.getInetAddress().getHostAddress()+"/n Confirmando piso");
            
            //Ahora con la conexión se debera manejar la confimaciíon de los datos y que el cliente sea el adecuado
            //capturar flujos
            datosEntrada = new DataInputStream ( cliente.getInputStream());
            datosSalida = new DataOutputStream( cliente.getOutputStream());
            
            datosEntrada.read(mensaje);
            mensajeLlegada = new String(mensaje);  
            System.out.println("Recibido " + mensajeLlegada);
            
            //Cerrando para asegurar la nueva conexión 
            datosEntrada.close();
            datosSalida.close();
            cliente.close();
            servidor.close();
        }
    }
    
    
    
    @Override
    public void run(){
        while(activo){
            try {
                escucharCliente();
            } catch (IOException ex) {
                System.out.println("Error en piso "+pisoID);
                Logger.getLogger(SocketPisos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getPisoID() {
        return pisoID;
    }

    public String getMensajeLlegada() {
        return mensajeLlegada;
    }
}
