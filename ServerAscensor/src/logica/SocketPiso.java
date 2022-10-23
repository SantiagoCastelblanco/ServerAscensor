package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketPiso extends Thread {

    private final Socket cliente;
    private DataInputStream datosEntrada;
    private DataOutputStream datosSalida;
    private String mensajeLlegada;
    private boolean recienCreado;
    
    private byte mensaje[];

    public SocketPiso(Socket clienteEntrante) throws IOException {
        mensaje = new byte[100];
        this.cliente = clienteEntrante;
        //capturar flujos
        datosEntrada = new DataInputStream(cliente.getInputStream());
        datosSalida = new DataOutputStream(cliente.getOutputStream());
        System.out.println("Socket creado");
        recienCreado=true;
    }

    public void run() {
        actualizarMensajeInterno();
    }
    
    public void actualizarMensajeInterno(){
        try {
            datosEntrada.read(mensaje);
        } catch (IOException ex) {
            Logger.getLogger(SocketPiso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DataInputStream getDatosEntrada() {
        return datosEntrada;
    }

    public DataOutputStream getDatosSalida() {
        return datosSalida;
    }
    
    public boolean datosNuevos(){
        boolean b = false;
        if(mensaje.length>2){
            b = true;
        }
        return b;
    }

    public void desconectar() throws IOException {
        //Cerrando para asegurar la nueva conexión 
        datosEntrada.close();
        datosSalida.close();
        cliente.close();
    } 

    public int getEstadoSocket() {
        int estado =0;
        System.out.println("Mensaje "+new String(mensaje));
        if(recienCreado){
            estado = 2;
            recienCreado = false;
        }
        else if(mensaje.length!=0){
            estado=1;
        }
        
        return estado;
    }
}
