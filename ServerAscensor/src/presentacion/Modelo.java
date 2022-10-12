
package presentacion;

import logica.ServerSocket;

public class Modelo {
    
    private Vista ventana;
    private ServerSocket appServidor;
    
    public ServerSocket getAppServidor(){
        if(appServidor ==null){
            appServidor = new ServerSocket();
        }
        return appServidor;
    }
    
    public void iniciar(){
        getVentana().setSize(800,800);
        getVentana().setVisible(true);
        getVentana().setResizable(false);
        ServerSocket ascensor = new ServerSocket();
        /*ascensor.subirPiso();
        ascensor.subirPiso();
        ascensor.subirPiso();
        ascensor.subirPiso();
        ascensor.subirPiso();
        ascensor.subirPiso();
        ascensor.subirPiso();
        ascensor.subirPiso();
        ascensor.subirPiso();*/
    }
    
    public Vista getVentana(){
        if(ventana==null){
            ventana = new Vista(this);
        }
        return ventana;
    }
}
