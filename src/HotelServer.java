import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;

public class HotelServer extends UnicastRemoteObject implements IHotelServer {
    private List<Hotel> hoteis = new ArrayList<>();

    protected HotelServer() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            HotelServer server = new HotelServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Server", server);
            System.out.println("Servidor Hotelaria iniciado");

            
            
            
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println(
                        "Opções: 1 - Adicionar Hotel, 2 - Consultar Disponibilidade, 3 - Modificar Reserva, 4 - Remover Reserva, 5 - Remover Hotel, 6 - Sair");
                int escolha = scanner.nextInt();
                scanner.nextLine();

                switch (escolha) {
                    case 1:
                        System.out.println("Digite o nome do hotel:");
                        String nomeHotel = scanner.nextLine();
                        System.out.println("Digite as credenciais do hotel:");
                        String credenciais = scanner.nextLine();
                        server.adicionarHotelAdmin(nomeHotel, credenciais);
                        break;
                    case 2:
                        System.out.println("Digite o nome do hotel:");
                        String nomeDoHotel = scanner.nextLine();

                        String disponibilidade = server.consultarDisponibilidade(nomeDoHotel);
                        System.out.println(disponibilidade);
                        break;
                    case 3:
                        System.out.println("Digite o nome do hotel:");
                        String nome = scanner.nextLine();
                        System.out.println("Digite o número do quarto atual da reserva: ");
                        int quartoAtual = scanner.nextInt();
                        System.out.println("Digite o número do novo quarto da reserva:");
                        int quartoNovo = scanner.nextInt();

                        String resultado = server.modificarReserva(nome, quartoAtual, quartoNovo);
                        System.out.println(resultado);
                        break;
                    case 4:
                        System.out.println("Digite o nome do hotel:");
                        String nomeHotelRemoverReserva = scanner.nextLine();
                        System.out.println("Digiteo número do quarto");
                        int numeroQuarto = scanner.nextInt();
                        String resultadoRemocao = server.removerReservaAdmin(nomeHotelRemoverReserva, numeroQuarto);
                        System.out.println(resultadoRemocao);
                        break;
                    case 5:
                        System.out.println("Digite o nome do hotel que deseja remover:");
                        String nomeHotelRemover = scanner.nextLine();
                        System.out.println("Digite o credenciais do hotel que deseja remover:");
                        String credenciaisHotel = scanner.nextLine();
                        server.removerHotelAdmin(nomeHotelRemover, credenciaisHotel);
                        break;
                    case 6:
                        System.out.println("Encerrando servidor...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String consultarDisponibilidade(String nomeDoHotel) {
        for (Hotel hotel : hoteis) {
            if (hotel.getNome().equals(nomeDoHotel)) {
                for (Quarto quarto : hotel.getQuartos()) {
                    if (quarto.getStatus()) {
                        System.out.println("Número do quarto não reservado: " + quarto.getNumero());
                        break;
                    }
                }
            }
        }
        return "Hotel não encontrado";
    }

    private void adicionarHotelAdmin(String nome, String credenciais) throws RemoteException {
        try {
            List<Quarto> quartos = new ArrayList<>();
            int numeroQuarto;
            System.out.println("Agora preciso que você adicione os quartos do hotel.");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Digite o número do quarto (-1 para sair):");
                numeroQuarto = scanner.nextInt();
                if (numeroQuarto == -1)
                    break;
                Quarto quarto = new Quarto(numeroQuarto);
                quartos.add(quarto);
            }
            scanner.nextLine();
            Hotel hotel = new Hotel(nome, credenciais, quartos);
            hoteis.add(hotel);
            System.out.println("Hotel adicionado: " + nome);
            for (Quarto quarto : quartos) {
                System.out.println("Quarto adicionado: " + quarto.getNumero());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void removerHotelAdmin(String nomeHotel, String credenciais) {
        Iterator<Hotel> iterator = hoteis.iterator();
        while (iterator.hasNext()) {
            Hotel hotel = iterator.next();
            if (hotel.getNome().equals(nomeHotel) && hotel.getCredenciais().equals(credenciais)) {
                iterator.remove();
                System.out.println("Hotel removido: " + nomeHotel);
                return;
            }
        }
        System.out.println("Hotel não encontrado");
    }

    private String modificarReservaAdmin(String nomeHotel, int quartoAtual, int novoQuarto) {
        for (Hotel hotel : hoteis) {
            if (hotel.getNome().equals(nomeHotel)) {
                for (Quarto quarto : hotel.getQuartos()) {
                    if (quarto.getNumero() == quartoAtual) {
                        if (!quarto.getStatus()) {
                            for (Quarto novo : hotel.getQuartos()) {
                                if (novo.getNumero() == novoQuarto) {
                                    if (novo.getStatus()) {
                                        quarto.setStatus(true);
                                        novo.setStatus(false);
                                        return "Reserva modificada com sucesso.";
                                    } else {
                                        return "O novo quarto já está reservado.";
                                    }
                                }
                            }
                            return "Novo quarto não encontrado.";
                        } else {
                            return "O quarto atual não está reservado.";
                        }
                    }
                }
                return "Quarto atual não encontrado.";
            }
        }
        return "Hotel não encontrado.";
    }

    private String removerReservaAdmin(String nomeDoHotel, int numeroQuarto){
        for(Hotel h : hoteis){
            if(h.getNome().equals(nomeDoHotel)){
                for (var q : h.getQuartos()){
                    if(q.getNumero() == numeroQuarto){
                        if(!q.getStatus()){
                            q.setStatus(true);
                            return "Reserva removida com sucesso.";
                        } else {
                            return "Nenhuma reserva a ser removida";
                        }
                    }
                }
            }
        }
        return "Hotel ou Reserva não encontrado.";
    }


    @Override
    public String adicionarReserva(String hotel, int numeroQuarto) throws RemoteException {
        for(Hotel h : hoteis){
            if(h.getNome().equals(hotel)){
                for (var q : h.getQuartos()){
                    if(q.getNumero() == numeroQuarto){
                        if(q.getStatus()){
                            q.setStatus(false);
                            return "Reserva adicionada com sucesso.";
                        } else {
                            return "Não foi possível adicionar a reserva.";
                        }
                    }
                }

            }
        }
        return "Quarto ou Hotel não encontrado.";
    }
    
    @Override
    public String modificarReserva(String nomeHotel, int quartoAtual, int novoQuarto) {
        for (Hotel hotel : hoteis) {
            if (hotel.getNome().equals(nomeHotel)) {
                for (Quarto quarto : hotel.getQuartos()) {
                    if (quarto.getNumero() == quartoAtual) {
                        if (!quarto.getStatus()) {
                            for (Quarto novo : hotel.getQuartos()) {
                                if (novo.getNumero() == novoQuarto) {
                                    if (novo.getStatus()) {
                                        quarto.setStatus(true);
                                        novo.setStatus(false);
                                        return "Reserva modificada com sucesso.";
                                    } else {
                                        return "O novo quarto já está reservado.";
                                    }
                                }
                            }
                            return "Novo quarto não encontrado.";
                        } else {
                            return "O quarto atual não está reservado.";
                        }
                    }
                }
                return "Quarto atual não encontrado.";
            }
        }
        return "Hotel não encontrado.";
    }

    @Override
    public String removerReserva(String nomeDoHotel, int numeroQuarto){
        for(Hotel h : hoteis){
            if(h.getNome().equals(nomeDoHotel)){
                for (var q : h.getQuartos()){
                    if(q.getNumero() == numeroQuarto){
                        if(!q.getStatus()){
                            q.setStatus(true);
                            return "Reserva removida com sucesso.";
                        } else {
                            return "Nenhuma reserva a ser removida";
                        }
                    }
                }
            }
        }
        return "Hotel ou Reserva não encontrado.";
    }
}