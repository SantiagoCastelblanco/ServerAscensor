package logica;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketConexionCliente extends Thread {
    
    private ServerSocket servidor;
    private Socket cliente;
    private int socketId;
    public final int puerto = 5000;
    
    private boolean activo;
    
    public SocketConexionCliente(int idInput){
        activo = false;
        this.socketId = idInput;
    }
    
    public void activar(boolean b){
        activo = b;
    }
}
