
package presentacion;

import logica.ServidorAscensor;

public class Modelo {
    
    private Vista ventana;
    private ServidorAscensor appServidor;
    
    public ServidorAscensor getAppServidor(){
        if(appServidor ==null){
            appServidor = new ServidorAscensor();
        }
        return appServidor;
    }
    
    public void iniciar(){
        
        getVentana().setSize(800,800);
        getVentana().setVisible(true);
    }
    
    public Vista getVentana(){
        if(ventana==null){
            ventana = new Vista(this);
        }
        return ventana;
    }
}
