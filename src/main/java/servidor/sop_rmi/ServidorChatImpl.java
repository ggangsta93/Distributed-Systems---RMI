package servidor.sop_rmi;

import Administrador.sop_rmi.AdministradorImpl;
import cliente.sop_rmi.ClienteInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.utilidades.UtilidadesArchivo;
import Administrador.sop_rmi.AdministradorInt;

public class ServidorChatImpl extends UnicastRemoteObject implements ServidorChatInt {

    private HashMap <String, ClienteInt> usuarios;
    private AdministradorInt objAdmin=null;
    public ServidorChatImpl() throws RemoteException
    {
        super();
        usuarios= new HashMap <String, ClienteInt> ();
    }
    
    @Override
    public synchronized boolean registrarCliente(ClienteInt cliente, String nickname) throws RemoteException {
        System.out.println("Desde registrarCliente()");
        boolean bandera=false;
        if (!usuarios.containsKey(nickname))
        {
            usuarios.put(nickname, cliente);
            notificarNuevoConectado(nickname);
            actualizarConectados();
            bandera=true;
        }        
        System.out.println("Saliendo de registrarCliente()");
        return bandera;       
    }
   
    @Override
    public void enviarMensaje(String mensaje, String nicknameEmisor)throws RemoteException
    {
        System.out.println("Desde enviarMensaje()");
        notificarUsuarios( mensaje, nicknameEmisor);
        registrarEnArchivo(nicknameEmisor);
        actualizarConectados();
        actualizarPromedioMensajesPorMinuto();
        System.out.println("Saliendo de enviarMensaje()");
    }
    

    @Override
    public boolean desconectarCliente(ClienteInt cliente, String nickname) throws RemoteException {
        System.out.println("Desde desconectarCliente()");
        boolean removido=false;
        if(usuarios.containsKey(nickname)){
            usuarios.remove(nickname);
            removido =true;
            actualizarConectados();
        }    
        System.out.println("Saliendo de desconectarCliente()");
        return removido;
    }
    
        private void notificarUsuarios(String mensaje,  String nicknameEmisor) throws RemoteException {
            for(Map.Entry<String, ClienteInt> entry: usuarios.entrySet())
            {
                if(entry.getValue() != null){
                    entry.getValue().fijarTexto(mensaje, nicknameEmisor);
                }
            }
        }
        
    
    private void actualizarConectados() throws RemoteException {
        for(Map.Entry<String, ClienteInt> entry: usuarios.entrySet())
        {
            entry.getValue().fijarContactos(usuarios.keySet().toArray(new String[usuarios.size()]));
        }
        if(estaConectadoAdmin()){
            objAdmin.numeroClientesActivos(usuarios.size());
        }    
    }
    
    private void actualizarPromedioMensajesPorMinuto() throws RemoteException {
        Calendar c = new GregorianCalendar();
        String nombreArchivo =c.get(Calendar.DATE)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.YEAR);
        if(estaConectadoAdmin()){
            objAdmin.promedioMensajesPorMinuto(UtilidadesArchivo.leerRegistrosEnArchivoParaPromedio(nombreArchivo));
        }    
    }
    
    
    private void notificarNuevoConectado(String nickname) throws RemoteException{
        for(Map.Entry<String, ClienteInt> entry: usuarios.entrySet())
        {
            if(entry.getValue() != null){
                entry.getValue().fijarContacto(nickname);
            }
        }
    }
    
    private void  registrarEnArchivo(String nicknameEmisor){
        Calendar c = new GregorianCalendar();
        String nombreArchivo =c.get(Calendar.DATE)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.YEAR);/*nombre del archivo*/
        String fecha =c.get(Calendar.DATE)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR);
        String hora=c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
        UtilidadesArchivo.escribirRegistroEnArchivo(fecha+" "+hora+" -> "+nicknameEmisor, nombreArchivo);
    }
    
    
    
    /*METODOS PARA ADMINISTRADOR*/
      
    public boolean registrarAdmin(AdministradorInt objAdmin){
       boolean seRegistro = false;
        try {
            if(this.objAdmin==null){
                this.objAdmin = objAdmin;
                seRegistro = true;
            }
            actualizarConectados();
        } catch (RemoteException ex) {
            Logger.getLogger(ServidorChatImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seRegistro;
    }
    
    public boolean desconectarAdmin(AdministradorInt objAdmin){
       boolean seDesconecto=false;
        if(objAdmin.equals(this.objAdmin)){
            this.objAdmin=null;
            seDesconecto=true;
        }
        return seDesconecto;
    }
    
    public boolean estaConectadoAdmin(){
       boolean estaConectado=false;
        if(this.objAdmin!=null){
            estaConectado=true;
        }
        return estaConectado;
    }
    
    public AdministradorInt obtenerAdministrador(){
        return objAdmin;
    }
	
	

}




