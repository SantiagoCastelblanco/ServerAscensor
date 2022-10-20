
package presentacion;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.AscensorLogica;
import logica.SocketServidor;

public class Modelo implements Runnable{
    
    private Vista ventana;
    private AscensorLogica appAscensor;
    private SocketServidor servidorSocket;
    
    public SocketServidor getServidorSocket(){
        if(servidorSocket==null){
            servidorSocket = new SocketServidor();
        }
        return servidorSocket;
    }
    
    public AscensorLogica getAppAscensor(){
        if(appAscensor ==null){
            appAscensor = new AscensorLogica();
        }
        return appAscensor;
    }
    
    public void iniciar(){
        getVentana().setSize(800,800);
        getVentana().setVisible(true);
        getVentana().setResizable(false);
        getServidorSocket().setActivo(true);
        getServidorSocket().start();
        run();
    }
    
    public Vista getVentana(){
        if(ventana==null){
            ventana = new Vista(this);
        }
        return ventana;
    }

    @Override
    public void run() {
        boolean activo = true;
        while (activo){
            for(int i=0;i<AscensorLogica.NUM_PISOS;i++){
                int estado = getServidorSocket().getEstadoSocketPiso(i);
                switch(estado){
                    //no existe
                    case -1:
                        if(getAppAscensor().getActivoPiso(i)){
                            getAppAscensor().setActivoPiso(i, false);
                            getVentana().pisoHabilitado(i, false);
                        }
                        break;
                        
                    //Existe, sin notificaciones
                    case 0:
                        if (!getAppAscensor().getActivoPiso(i)){
                            getAppAscensor().setActivoPiso(i, true);
                            getVentana().pisoHabilitado(i, true);
                        }
                        break;
                    //Hay notificaciones
                    case 1:
                        break;
                    //Existe, recien crado
                    case 2:
                        if (getAppAscensor().getActivoPiso(i)){
                            getAppAscensor().setActivoPiso(i, true);
                            getVentana().pisoHabilitado(i, true);
                        }
                        break;
                        
                }
            }
        }
    }
}
