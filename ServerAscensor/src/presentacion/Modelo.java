
package presentacion;

import logica.AscensorLogica;
import logica.SocketPisos;

public class Modelo implements Runnable{
    
    private Vista ventana;
    private AscensorLogica appAscensor;
    private SocketPisos[] socketsPisos;
    
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
        socketsPisos = new SocketPisos[AscensorLogica.NUM_PISOS];
        for(int i=0;i<AscensorLogica.NUM_PISOS;i++){
            socketsPisos[i] = new SocketPisos(i);
            socketsPisos[i].start();
            socketsPisos[i].setActivo(true);
        }
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
            }
        }
    }
}
