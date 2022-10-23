package presentacion;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.AscensorLogica;
import logica.SocketServidor;

public class Modelo implements Runnable {

    private Vista ventana;
    private AscensorLogica appAscensor;
    private SocketServidor servidorSocket;

    public SocketServidor getServidorSocket() {
        if (servidorSocket == null) {
            servidorSocket = new SocketServidor();
        }
        return servidorSocket;
    }

    public AscensorLogica getAppAscensor() {
        if (appAscensor == null) {
            appAscensor = new AscensorLogica();
        }
        return appAscensor;
    }

    public void iniciar() {
        getVentana().setSize(800, 800);
        getVentana().setVisible(true);
        getVentana().setResizable(false);
        getServidorSocket().setActivo(true);
        getServidorSocket().start();
        run();
    }

    public Vista getVentana() {
        if (ventana == null) {
            ventana = new Vista(this);
        }
        return ventana;
    }

    @Override
    public void run() {
        boolean activo = true;
        while (activo) {
            /*
            Chequeo de cada piso
            */
            int pisoActualAscensor = getAppAscensor().getPisoActual();
            for (int i = 0; i < AscensorLogica.NUM_PISOS; i++) {
                int estado = getServidorSocket().getEstadoSocketPiso(i);
                //Revision de cada socket de piso
                switch (estado) {
                    //No existe
                    case -1 -> {
                        if (getAppAscensor().getActivoPiso(i)) {
                            getAppAscensor().setActivoPiso(i, false);
                            getVentana().cambiarEstadoLblPiso(i, false);
                            getVentana().cambiarIconoLblPisos(i, "inactive");
                        }
                    }
                    //Existe sin modificaciones
                    case 0 -> {
                        if (!getAppAscensor().getActivoPiso(i)) {
                            getAppAscensor().setActivoPiso(i, true);
                            getVentana().cambiarEstadoLblPiso(i, true); 
                        }
                    }
                    //Existe con datos
                    case 1 -> {
                        if(getAppAscensor().getEstadoPiso(i)==0){
                            
                            int estadoEntrada = getServidorSocket().getEstadoSocketPiso(i);
                            int personasEntrada = getServidorSocket().getPersonasPiso(i);
                            int arregloMensaje[] = getServidorSocket().getArregloMensajeSocket(i);
                            getAppAscensor().setSolicitudPiso(i, estadoEntrada, personasEntrada, arregloMensaje);
                            System.out.println("Piso "+i+"\nSolicita: "+getAppAscensor().getEstadoPiso(i)+"\nCon personas: "+getAppAscensor().getPersonasEsperandoPiso(i));
                            getVentana().cambiarEstadoLblPiso(i, true);
                            getVentana().cambiarIconoLblPisos(i, "update");
                        }
                        
                    }
                    //Existe recien crado
                    case 2 -> {
                        if (!getAppAscensor().getActivoPiso(i)) {
                            getAppAscensor().setActivoPiso(i, true);
                            getVentana().cambiarEstadoLblPiso(i, true);
                            getVentana().cambiarIconoLblPisos(i, "active");
                        }
                    }
                }
            }
        }
    }
}
