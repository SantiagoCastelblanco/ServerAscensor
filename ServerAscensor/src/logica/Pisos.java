package logica;

public class Pisos {
    
    private boolean pisoActivo;
    private String solicitudDePiso;
    private float cargaDelPiso;
    private int personasEsperando;
    private boolean solicitaSubida;
    private boolean solicitaBajada;
    
    public Pisos(){
        
    }
    
    public void activarPiso(){
        pisoActivo = true;
    }
    
    public boolean pisoEstaActivo(){
        return pisoActivo;
    }
    
    public void solicitudPiso(int solicitud, int personas, int destinoPersonas[]){
        if(solicitud ==1){
            solicitaSubida = true;
        }
        else{
            solicitaBajada = true;
        }
        personasEsperando = personas;
    }

    public void setSolicitaSubida(boolean solicitaSubida) {
        this.solicitaSubida = solicitaSubida;
    }

    public void setSolicitaBajada(boolean solicitaBajada) {
        this.solicitaBajada = solicitaBajada;
    }
    
}
