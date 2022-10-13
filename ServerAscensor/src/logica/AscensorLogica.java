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
        for (int i = 0; i < listaPisos.length; i++) {
            listaPisos[i] = new Pisos();
        }
        pisoActual=0;
    }
    
    public void subirPiso(){
        if(pisoActual<listaPisos.length-1&&carga<cargaMaxima){
            pisoActual++;
        }else{
            //no se mueve el ascensor
        }
    }
    public void bajarPiso(){
        if(pisoActual>0&&carga<cargaMaxima){
            pisoActual--;
        }else{
            //no se mueve
        }
    }
    public void irPiso(int piso){
        if(piso<=listaPisos.length-1&&piso>=0){
            if(pisoActual!=piso){
                if(piso>pisoActual){
                    while(piso>pisoActual){
                        subirPiso();
                        //System.out.println("Subiendo piso");
                    }
                }else{
                    while(piso<pisoActual){
                        bajarPiso();
                        //System.out.println("Bajando piso");
                    }
                }
            }//ya esta en el piso
        }// no existe piso
    }

    public int getPisoActual() {
        return pisoActual;
    }
    
}
