
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
    
    public AscensorLogica getAppServidor(){
        if(appAscensor ==null){
            appAscensor = new AscensorLogica();
        }
        return appAscensor;
    }
    
    public void iniciar(){
        getVentana().setSize(800,800);
        getVentana().setVisible(true);
        getVentana().setResizable(false);
        AscensorLogica ascensor = new AscensorLogica();
        servidorSocket = new SocketServidor();
        /*
        servidorSocket.setActivo(true);
        try {
            servidorSocket.escucharCliente();
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
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
            
        }
    }
}
