package logica;


public class AscensorLogica {
    private Pisos[] listaPisos;
    private int pisoActual;//por ahora lo declaro aqui
    public final int NUM_PISOS = 10;
    public final int PISOS_SUBTERRANEOS = 3;
    public final float cargaMaxima = 1000;//kilos
    private float carga = 0;
    
    public AscensorLogica(){
        listaPisos = new Pisos[NUM_PISOS];
        pisoActual=0;
    }
    
    public void subirPiso(){
        if(pisoActual<listaPisos.length-1&&carga<cargaMaxima){
            pisoActual++;
        }
    }

    public int getPisoActual() {
        return pisoActual;
    }
    
}
