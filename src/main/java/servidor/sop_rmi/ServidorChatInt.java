package servidor.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import cliente.sop_rmi.ClienteInt;


public interface ServidorChatInt extends Remote
{
    public boolean registrarCliente(ClienteInt  cliente, String nickname) throws RemoteException;
    public void enviarMensaje(String mensaje, String nicknameEmisors)throws RemoteException;
    public boolean desconectarCliente(ClienteInt cliente, String nickname)throws RemoteException;       
}


