package execoes;

public class valorInvalido extends RuntimeException{

    public valorInvalido(String mensagem){
        super(mensagem);
    }
}
