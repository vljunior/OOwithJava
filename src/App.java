/* Entry Point do projeto: App com método public static void main(String[] args)
 * Lorenzon 2025-2
 */

import utilitarios.*;

public class App {    

    public static void main(String[] args) throws Exception {        

        var zero = 0; //omitindo tipos pra var locais
        int valor = zero;

        Video.limparTela();  
        Video.cabecalho("Teste de aplicação!");      

        valor = Teclado.readInt("Informe um valor:");  
        
        Video.barraProgresso (10, 100);
        Video.mensagem("Foi digitado: " + valor);    
       
        Video.escreverLento("Programa vai ser encerrado!", 100);
        Video.pausa();        

        System.exit(0); //isso em qualquer lugar fecha a aplicação
    }
}
