import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel {
    private String nome;
    private int qtdQuartos;
    private String credenciais;
    private List<Reservas> reservas;

    public Hotel(String nome, int qtdQuartos, String credenciais) {
        this.nome = nome;
        this.qtdQuartos = qtdQuartos;
        this.credenciais = credenciais;
        this.reservas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public boolean verificarDisponibilidade(Date dataInicial, Date dataFinal) {
        // Lógica para verificar a disponibilidade de quartos
        return true; // Simplesmente retornando true para fins de exemplo
    }

    public boolean modificarReserva(Date dataInicial, Date novaDataInicial, Date novaDataFinal) {
        for (Reservas reserva : reservas) {
            if (reserva.getDataInicial().equals(dataInicial)) {
                reserva.setDataInicial(novaDataInicial);
                reserva.setDataFinal(novaDataFinal);
                return true;
            }
        }
        return false;
    }

    public boolean removerReserva(Date dataInicial) {
        for (Reservas reserva : reservas) {
            if (reserva.getDataInicial().equals(dataInicial)) {
                reservas.remove(reserva);
                return true;
            }
        }
        return false;
    }
}