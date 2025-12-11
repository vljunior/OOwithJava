package servico;

public class IngressoIndisponivelException extends Exception {
    private static final long serialVersionUID = 1L; // serve como um controle

    public IngressoIndisponivelException(String message) {
        super(message);
    }
}
