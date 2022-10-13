package logica;


public class ServerLogica {
   private SocketConexionCliente clientes[];
   
   public ServerLogica(){
       clientes = new SocketConexionCliente[AscensorLogica.NUM_PISOS];
       
       for(int i =0;i<AscensorLogica.NUM_PISOS;i++){
           clientes[i] =  new SocketConexionCliente(i);
       }
   }
}
