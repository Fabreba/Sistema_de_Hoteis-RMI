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
                        server.adicionarHotel(nomeHotel, credenciais);
                        break;
                    case 2:
                        System.out.println("Digite o nome do hotel:");
                        String nomeDoHotel = scanner.nextLine();
                        System.out.println("Digite a data inicial (Formato: yyyy-MM-dd HH:mm:ss):");
                        String dataInicialStr = scanner.nextLine();
                        System.out.println("Digite a data final (Formato: yyyy-MM-dd HH:mm:ss):");
                        String dataFinalStr = scanner.nextLine();

                        Date dataInicial = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataInicialStr);
                        Date dataFinal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataFinalStr);

                        String disponibilidade = server.consultarDisponibilidade(nomeDoHotel, dataInicial, dataFinal);
                        System.out.println(disponibilidade);
                        break;
                    case 3:
                        System.out.println("Digite o nome do hotel:");
                        String nomeHotelModificar = scanner.nextLine();
                        System.out.println("Digite a data inicial da reserva (Formato: yyyy-MM-dd HH:mm:ss):");
                        String dataInicialModificar = scanner.nextLine();
                        System.out.println("Digite a nova data inicial (Formato: yyyy-MM-dd HH:mm:ss):");
                        String novaDataInicialModificar = scanner.nextLine();
                        System.out.println("Digite a nova data final (Formato: yyyy-MM-dd HH:mm:ss):");
                        String novaDataFinalModificar = scanner.nextLine();

                        Date dataIniMod = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataInicialModificar);
                        Date novaDataIniMod = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .parse(novaDataInicialModificar);
                        Date novaDataFinalMod = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .parse(novaDataFinalModificar);

                        String resultadoModificacao = server.modificarReserva(nomeHotelModificar, dataIniMod,
                                novaDataIniMod, novaDataFinalMod);
                        System.out.println(resultadoModificacao);
                        break;
                    case 4:
                        System.out.println("Digite o nome do hotel:");
                        String nomeHotelRemoverReserva = scanner.nextLine();
                        System.out.println("Digite a data inicial da reserva (Formato: yyyy-MM-dd HH:mm:ss):");
                        String dataInicialRemoverReserva = scanner.nextLine();

                        Date dataIniRemReserva = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .parse(dataInicialRemoverReserva);

                        String resultadoRemocao = server.removerReserva(nomeHotelRemoverReserva, dataIniRemReserva);
                        System.out.println(resultadoRemocao);
                        break;
                    case 5:
                        System.out.println("Digite o nome do hotel que deseja remover:");
                        String nomeHotelRemover = scanner.nextLine();
                        System.out.println("Digite o credenciais do hotel que deseja remover:");
                        String credenciaisHotel = scanner.nextLine();
                        server.removerHotel(nomeHotelRemover, credenciaisHotel);
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

    private void adicionarHotel(String nome, String credenciais) throws RemoteException {
        try {
            List<Quarto> quartos = new ArrayList<>();
            int numeroQuarto = 0;
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
    
    private void removerHotel(String nomeHotel, String credenciais) {
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

    public String modificarReserva(String nomeHotel, Date dataInicial, Date novaDataInicial, Date novaDataFinal) {
        for (Hotel hotel : hoteis) {
            if (hotel.getNome().equals(nomeHotel)) {
                if (hotel.modificarReserva(dataInicial, novaDataInicial, novaDataFinal)) {
                    return "Reserva modificada com sucesso.";
                } else {
                    return "Não foi possível modificar a reserva.";
                }
            }
        }
        return "Hotel não encontrado";
    }

    public String removerReserva(String nomeHotel, Date dataInicial) {
        for (Hotel hotel : hoteis) {
            if (hotel.getNome().equals(nomeHotel)) {
                if (hotel.removerReserva(dataInicial)) {
                    return "Reserva removida com sucesso.";
                } else {
                    return "Não foi possível remover a reserva.";
                }
            }
        }
        return "Hotel não encontrado";
    }

    public String consultarDisponibilidade(String nomeHotel, Date dataInicial, Date dataFinal) {
        for (Hotel hotel : hoteis) {
            if (hotel.getNome().equals(nomeHotel)) {
                if (hotel.verificarDisponibilidade(dataInicial, dataFinal)) {
                    return "Quartos disponíveis para o período selecionado.";
                } else {
                    return "Quartos não disponíveis para o período selecionado.";
                }
            }
        }
        return "Hotel não encontrado";
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
                        if(q.getStatus()){
                            q.setStatus(true);
                            return "Reserva removida com sucesso.";
                        } else {
                            return "O quarto já está disponível.";
                        }
                    }
                }
            }
        }
        return "Quarto ou Hotel não encontrado.";
    }
}