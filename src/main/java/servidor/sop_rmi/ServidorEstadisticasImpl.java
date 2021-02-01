/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.utilidades.UtilidadesArchivo;
import Administrador.sop_rmi.AdministradorInt;

/**
 *
 * @author javier
 */
public class ServidorEstadisticasImpl extends UnicastRemoteObject implements ServidorEstadisticasInt{

    private ServidorChatImpl objServidorCallbkImpl=null;
    private  hiloMensajePorMinuto hilo=null;
    
    public ServidorEstadisticasImpl() throws RemoteException {
        super();
    }    
    
   public void agregarServidorCallbackImpl(ServidorChatImpl objServidorCallbkImpl){
        this.objServidorCallbkImpl = objServidorCallbkImpl;
    }

    @Override
    public boolean registrarAdministrador(AdministradorInt administrador) throws RemoteException {
        boolean seRegistro =false;
        seRegistro = this.objServidorCallbkImpl.registrarAdmin(administrador);
        if(this.objServidorCallbkImpl.estaConectadoAdmin()){
            hilo=new hiloMensajePorMinuto(objServidorCallbkImpl);
            hilo.start();            
        }
        return seRegistro;        
    }
    
    @Override
    public boolean desconectarAdministrador(AdministradorInt administrador) throws RemoteException {
        boolean seDesconecto =false;
        seDesconecto = objServidorCallbkImpl.desconectarAdmin(administrador);
        return seDesconecto;
    }
       
}


class hiloMensajePorMinuto extends Thread
{
    private ServidorChatImpl objServidorCallbkImpl;

    public hiloMensajePorMinuto(ServidorChatImpl objServidorCallbkImpl){
        this.objServidorCallbkImpl = objServidorCallbkImpl;
    }
    
   @Override
   public void run()
   {
       Calendar c;
       String fecha;
       
       while(true){              
               c = new GregorianCalendar();
               fecha =c.get(Calendar.DATE)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.YEAR);
               numeroMensajesPorMinuto(UtilidadesArchivo.leerRegistrosEnArchivo(fecha));
       }
   }
   
   public void numeroMensajesPorMinuto(String mensaje){
        try {
            objServidorCallbkImpl.obtenerAdministrador().numeroMensajesPorMinuto(mensaje);
        } catch (RemoteException ex) {
            Logger.getLogger(hiloMensajePorMinuto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
};
