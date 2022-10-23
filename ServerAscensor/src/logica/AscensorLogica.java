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

    public boolean debeSeguirSubiendo() {
        //El modelo ya habra pedido el estado
        boolean b = false;
        //Caso especial para el ultimo piso
        if (listaPisos[AscensorLogica.NUM_PISOS - 1].getSolicitudDePiso() == -1) {
            return pisoActual != AscensorLogica.NUM_PISOS - 1;
        }
        for (int i = 0; i < AscensorLogica.NUM_PISOS; i++) {
            //ignoraremos los pisos anteriores
            if (pisoActual >= i) {
                switch (listaPisos[i].getSolicitudDePiso()) {
                    case 1 ->
                        b = true;
                    case 2 ->
                        b = true;
                }
                if (b) {
                    break;
                }
            }
        }
        return b;
    }

    public boolean debeSeguirBajando() {
        //El modelo ya habra pedido el estado
        boolean b = false;
        //Caso especial para el primer piso
        if (listaPisos[0].getSolicitudDePiso() == 1) {
            return pisoActual != 0;
        }
        for (int i = 0; i < AscensorLogica.NUM_PISOS; i++) {
            //ignoraremos los pisos anteriores
            if (pisoActual <= i) {
                switch (listaPisos[i].getSolicitudDePiso()) {
                    case -1 ->
                        b = true;
                    case 2 ->
                        b = true;
                }
                if (b) {
                    break;
                }
            }
        }
        return b;
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

    public int getEstadoPiso(int i) {
        int estatus = 0;
        estatus = listaPisos[i].getSolicitudDePiso();
        return estatus;
    }
    
    public int getPersonasEsperandoPiso(int i){
        return listaPisos[i].getPersonasEsperando();
    }
    
    public void setSolicitudPiso(int piso,int solicitud, int personas, int destinoPersonas[]){
        listaPisos[piso].solicitudPiso(solicitud, personas, destinoPersonas);
    }
}
