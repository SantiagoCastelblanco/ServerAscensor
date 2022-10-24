package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketPiso extends Thread {

    private final Socket cliente;
    private final DataInputStream datosEntrada;
    private final DataOutputStream datosSalida;
    private String mensajeLlegada;
    private boolean recienCreado;
    private boolean nuevoMensaje;

    private final byte mensaje[];
    private int arregloLocal[];
    private int estadoMensaje;
    private int cantidadLocal;

    public SocketPiso(Socket clienteEntrante) throws IOException {
        estadoMensaje = 0;
        cantidadLocal = 0;
        arregloLocal = new int[AscensorLogica.NUM_PISOS];
        mensaje = new byte[100];
        this.cliente = clienteEntrante;
        //capturar flujos
        datosEntrada = new DataInputStream(cliente.getInputStream());
        datosSalida = new DataOutputStream(cliente.getOutputStream());
        System.out.println("Socket creado");
        recienCreado = true;
    }

    @Override
    public void run() {
        boolean activo = true;
        while (activo) {
            if (hayNuevoMensaje()) {
                try {
                    leerCantidadPersonas();
                    leerArreglo();
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SocketPiso.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SocketPiso.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int[] getArregloLocal() {
        return arregloLocal;
    }
    
    public void mensajeLeido(){
        nuevoMensaje = false;
    }

    public boolean hayNuevoMensaje() {
        boolean b = false;
        try {
            estadoMensaje = datosEntrada.readByte();
            if (estadoMensaje != 0) {
                b = true;
            }
        } catch (IOException ex) {
            if (ex instanceof java.io.EOFException) {
                try {
                    //El error era esperado
                    estadoMensaje =0;
                    sleep(500);
                } catch (InterruptedException ex1) {
                    Logger.getLogger(SocketPiso.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            else if(ex instanceof java.net.SocketException){
                try {
                    desconectar();
                } catch (IOException ex1) {
                    Logger.getLogger(SocketPiso.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            else {
                Logger.getLogger(SocketPiso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        nuevoMensaje = b;
        return b;

    }

    public void leerCantidadPersonas() throws IOException {
        cantidadLocal = datosEntrada.readByte();
    }

    public int getPersonasLocal() {
        return cantidadLocal;
    }

    public int getEstadoMensaje() {
        return estadoMensaje;
    }

    public void leerArreglo() throws IOException {
        int[] arreglo = new int[datosEntrada.readInt()];
        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = datosEntrada.readInt();
        }
        arregloLocal = arreglo;
    }

    public int obtenerEstadoMensaje() {
        return estadoMensaje;
    }

    public DataInputStream getDatosEntrada() {
        return datosEntrada;
    }

    public DataOutputStream getDatosSalida() {
        return datosSalida;
    }

    public boolean datosNuevos() {
        boolean b = false;
        if (mensaje.length > 2) {
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
        int estado = 0;
        if (recienCreado) {
            estado = 2;
            recienCreado = false;
        } else if (nuevoMensaje) {
            estado = 1;
        }

        return estado;
    }
    
    public void enviarDatosAscensor(int pisoActual, int estadoAscensor){
        try {
            //Confirmamos que es un mensaje
            datosSalida.writeBoolean(true);
            datosSalida.write(pisoActual);
            datosSalida.write(estadoAscensor);
        } catch (IOException ex) {
            Logger.getLogger(SocketPiso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
