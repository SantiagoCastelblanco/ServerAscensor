package logica;

public class Pisos {
    
    private boolean pisoActivo;
    private String solicitudDePiso;
    private float cargaDelPiso;
    private int personasEsperando;
    
    public Pisos(){
        
    }
    
    public void activarPiso(){
        pisoActivo = true;
    }
    
    public boolean pisoEstaActivo(){
        return pisoActivo;
    }

    public void setSolicitudDePiso(String solicitudDePiso) {
        this.solicitudDePiso = solicitudDePiso;
    }
    

    public String getSolicitudDePiso() {
        return solicitudDePiso;
    }
}
