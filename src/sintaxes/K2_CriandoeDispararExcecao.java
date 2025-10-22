package sintaxes;
import utilitarios.*;

/**
 * Programa com o objetivo:
 * - Demonstrar como disparar uma exceção
 * 
 * Autor: Professor Lorenzon, 2025-2
 */

class MinhaExcecao extends Exception { //Herança de Exception

    private final String mensagem = "Número negativo não é permitido!";

    public MinhaExcecao(String mensagem) {
        //Guard Clause
        if (mensagem != null || !mensagem.isBlank()) {
            this.mensagem = mensagem;
        }
        //Happy path, manda pro pai    
        super(mensagem);
    }
}

public class K2_CriandoeDispararExcecao {

    //O método que dá o gatilho deve tratar ou repassar a exceção
    public static int executar() throws MinhaExcecao { //repassando a exceção

        var  numeroUsuario = 0;

        numeroUsuario = Teclado.readInt("Informe um número positivo: ");
        if (numeroUsuario < 0) {
            //Disparando uma minha exceção
            throw new MinhaExcecao(""); //pode passar mensagem customizada
        }

        return numeroUsuario;
    }


    public static void main(String[] args)  
        
            // Não vai compilar se não tratar a exceção:
            throws MinhaExcecao {
       
        var numero  = 0; 
        //Tratando a exceção
        try {            
            numero = executar(); 
        } catch (MinhaExcecao e) {
            Video.mensagemErro("Sistema será abortado.\nMotivo: " + e.getMessage());        
            Video.pausa();
            System.exit(0); //Abortando o sistema
        }

        Video.mensagemOk("Você executou com sucesso o programa!!!. Número: " + numero);

    }       

}
