package Administrador;

import Administrador.presentacion.GUIAdministrador;
import Administrador.presentacion.GUIAdministradorSesion;
import Administrador.sop_rmi.AdministradorImpl;
import Administrador.utilidades.UtilidadesRegistroC;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import servidor.sop_rmi.ServidorEstadisticasInt;
/**
 *
 * @author javier
 */
public class ClienteDeObjetos {
    
        private static ClienteDeObjetos clienteDeObjetos; 
        private static ServidorEstadisticasInt servidor=null;
        private AdministradorImpl objAdmin=null;
        
        private ClienteDeObjetos(){}
        
        public static ClienteDeObjetos getInstancia()
        {
            if(clienteDeObjetos==null){
               clienteDeObjetos=new ClienteDeObjetos();
            }
             return clienteDeObjetos;
        }
        
        public static void main(String[] args) {       
                        if(ClienteDeObjetos.obtenerClienteDeObjetos()==null){
                            ClienteDeObjetos clienteDeObjetos=ClienteDeObjetos.getInstancia();
                            ClienteDeObjetos.obtenerClienteDeObjetos();
                            java.awt.EventQueue.invokeLater(() -> {
                                   new GUIAdministradorSesion(clienteDeObjetos).setVisible(true);
                           });   
                            
                        }else{
                                 JOptionPane.showMessageDialog(null, "oyee!!Es posible que ya exista una sesion activa.");
                        }
                        
        }
        
        
     public boolean obtenerObjetoServidor(int numPuertoRMIRegistry, String direccionIpRMIRegistry){      
                servidor = (ServidorEstadisticasInt) UtilidadesRegistroC.obtenerObjRemoto(numPuertoRMIRegistry,direccionIpRMIRegistry, "ServidorEstadisticas");                      
                return true;
     }
     
     public boolean existeObjetoServidor(){
         boolean existe = false;
         if(servidor!=null){
             existe =true;
         }
         return existe;
     }
     
      public boolean registrarAdministrador(){
          boolean seRegistro=false;
          try {                
                GUIAdministrador nuevoEstadistica=new GUIAdministrador(this);
                objAdmin=new AdministradorImpl(nuevoEstadistica);
                seRegistro = servidor.registrarAdministrador(objAdmin);
            } catch (RemoteException ex) {
                Logger.getLogger(ClienteDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return seRegistro;
        }
      
      public boolean desconectarAdministrador(){
          boolean seDesconecto=false;
          try {
                seDesconecto = servidor.desconectarAdministrador(this.objAdmin);      
            } catch (RemoteException ex) {
                Logger.getLogger(ClienteDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
            }
          return seDesconecto;
      }
      
      public AdministradorImpl obtenerObjAdmin(){
          return this.objAdmin;
      }
      
      public  static ClienteDeObjetos obtenerClienteDeObjetos(){
          if(clienteDeObjetos==null)
              System.out.println("ES NULO");
          else
              System.out.println("NO ES NULO");

          return clienteDeObjetos;
      }
    
}
