/**
 * Programa com o seguinte objetivo:
 * - Apresentar princípios básicos de programação (variáveis, operadores, fluxo).
 * - Refrescar lógica com incremento/decremento e acumuladores.
 * - Mostrar uso de operadores compostos e booleanos.
 * 
 * Autor: Professor Lorenzon
 */

package sintaxes;

public class C_PrincipiosContAcumFlag { //inicio da classe
    
    public static void main (String[] args) { 
  
        //Contador: exemplo clássico de variável de controle
        int contador = 0;

        //Acumulador: variável que soma progressivamente valores
        double acumulador = 0;

        //Flag (sentinela): variável booleana para controle de fluxo
        boolean flag = false; // valor padrão já é false, mas é importante declarar

        System.out.printf ("\nValor inicial de declaração do contador: %d\n", 
                           contador);
        System.out.printf ("Valor modificado primeira vez: %d\n", 
                           contador++); //usa e depois...
        System.out.printf ("Valor modificado segunda vez: %d\n", 
                           ++contador); //incrementa antes
                      
        //Agora contador = 2
        --contador;  // vira 1
        contador--;  // vira 0

        System.out.printf ("\nValor inicial retornado do contador: %d\n\n", contador);

        //Acumulador: somas progressivas
        System.out.println("\nAcumulador inicial: " + acumulador);
        acumulador = acumulador + 10;   // forma tradicional
        System.out.println("Após acumulador = acumulador + 10: " + acumulador);
        acumulador += 100;              // forma simplificada com operador composto
        System.out.println("Após acumulador += 100: %.2f" + acumulador);

        //Inversão de booleano (sentinela)
        System.out.println("\nValor inicial da flag: " + flag);
        flag = !flag; // inverte valor lógico (false → true)
        System.out.println("Valor da flag após inversão (!flag): " + flag);
               
    } //fim de main
  
  } //fim da classe