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
        try {
            servidor = new ServerSocket(puerto);
        } catch (IOException ex) {
            Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escucharCliente() throws IOException {
        System.out.println("Servidor iniciado");
        if (activo) {
            //Espera de conexion
            System.out.println("esperando conexion...");
            servidor.setSoTimeout(50000);
            cliente = servidor.accept();
            SocketPiso sp = new SocketPiso(cliente);
            //logica para agregacion al vector
            System.out.println("Se ha conectado " + cliente.getInetAddress().getHostAddress() + "\n Confirmando piso");
            DataInputStream dataEntrada = new DataInputStream(sp.getDatosEntrada());

            byte mensaje = dataEntrada.readByte();
            int identificador = mensaje;
            System.out.println("Identificador para piso " + identificador + " aceptado");
            if (identificador > 0 && identificador < 10) {
                if (listaPisos[identificador] != null) {

                } else {
                    listaPisos[identificador] = sp;
                    listaPisos[identificador].run();
                }
            } else {
                System.out.println("Identificador de piso incorrecta");
            }

        }
    }

    public int getEstadoSocketPiso(int piso) {
        int estado = -1;
        if (listaPisos[piso] != null) {
            estado = listaPisos[piso].getEstadoSocket();
        }
        return estado;
    }

    public boolean todosSocketsConectados() {
        boolean b = true;
        for (int i = 0; i < AscensorLogica.NUM_PISOS; i++) {
            if(listaPisos[i]!=null){
                if(listaPisos[i].getEstadoSocket()==-1){
                    b = false;
                }
            }
            else{
                b = false;
                break;
            }
        }
        return b;
    }
}
