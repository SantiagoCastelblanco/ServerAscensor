package logica;

public class AscensorLogica {

    private Pisos[] listaPisos;
    private int pisoActual;
    public static final int NUM_PISOS = 10;
    public static final int PISOS_SUBTERRANEOS = 3;
    public static final float CARGA_MAXIMA = 1000;//kilos
    public static final float PROMEDIO_PERSONA = 80;
    private float carga = 0;
    //0 es quieto, 1 es subiendo, -1 bajando
    private int estadoAscensor;
    private int personasDentro;
    private int personasBajandoAscensor[];
    private int personasSubiendoAscensor[];

    public AscensorLogica() {
        estadoAscensor = 0;
        pisoActual = 0;
        personasDentro =0;
        listaPisos = new Pisos[NUM_PISOS];
        personasBajandoAscensor = new int[NUM_PISOS];
        personasSubiendoAscensor = new int [NUM_PISOS];
        for (int i = 0; i < listaPisos.length; i++) {
            listaPisos[i] = new Pisos();
        }
        pisoActual = 0;
    }
    
    public boolean dejaPersonasPisoActual(){
        boolean b = false;
        if(estadoAscensor==1){
            int personas = personasSubiendoAscensor[pisoActual];
            if(personas!=0){
                personasDentro -=personas;
                personasSubiendoAscensor[pisoActual]=0;
                carga = carga-(personas*PROMEDIO_PERSONA);
                System.out.println("Dejadas personas subiendo"+pisoActual);
                b = true;
            }
        }
        else if(estadoAscensor==-1){
            int personas = personasBajandoAscensor[pisoActual];
            if(personas!=0){
                personasDentro-=personas;
                carga = carga-(personas*PROMEDIO_PERSONA);
                personasBajandoAscensor[pisoActual] =0;
                System.out.println("Dejadas personas bajando "+pisoActual);
                b = true;
            }
        }
        return b;
    }
    
    public boolean recogePersonasPisoActual(){
        boolean b = false;
        int solicitudPiso = listaPisos[pisoActual].getSolicitudDePiso();
        if (solicitudPiso!=0) {
            if(estadoAscensor==-1||(estadoAscensor==0&&solicitudPiso==-1)){
                int[] arregloRechazados = listaPisos[pisoActual].getDestinoPersonasBajando();
                for (int i = 0; i < NUM_PISOS; i++) {
                    if(carga+PROMEDIO_PERSONA<CARGA_MAXIMA){
                        b = true;
                        int personas = arregloRechazados[i];
                        for (int j = 1; j <= personas; j++) {
                            carga+=PROMEDIO_PERSONA;
                            personasDentro++;
                            arregloRechazados[i]--;
                            personasBajandoAscensor[i]++;
                        }
                    }
                    else{
                        break;
                    }
                }
                listaPisos[pisoActual].descargaDePersonas(-1, arregloRechazados);
            }
            else if(estadoAscensor==1||(estadoAscensor==0&&solicitudPiso==1)){
                int[] arregloRechazados = listaPisos[pisoActual].getDestinoPersonasSubiendo();
                for (int i=NUM_PISOS-1;i>=0;i--) {
                    if(carga+PROMEDIO_PERSONA<CARGA_MAXIMA){
                        b = true;
                        int personas = arregloRechazados[i];
                        for (int j = 1; j <= personas; j++) {
                            carga += PROMEDIO_PERSONA;
                            personasDentro++;
                            arregloRechazados[i]--;
                            personasSubiendoAscensor[i]++;
                        }
                    }
                    else{
                        break;
                    }
                }
                listaPisos[pisoActual].descargaDePersonas(1, arregloRechazados);
            }
        }
        return b;
    }

    public boolean debeSeguirSubiendo() {
        //El modelo ya habra pedido el estado
        boolean b = false;
        //Caso especial para el ultimo piso
        if (listaPisos[AscensorLogica.NUM_PISOS - 1].getSolicitudDePiso() == -1&&pisoActual!=NUM_PISOS-1) {
            return true;
        }
        else if(pisoActual==NUM_PISOS-1){
            return false;
        }
        for (int i = 0; i < AscensorLogica.NUM_PISOS; i++) {
            //ignoraremos los pisos anteriores
            if (pisoActual < i) {
                switch (listaPisos[i].getSolicitudDePiso()) {
                    case -1->
                        b = true;
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
        
        //Manejo por las solicitudes
        for (int i = 0; i < NUM_PISOS; i++) {
            if(personasSubiendoAscensor[i]!=0){
                b =true;
                break;
            }
        }
        return b;
    }

    public boolean debeSeguirBajando() {
        //El modelo ya habra pedido el estado
        boolean b = false;
        //Caso especial para el primer piso
        if (listaPisos[0].getSolicitudDePiso() == 1&&pisoActual==0) {
            return true;
        }
        else if(pisoActual==0){
            return false;
        }
        for (int i = 0; i < AscensorLogica.NUM_PISOS; i++) {
            //ignoraremos los pisos anteriores
            if (i < pisoActual) {
                switch (listaPisos[i].getSolicitudDePiso()) {
                    case -1 ->
                        b = true;
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
        
        //Manejo por las solicitudes
        for (int i = 0; i < NUM_PISOS; i++) {
            if(personasBajandoAscensor[i]!=0){
                b =true;
                break;
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
        if (pisoActual < listaPisos.length - 1 && carga < CARGA_MAXIMA && estadoAscensor == 1 || estadoAscensor == 0) {
            pisoActual++;
            estadoAscensor = 1;
            return true;
        } else {
            return false;
        }
    }

    public boolean bajarPiso() {
        if (pisoActual > 0 && carga < CARGA_MAXIMA && estadoAscensor == -1 || estadoAscensor == 0) {
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

    public int getPersonasEsperandoPiso(int i) {
        return listaPisos[i].getPersonasEsperando();
    }

    public void setSolicitudPiso(int piso, int solicitud, int personas, int destinoPersonas[]) {
        listaPisos[piso].solicitudPiso(solicitud, personas, destinoPersonas);
    }

    public void detenerAscensor() {
        estadoAscensor = 0;
    }

    public int getPersonasDentro() {
        return this.personasDentro;
    }
}
