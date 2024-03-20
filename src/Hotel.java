import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel {
    private String nome;
    private List<Quarto> quartos;
    private String credenciais;
    private List<Reservas> reservas = new ArrayList<>();

    public Hotel(String nome, String credenciais, List<Quarto> quartos) {
        this.nome = nome;
        this.credenciais = credenciais;
        this.quartos = quartos;
    }

    public String getNome() {
        return nome;
    }
    public List<Quarto> getQuartos() {
        return this.quartos;
    }
    public String getCredenciais(){
        return credenciais;
    }

}