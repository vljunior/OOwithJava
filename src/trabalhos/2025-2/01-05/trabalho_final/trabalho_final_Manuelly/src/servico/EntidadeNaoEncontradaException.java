package servico;

public class EntidadeNaoEncontradaException extends Exception {
    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
