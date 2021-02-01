package servidor;

import Administrador.sop_rmi.AdministradorImpl;
import servidor.utilidades.UtilidadesConsola;
import servidor.utilidades.UtilidadesRMIServidor;
import servidor.sop_rmi.ServidorChatImpl;
import servidor.sop_rmi.ServidorEstadisticasImpl;

public class ServidorDeObjetos {
    public static void main(String args[])
    {         
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";
        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero(); 
        try
        {
                UtilidadesRMIServidor.ArrancarNS(numPuertoRMIRegistry);

                ServidorEstadisticasImpl objRemEstadisticas = new ServidorEstadisticasImpl();  
                UtilidadesRMIServidor.RegistrarObjetoRemoto(objRemEstadisticas, direccionIpRMIRegistry, numPuertoRMIRegistry,"ServidorEstadisticas"); 
            
                ServidorChatImpl objRemoto = new ServidorChatImpl();  
                UtilidadesRMIServidor.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistry, numPuertoRMIRegistry,"ServidorUsuarios");          
                objRemEstadisticas.agregarServidorCallbackImpl(objRemoto);
            
                      
            System.out.println("Objetos remotos registrados, esperando peticiones ...");
        } catch (Exception e)
        {
            System.err.println("No se pudo Arrancar el NS o Registrar el objeto remoto");
        }                
    }
}
