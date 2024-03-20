import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Client {
    private static IHotelServer server;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            server = (IHotelServer) registry.lookup("Server");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Bem-vindo ao sistema de reservas de hotéis!");

            while (true) {
                System.out.println("\nOpções: 1 - Consultar Disponibilidade, 2 - Fazer Reserva, 3 - Modificar Reserva, 4 - Cancelar Reserva, 5 - Sair");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        consultarDisponibilidade(scanner);
                        break;
                    case 2:
                        fazerReserva(scanner);
                        break;
                    case 3:
                        modificarReserva(scanner);
                        break;
                    case 4:
                        cancelarReserva(scanner);
                        break;
                    case 5:
                        System.out.println("Saindo do sistema...");
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void consultarDisponibilidade(Scanner scanner) throws Exception {
        System.out.println("Digite o nome do hotel:");
        String nomeDoHotel = scanner.nextLine();
        System.out.println("Digite a data inicial (Formato: yyyy-MM-dd HH:mm:ss):");
        String dataInicialStr = scanner.nextLine();
        System.out.println("Digite a data final (Formato: yyyy-MM-dd HH:mm:ss):");
        String dataFinalStr = scanner.nextLine();

        Date dataInicial = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataInicialStr);
        Date dataFinal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataFinalStr);

        //String disponibilidade = server.consultarDisponibilidade(nomeDoHotel, dataInicial, dataFinal);
        //System.out.println(disponibilidade);
    }

    private static void fazerReserva(Scanner scanner) throws Exception {
        System.out.println("Digite o nome do hotel:");
        String nomeDoHotel = scanner.nextLine();
        System.out.println("Digite o número do quarto desejado: ");
        int numeroQuarto = scanner.nextInt();
        String resultado = server.adicionarReserva(nomeDoHotel, numeroQuarto);
        System.out.println(resultado);
    }

    private static void modificarReserva(Scanner scanner) throws Exception {
        System.out.println("Digite o nome do hotel:");
        String nomeDoHotel = scanner.nextLine();
        System.out.println("Digite o número do quarto atual da reserva: ");
        int quartoAtual = scanner.nextInt();
        System.out.println("Digite o número do novo quarto da reserva:");
        int quartoNovo = scanner.nextInt();

        String resultado = server.modificarReserva(nomeDoHotel, quartoAtual, quartoNovo);
        System.out.println(resultado);
    }

    private static void cancelarReserva(Scanner scanner) throws Exception {
        System.out.println("Digite o nome do hotel:");
        String nomeDoHotel = scanner.nextLine();
        System.out.println("Digite o número do Quarto");
        int numeroQuarto = scanner.nextInt();
        String resultado = server.removerReserva(nomeDoHotel, numeroQuarto);
        System.out.println(resultado);
    }
}
