package execoes;

public class semProdutosRegistrados extends RuntimeException {
    
    public semProdutosRegistrados(String mensagem){
        super(mensagem);
    }
}
