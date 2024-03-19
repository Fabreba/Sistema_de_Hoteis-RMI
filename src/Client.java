import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("Escolha");

            IHotelServer server = (IHotelServer) Naming.lookup("rmi://localhost/Server");
            while (true){
                System.out.println("Digite o número para acessar os menus correspondentes:\n1-Consulta de reservar \n2-Reservar um quarto \n3-Modificar uma reserva \n4-Cancelar Reserva");
                int escolha = scanner.nextInt();
                switch (escolha){
                    case 1:
                        
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
