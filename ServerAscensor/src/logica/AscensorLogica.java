package logica;

public class AscensorLogica{
    
    private Pisos[] listaPisos;
    private int pisoActual;//por ahora lo declaro aqui
    public static final int NUM_PISOS = 10;
    public static final int PISOS_SUBTERRANEOS = 3;
    public static final float cargaMaxima = 1000;//kilos
    private float carga = 0;
    
     //0 es quieto, 1 es subiendo, -1 bajando
    private int estado;
    
    public AscensorLogica(){
        estado =0;
        listaPisos = new Pisos[NUM_PISOS];
        for (int i = 0; i < listaPisos.length; i++) {
            listaPisos[i] = new Pisos();
        }
        pisoActual=0;
    }
    
    public boolean subirPiso(){
        if(pisoActual<listaPisos.length-1&&carga<cargaMaxima&&estado==1||estado==0){
            pisoActual++;
            return true;
        }else{
            return false;
        }
    }
    public boolean bajarPiso(){
        if(pisoActual>0&&carga<cargaMaxima&&estado==-1||estado==0){
            estado=-1;
            pisoActual--;
            return true;
        }else{
            return false;
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
    
    public void agregarPeso(float peso){
        carga+=peso;
    }
    
    public void quitarPeso(float peso){
        carga-=peso;
    }
    
    public boolean priorizar(){
        return false;
    }
    
    public float getCarga() {
        return carga;
    }

    public int getPisoActual() {
        return pisoActual;
    }
}
