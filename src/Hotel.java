import java.util.Date;
import java.util.List;

public class Hotel {
    private String nome;
    private int qtdQuartos;
    private String credenciais;
    private List<Date> datasDisponiveis;

    public String getCredenciais() {
        return credenciais;
    }

    public void setCredenciais(String credenciais) {
        this.credenciais = credenciais;
    }

    public Hotel(String nome, int qtdQuartos, String credenciais) {
        this.nome = nome;
        this.qtdQuartos = qtdQuartos;
        this.credenciais = credenciais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdQuartos() {
        return qtdQuartos;
    }

    public void setQtdQuartos(int qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
    }

    public void adicionarQuarto(int quantidade){
        this.qtdQuartos += quantidade;
    }
    public void removerQuarto(int quantidade){
        this.qtdQuartos -= quantidade;
    }


    public List<Date> getDatasDisponiveis() {
        return datasDisponiveis;
    }



    public void setDatasDisponiveis(Date dataInicial, Date dataFinal) {
        this.datasDisponiveis.add((dataInicial,dataFinal));
    }
}
