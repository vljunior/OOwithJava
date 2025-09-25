/**
 * Programa com o objetivo:
 * - Apresentar a classe Math e uso de Math.random()
 * - Demonstrar manipulação de objetos String
 * - Utilizar casting explícito de double para int
 * 
 * Observação:
 * - Math e String pertencem ao pacote java.lang, importado automaticamente
 * 
 * Autor: Professor Lorenzon, 2025-2
 */
package sintaxes;
import utilitarios.Teclado;

public class Z_App1 {

    public static void main(String[] args) {
       
        char continuar;
        int  numero, numeroUsuario;

        do {

            System.out.println ("Tente acertar o número que eu vou pensar !!!");      
            //Procure a documentação de random
            numero = (int)(Math.random()*100); //Casting de um double para int
            numeroUsuario = Teclado.readInt("Informe o número que você acredita ser: ");
            System.out.printf ("O número que eu pensei foi %d.\n", numero);
            if (numero == numeroUsuario) {
               System.out.println ("Portanto, você acertou!!!"); 
            } else {
                System.out.println ("Portanto, você não acertou!!!"); 
            }
            System.out.println ("Vamos tentar mais uma vez? S/N?");
            //Cascateando mensagens aos objetos.
            //Com a String retornada, transformando em Uppercase e deste retorno, 
            continuar = (Teclado.readString()
                                .toUpperCase()
                                .charAt(0)
            ); //pegando o primeiro char      

        } while (continuar == 'S');
    }
        
}
