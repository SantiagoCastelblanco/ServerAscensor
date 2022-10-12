package logica;


public class AscensorLogica {
     private int pisoActual;//por ahora lo declaro aqui
    public final int NUM_PISOS = 10;
    public final int PISOS_SUBTERRANEOS = 3;
    public final float cargaMaxima = 1000;//kilos
    private float carga = 0;
    
    public void subirPiso(){
        //for (int i = 0; i <=NUM_PISOS; i++) {
            if(pisoActual<NUM_PISOS&&carga<=cargaMaxima){
                pisoActual++;
            }else{
                //no se mueve el ascensor
            }
        //}
    }

    public int getPisoActual() {
        return pisoActual;
    }
    
}
