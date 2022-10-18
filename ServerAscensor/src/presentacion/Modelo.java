
package presentacion;

import logica.AscensorLogica;
import logica.AscensorLogica;

public class Modelo {
    
    private Vista ventana;
    private AscensorLogica appAscensor;
    
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
    }
    
    public Vista getVentana(){
        if(ventana==null){
            ventana = new Vista(this);
        }
        return ventana;
    }
}
