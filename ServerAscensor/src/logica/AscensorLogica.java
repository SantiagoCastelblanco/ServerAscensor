package logica;

public class AscensorLogica {

    private Pisos[] listaPisos;
    private int pisoActual;//por ahora lo declaro aqui
    public static final int NUM_PISOS = 10;
    public static final int PISOS_SUBTERRANEOS = 3;
    public static final float cargaMaxima = 1000;//kilos
    private float carga = 0;
    //0 es quieto, 1 es subiendo, -1 bajando
    private int estadoAscensor;

    public AscensorLogica() {
        estadoAscensor = 0;
        listaPisos = new Pisos[NUM_PISOS];
        for (int i = 0; i < listaPisos.length; i++) {
            listaPisos[i] = new Pisos();
        }
        pisoActual = 0;
    }
    
    

    public void setActivoPiso(int piso, boolean b) {
        listaPisos[piso].setActivo(b);
    }

    public boolean getActivoPiso(int i) {
        boolean b = false;
        if (listaPisos[i] != null) {
            b = listaPisos[i].getActivo();
        }
        return b;
    }

    public boolean subirPiso() {
        if (pisoActual < listaPisos.length - 1 && carga < cargaMaxima && estadoAscensor == 1 || estadoAscensor == 0) {
            pisoActual++;
            estadoAscensor = 1;
            return true;
        } else {
            return false;
        }
    }

    public boolean bajarPiso() {
        if (pisoActual > 0 && carga < cargaMaxima && estadoAscensor == -1 || estadoAscensor == 0) {
            estadoAscensor = -1;
            pisoActual--;
            return true;
        } else {
            return false;
        }
    }

    public void agregarPeso(float peso) {
        carga += peso;
    }

    public void quitarPeso(float peso) {
        carga -= peso;
    }

    public float getCarga() {
        return carga;
    }

    //Piso actual manejkado
    public int getPisoActual() {
        return pisoActual;
    }

    public int getEstadoAscensor() {
        return estadoAscensor;
    }
}
