package Administrador.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface AdministradorInt extends Remote
{
      public void numeroClientesActivos(int clientesActivos)throws RemoteException;
      public void numeroMensajesPorMinuto(String mensaje)throws RemoteException;
      public void promedioMensajesPorMinuto(String mensaje)throws RemoteException;
}


