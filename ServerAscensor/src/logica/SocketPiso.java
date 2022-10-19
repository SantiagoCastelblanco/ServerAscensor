package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketPiso extends Thread {

    private final Socket cliente;
    private DataInputStream datosEntrada;
    private DataOutputStream datosSalida;
    private String mensajeLlegada;

    public SocketPiso(Socket clienteEntrante) throws IOException {
        this.cliente = clienteEntrante;
        //capturar flujos
        datosEntrada = new DataInputStream(cliente.getInputStream());
        datosSalida = new DataOutputStream(cliente.getOutputStream());
        System.out.println("Socket creado");
    }

    public void run() {
        
    }

    public DataInputStream getDatosEntrada() {
        return datosEntrada;
    }

    public DataOutputStream getDatosSalida() {
        return datosSalida;
    }
    
    

    public void desconectar() throws IOException {
        //Cerrando para asegurar la nueva conexión 
        datosEntrada.close();
        datosSalida.close();
        cliente.close();
    }
}
