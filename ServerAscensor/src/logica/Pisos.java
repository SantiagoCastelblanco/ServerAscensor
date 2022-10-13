package logica;

public class Pisos {
    
    private boolean pisoActivo;
    private String solicitudDePiso;
    private float cargaDelPiso;
    private int personasEsperando;
    private int personas;
    
    public Pisos(){
        
    }
    
    public void activarPiso(){
        pisoActivo = true;
    }
    
    public boolean pisoEstaActivo(){
        return pisoActivo;
    }
    
}
