package presentacion;

import logica.AscensorLogica;
import logica.SocketServidor;

public class Modelo implements Runnable {

    private Vista ventana;
    private AscensorLogica appAscensor;
    private SocketServidor servidorSocket;
    private long tiempoInicio;
    private long tiempoPasado;

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
        tiempoInicio = System.nanoTime();
        getVentana().setSize(800, 800);
        getVentana().setVisible(true);
        getVentana().setResizable(false);
        getVentana().actualizarLblCapacidad(AscensorLogica.CARGA_MAXIMA);
        getServidorSocket().setActivo(true);
        getServidorSocket().start();
        getVentana().iconoUbicacionAscensor(getAppAscensor().getPisoActual());
        run();
    }

    public Vista getVentana() {
        if (ventana == null) {
            ventana = new Vista(this);
        }
        return ventana;
    }
    
    public int segundosPasadosDesdeInicio(){
        tiempoPasado = System.nanoTime();
        return (int) ((tiempoPasado-tiempoInicio)/1000000000);
    }

    @Override
    public void run() {
        boolean actualizadoAscensor = false;
        boolean activo = true;
        int segundoUltimaAct = 0;
        while (activo) {
            //Actualizamos el ascensor
            if(!actualizadoAscensor){
                int segundosPasados = segundosPasadosDesdeInicio();
                if(segundosPasados%1==0&&segundoUltimaAct != segundosPasados){
                    segundoUltimaAct = segundosPasados;
                    actualizadoAscensor = true;
                    //Representamos la ubicacion actual del ascensor
                    int pisoActualAscensor = getAppAscensor().getPisoActual();
                    getVentana().iconoUbicacionAscensor(pisoActualAscensor);
                    getVentana().actualizarLblCarga(getAppAscensor().getCarga());
                    getVentana().actualizarLblPersonas(getAppAscensor().getPersonasDentro());
                    if(getAppAscensor().dejaPersonasPisoActual()){
                        System.out.println("Dejando personas "+pisoActualAscensor);
                    }
                    if(getAppAscensor().recogePersonasPisoActual()){
                        System.out.println("Regoiendo personas "+pisoActualAscensor);
                    }
                    
                    
                    int estadoAscensor = getAppAscensor().getEstadoAscensor();
                    //establecemos la nueva ubicación del ascensor
                    switch(estadoAscensor){
                        case -1 ->{
                            if(getAppAscensor().debeSeguirBajando()){
                               getAppAscensor().bajarPiso();
                            }
                            else{
                                getAppAscensor().detenerAscensor();
                            }
                        }
                        case 0->{
                            if(getAppAscensor().debeSeguirSubiendo()){
                                getAppAscensor().subirPiso();
                            }
                            else if(getAppAscensor().debeSeguirBajando()){
                                getAppAscensor().bajarPiso();
                            }
                        }
                        case 1->{
                            if(getAppAscensor().debeSeguirSubiendo()){
                               getAppAscensor().subirPiso();
                            }
                            else{
                                getAppAscensor().detenerAscensor();
                            }
                        }
                    }
                }
            }
            else{
                actualizadoAscensor=false;
            }
            /*
            Chequeo de cada piso en socket
            */
            for (int i = 0; i < AscensorLogica.NUM_PISOS; i++) {
                int estado = getServidorSocket().getEstadoSocketPiso(i);
                //Revision de cada socket de piso
                switch (estado) {
                    //No existe - fue eliminjado
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
                        //Cambiamos unicamente si esta en 0
                        if(getAppAscensor().getEstadoPiso(i)==0){
                            getVentana().cambiarEstadoLblPiso(i, true);
                            getVentana().cambiarIconoLblPisos(i, "update");
                        }
                        int estadoEntrada;
                        estadoEntrada = getServidorSocket().getMensajeSocketPiso(i);
                        //Creamos variables locales recibiendo los datos del socket
                        int personasEntrada = getServidorSocket().getPersonasPiso(i);
                        int arregloMensaje[] = getServidorSocket().getArregloMensajeSocket(i);
                        getServidorSocket().confirmarMensajeRecibido(i);
                        getAppAscensor().setSolicitudPiso(i, estadoEntrada, personasEntrada, arregloMensaje);
                        System.out.println("Piso " + i + "\nSolicita: " + getAppAscensor().getEstadoPiso(i) + "\nCon personas: " + getAppAscensor().getPersonasEsperandoPiso(i));
                        for (int j = 0; j < 10; j++) {
                            System.out.println("Piso "+j +" tiene "+arregloMensaje[j]);
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
