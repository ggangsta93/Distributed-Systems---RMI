package cliente.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClienteInt extends Remote
{	
        public void fijarTexto(String mensaje, String nicknameEmisor) throws RemoteException;
        public void fijarContactos(String[] clientesConectados) throws RemoteException;
        public void fijarContacto(String clienteNuevo) throws RemoteException;
}


