package cliente;

import cliente.utilidades.UtilidadesRegistroC;
import cliente.presentacion.GUIClienteSesion;
import cliente.sop_rmi.ClienteImpl;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.sop_rmi.ServidorChatInt;


public class ClienteDeObjetos
{	 	
                 private ServidorChatInt servidor ;
                 private ClienteImpl objetoCli;
;
                 private String nickname="";

	public static void main(String[] args) throws IOException
	{   
                        ClienteDeObjetos nuevo =new ClienteDeObjetos();
                        java.awt.EventQueue.invokeLater(() -> {
                            new GUIClienteSesion(nuevo).setVisible(true);
                        });    
                }    

                public ClienteDeObjetos()  {
                     try {
                         this.objetoCli = new ClienteImpl();
                     } catch (RemoteException ex) {
                         Logger.getLogger(ClienteDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
        
        
                public boolean obtenerObjetoServidor(int numPuertoRMIRegistry, String direccionIpRMIRegistry){  
           
                    servidor = (ServidorChatInt) UtilidadesRegistroC.obtenerObjRemoto(numPuertoRMIRegistry,direccionIpRMIRegistry, "ServidorUsuarios");                      
                     try {
                         objetoCli= new ClienteImpl();
                     } catch (RemoteException ex) {
                            Logger.getLogger(ClienteDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
                     }
        
                    
                    return true;
	}
                
                public boolean registrarCliente(String nickname){
                    boolean seRegistro=false;
                    try {
                         seRegistro = servidor.registrarCliente(objetoCli,nickname);
                         this.nickname = nickname;
                     } catch (RemoteException ex) {
                         Logger.getLogger(ClienteDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
                     }
                      return seRegistro;
                }
                
                public boolean enviarMensaje(String mensaje){
                     try {
                         servidor.enviarMensaje(mensaje,this.nickname);
                     } catch (RemoteException ex) {
                         Logger.getLogger(ClienteDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     return true;
                }
                
                public boolean desconectar(){
                    boolean seDesconecto = false; 
                    try {
                         servidor.desconectarCliente(objetoCli, this.nickname);
                         seDesconecto = true;
                     } catch (RemoteException ex) {
                         Logger.getLogger(ClienteDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
                     }
                    return seDesconecto;
                }
                
                
                public ClienteImpl getClienteImpl() {
                         return this.objetoCli;
                }
                
                public void setClienteImpl(ClienteImpl objCli){
                    this.objetoCli = objCli;
                }

}