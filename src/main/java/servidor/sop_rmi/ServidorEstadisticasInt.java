package servidor.sop_rmi;

import cliente.sop_rmi.ClienteInt;
import java.rmi.Remote;
import java.rmi.RemoteException;
import Administrador.sop_rmi.AdministradorInt;


public interface ServidorEstadisticasInt extends Remote
{
    public boolean registrarAdministrador(AdministradorInt  administrador) throws RemoteException;
    public boolean desconectarAdministrador(AdministradorInt  administrador)throws RemoteException;       
}


