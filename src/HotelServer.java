import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class HotelServer extends UnicastRemoteObject implements IHotelServer {
    public List<Hotel> hoteis = new ArrayList<>();

    protected HotelServer() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try{
            HotelServer server = new HotelServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Server", server);
            System.out.println("Servidor Hotelaria iniciado");
            while(true){

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


//    protected void adicionarHotel(String nome, int qtdQuartos, String credenciais) throws RemoteException {
//        Hotel hotel = new Hotel(nome,qtdQuartos, credenciais);
//        hoteis.add(hotel);
//    }
//
//    protected void removerHotel(String nome, String credenciais) throws RemoteException {
//        Iterator<Hotel> iterator = hoteis.iterator();
//        while (iterator.hasNext()) {
//            Hotel hotel = iterator.next();
//            if (hotel.getNome().equals(nome) && hotel.getCredenciais().equals(credenciais)) {
//                iterator.remove();
//                System.out.println("Hotel removido: " + hotel.getNome());
//                return;
//            }
//        }
//        System.out.println("Hotel não encontrado ou credenciais incorreta.");
//    }

    @Override
    public void adicionarReserva(String hotel, Date dataInicial, Date dataFinal) throws RemoteException {
        Iterator<Hotel> iterator = hoteis.iterator();
        while (iterator.hasNext()){
            Hotel hotelAtual = iterator.next();
            if(hotelAtual.getNome().equals(hotel)){

            }
        }
    }
}
