import java.util.Date;

public class Reservas {
    private String hotel;
    private Date dataInicial;
    private Date dataFinal;

    public Reservas(String hotel, Date dataInicial, Date dataFinal) {
        this.hotel = hotel;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
}