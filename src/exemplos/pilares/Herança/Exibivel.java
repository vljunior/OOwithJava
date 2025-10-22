//2025-2 - Lorenzon
//Resposta Exercício interface - Apostila Herança


public interface Exibivel {
    
    // Atributo constante (prefixo padrão das mensagens)
    public final static String PREFIXO = "MSG:";

    // Método privado para formatar mensagens (somente uso interno da interface)
    private String formatar(String mensagem) {
        return "(" + PREFIXO + " " + mensagem + ")";
    }

    // Método default que utiliza o método privado para exibir mensagem já formatada
    //A forma de fazer utiliza o pré utilizar em algo que poderá ser usado fora:
    //no pacote que estará a interface, por visibilidade default

    //explicitar default senao vira public abstract e não poderá ter corpo
    //lembrar que default era o vazio que tem visibilidade de pacote
    default void exibirMensagem(String mensagem) {
        System.out.println(formatar(mensagem));
    }

    // Métodos abstratos (contrato para classes que implementarem a interface)
    public abstract void mostrarMensagemSimples(String mensagem);

    public abstract void mostrarMensagemDetalhada(String mensagem);
}