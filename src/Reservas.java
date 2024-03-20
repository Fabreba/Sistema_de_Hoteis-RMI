import java.util.Date;
import java.util.UUID;

public class Reservas extends Quarto{

    private UUID uuid = null;
    public Reservas(String nome, boolean status) {
        super(nome, status);
    }

    public UUID reservar(){
        if(!status) return uuid;
        
        UUID uuid = UUID.randomUUID();
        String reservaId = uuid.toString();
        System.out.println("Reserva feita com sucesso! ID: " + reservaId);
        return uuid;
    }



    
}