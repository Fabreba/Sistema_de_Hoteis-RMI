import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IHotelServer extends Remote {
    String adicionarReserva(String hotel, int numeroHotel) throws RemoteException;
    
    // String consultarDisponibilidade(String nomeHotel, Date dataInicial, Date dataFinal) throws RemoteException;

    // String modificarReserva(String nomeHotel, Date dataInicial, Date novaDataInicial, Date novaDataFinal);

    // String removerReserva(String nomeDoHotel, Date dataInicial);
}
