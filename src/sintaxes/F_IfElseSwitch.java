/**
 * Programa com o objetivo de:
 * - Apresentar estruturas de controle de fluxo com condição (if, else, switch)
 * - Mostrar boas práticas de uso de blocos e operadores lógicos
 * 
 * Autor: Professor Lorenzon, 2025-2
 */


package sintaxes;
import utilitarios.Teclado;

public class F_IfElseSwitch {
    public static void main(String[] args) {
        
    //Variáveis de controle
    boolean resultado = true;
    int a = 0, b = 0;
    int opcao = 0;
    String mensagem = "";
     
    //Operação relacional
    resultado = (a == b); // true, já que ambos são zero

    //IF simples
    if (resultado) { // boa prática: sempre usar blocos {}
          mensagem = "O resultado foi verdadeiro";
    }
    System.out.println(mensagem);
        
    //IF/ELSE (duplo)
    if (resultado) {
          mensagem = "O resultado foi verdadeiro";
    } else {
          mensagem = "O resultado foi falso";
    }
    System.out.println(mensagem);

    //Operador ternário (condição ? valorTrue : valorFalse)
    mensagem = resultado ? "O resultado foi verdadeiro" : "O resultado foi falso";
    System.out.println(mensagem);

    //SWITCH/CASE – múltiplas escolhas
    System.out.println("\nEscolha uma opção às cegas (1 a 5):");
    opcao = Teclado.readInt();
    
    switch (opcao) {
      case 1:
        System.out.println("Opção 1 escolhida!");
        break;
    
      case 2:
        System.out.println("Opção 2 escolhida!");
        break;

      case 3:
        System.out.println("Opção 3 escolhida!");
        break;

      case 4:
        System.out.println("Opção 4 escolhida!");
        break;

      case 5:
        System.out.println("Opção 5 escolhida!");
        break;

      default:
        System.out.println("Opção escolhida inválida!");
        break;
    }

    }
}
