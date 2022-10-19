package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketServidor {
    //Manejadores del socket
    private ServerSocket servidor;
    private Socket cliente;
    private final int puerto = 5000;
    private SocketPiso listaPisos[];
    private boolean activo;

    public void setActivo(boolean b) {
        activo = b;
    }

    //Constructor de la clase, el id hace referencia a cual piso es determinado
    public SocketServidor() {
        activo = false;
        listaPisos = new SocketPiso[AscensorLogica.NUM_PISOS];
    }

    public void escucharCliente() throws IOException {
        servidor = new ServerSocket(puerto);

        System.out.println("Servidor iniciado");
        while (activo) {
            //Espera de conexion
            System.out.println("esperando conexion...");
            cliente = servidor.accept();
            SocketPiso sp = new SocketPiso(cliente);
            //logica para agregacion al vector
            System.out.println("Se ha conectado " + cliente.getInetAddress().getHostAddress() + "\n Confirmando piso");
            DataInputStream dataEntrada = new DataInputStream(sp.getDatosEntrada());
            
            byte mensaje = dataEntrada.readByte();
            int identificador = mensaje;
            System.out.println("Identificador para piso "+identificador+" aceptado");
            if(listaPisos[identificador]!=null){
                
            }else{
                listaPisos[identificador] = sp;
            }
        }
    }

}
