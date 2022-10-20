package logica;

import java.util.Arrays;

public class Pisos {
    
    private boolean pisoActivo;
    private float cargaDelPiso;
    private int[] destinoPersonasSubiendo;
    private int[] destinoPersonasBajando;
    private int totalPersonasEsperando;
    private boolean solicitaSubida;
    private boolean solicitaBajada;
    
    public Pisos(){
        /*
        El funcionamiento de las personas subiendo y bajando representa la candidad de personas que van a cada piso,
        cada piso se determina como una ubicacion en el arreglo de tanto subida como bajada
        */
        destinoPersonasSubiendo = new int[AscensorLogica.NUM_PISOS];
        destinoPersonasBajando = new int[AscensorLogica.NUM_PISOS];
    }
    
    
    public void setActivo(boolean b){
        pisoActivo = b;
    }
    
    public boolean getActivo(){
        return pisoActivo;
    }
    
    public boolean pisoEstaActivo(){
        return pisoActivo;
    }
    
    public void descargaDePersonas(int estadoDescarga, int destinoPersonasNoAceptadas[]){
        if(estadoDescarga == 1){
            if(destinoPersonasNoAceptadas==null){
                Arrays.fill(destinoPersonasSubiendo, 0);
            }else{
                destinoPersonasSubiendo = destinoPersonasNoAceptadas;
            }
        }
        else if(estadoDescarga == -1){
            if(destinoPersonasNoAceptadas==null){
                Arrays.fill(destinoPersonasBajando, 0);
            }
            else{
                destinoPersonasBajando = destinoPersonasNoAceptadas;
            }
        }
        else{
            System.out.println("Error en la solicitud");
        }
    }
    
    public void solicitudPiso(int solicitud, int personas, int destinoPersonas[]){
        totalPersonasEsperando+=personas;
        //-1 es bajada unicamente, 1 es subida unicamente
        if(solicitud ==1){
            solicitaSubida = true;
            destinoPersonasSubiendo = destinoPersonas;
        }
        else if (solicitud ==-1){
            solicitaBajada = true;
            destinoPersonasBajando = destinoPersonas;
        }
        else{
            System.out.println("Solicitud incorrecta");
        }
    }

    public void setSolicitaSubida(boolean solicitaSubida) {
        this.solicitaSubida = solicitaSubida;
    }

    public void setSolicitaBajada(boolean solicitaBajada) {
        this.solicitaBajada = solicitaBajada;
    }

    public int getSolicitudDePiso() {
        //0 es sin solicitud, 1  es solo subida, -1 es solo bajada, 2 es ambas
        int estado;
        if(solicitaBajada&&solicitaSubida){
            estado = 2;
        }
        else if(solicitaSubida){
            estado =1;
        }
        else if(solicitaBajada){
            estado = -1;
        }
        else{
            estado =0;
        }
        return estado;
    }

    public float getCargaDelPiso() {
        return cargaDelPiso;
    }

    public void setCargaDelPiso(float cargaDelPiso) {
        this.cargaDelPiso = cargaDelPiso;
    }
    
    public int[] getDestinoPersonasSubiendo() {
        return destinoPersonasSubiendo;
    }

    public int[] getDestinoPersonasBajando() {
        return destinoPersonasBajando;
    }
    
}
