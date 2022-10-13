
package presentacion;

import logica.AscensorLogica;
import logica.ServerLogica;

public class Modelo {
    
    private Vista ventana;
    private ServerLogica appServidor;
    
    public ServerLogica getAppServidor(){
        if(appServidor ==null){
            appServidor = new ServerLogica();
        }
        return appServidor;
    }
    
    public void iniciar(){
        getVentana().setSize(800,800);
        getVentana().setVisible(true);
        getVentana().setResizable(false);
        AscensorLogica ascensor = new AscensorLogica();
        System.out.println(ascensor.getPisoActual());
        ascensor.irPiso(4);
        
    }
    
    public Vista getVentana(){
        if(ventana==null){
            ventana = new Vista(this);
        }
        return ventana;
    }
}
