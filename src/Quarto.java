
public class Quarto {
    int numero;
    boolean status;
    
    public Quarto(int numero){
        this.numero = numero;
        this.status = true;
    }

    public Quarto(String nome, boolean status) {
        this.numero = numero;
        this.status = status;
    }

    public int getNumero() {
        return numero;
    }

    public boolean getStatus(){
        return this.status;
    }
    public void setStatus(boolean status){
        this.status = status;
    }


}