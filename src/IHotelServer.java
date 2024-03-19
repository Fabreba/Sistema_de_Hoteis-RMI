import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IHotelServer extends Remote {
    String adicionarReserva(String hotel, Date dataInicial, Date dataFinal) throws RemoteException;
}
