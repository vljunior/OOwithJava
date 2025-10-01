package sintaxes;
import utilitarios.*;

/**
 * Programa com o objetivo:
 * - Demonstrar como disparar uma exceção
 * 
 * Autor: Professor Lorenzon, 2025-2
 */

public class K_DispararExcecao {

    //O método que dá o gatilho deve tratar ou repassar a exceção
    public static int executar() throws Exception { //repassando a exceção

        var  numeroUsuario = 0;

        numeroUsuario = Teclado.readInt("Informe um número positivo: ");
        if (numeroUsuario < 0) {
            //Disparando uma exceção
            throw new Exception("Número negativo não é permitido");
        }

        return numeroUsuario;
    }


    public static void main(String[] args) throws Exception {
       
        var numero  = 0; 
        //Tratando a exceção
        try {            
            numero = executar(); 
        } catch (Exception e) {
            Video.mensagemErro("Sistema será abortado.\nMotivo: " + e.getMessage());        
            Video.pausa();
            System.exit(0); //Abortando o sistema
        }
        
        Video.mensagemOk("Você executou com sucesso o programa!!!. Número: " + numero);

    }       

}
