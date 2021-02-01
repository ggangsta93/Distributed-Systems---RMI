package cliente.sop_rmi;

import cliente.presentacion.GUICliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClienteImpl extends UnicastRemoteObject implements ClienteInt
{
	
    private GUICliente chat;
    
    public ClienteImpl() throws RemoteException
    {
        super();		
    }
    
    public void asignarVista(GUICliente chat){
                this.chat = chat;
    }

    @Override
    public void fijarTexto(String mensaje, String nicknameEmisor) throws RemoteException {
        chat.fijarTexto(mensaje, nicknameEmisor);
    }

    @Override
    public void fijarContactos(String[] clientesConectados) throws RemoteException {
        chat.fijarContactos(clientesConectados);
    }

    @Override
    public void fijarContacto(String clienteNuevo) throws RemoteException {
        chat.fijarContacto(clienteNuevo);
    }

}
