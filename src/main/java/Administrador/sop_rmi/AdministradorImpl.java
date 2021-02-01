package Administrador.sop_rmi;

import Administrador.presentacion.GUIAdministrador;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import servidor.sop_rmi.ServidorChatImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author javier
 */
public class AdministradorImpl extends UnicastRemoteObject implements AdministradorInt{
    
    private ServidorChatImpl servidorClientes;
    private GUIAdministrador vistaAdministracion;
    
    public AdministradorImpl(GUIAdministrador vistaAdministracion) throws RemoteException{
        super();
        this.vistaAdministracion = vistaAdministracion;
        vistaAdministracion.setVisible(false);
    }
    
    public GUIAdministrador obtenerVista(){
        return this.vistaAdministracion;
    }
    


    @Override
    public void numeroClientesActivos(int clientesActivos){
        vistaAdministracion.escribirUsuariosActivos(clientesActivos);
    }

    @Override
    public void numeroMensajesPorMinuto(String mensaje) {
        vistaAdministracion.escribirMensajesXMinuto(mensaje);
    }

    @Override
    public void promedioMensajesPorMinuto(String mensaje) throws RemoteException {
        vistaAdministracion.escribirPromedioMensajesPorMinuto(mensaje);
    }
    
    
    
}
