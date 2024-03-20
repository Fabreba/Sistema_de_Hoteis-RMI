import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IHotelServer extends Remote {
    String adicionarReserva(String hotel, int numeroHotel) throws RemoteException;
    
    // String consultarDisponibilidade(String nomeHotel, Date dataInicial, Date dataFinal) throws RemoteException;

    String modificarReserva(String nomeHotel, int quartoAtual, int novoQuarto) throws RemoteException;

    String removerReserva(String nomeDoHotel, int numeroQuarto) throws RemoteException;
}
