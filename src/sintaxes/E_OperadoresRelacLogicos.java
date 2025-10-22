/**
 * Programa com o objetivo de:
 * - Apresentar operadores relacionais e lógicos
 *  
 * Autor: Professor Lorenzon, 2025-2
 */
package sintaxes;   

public class E_OperadoresRelacLogicos { //inicio da classe

    public static void main (String[] args)  {    

    //Operadores lógicos e curto-circuito
    boolean x = true, y = true; // testar também false/true, false/false etc.
    boolean resultadoE = x && y;   // AND lógico
    boolean resultadoOu = x || y;  // OR lógico

    System.out.printf(
            "\nResultado da operação lógica AND (x && y): %b\n" +
            "Resultado da operação lógica OR (x || y): %b\n",
            resultadoE, resultadoOu
    );

    //Dica: Curto-circuito
    // - Se x for false, x && y não avalia y (pois já se sabe que será false)
    // - Se x for true, x || y já será true (não avalia y)


    //Operadores relacionais
    int a = 5, b = 10;
    boolean resultado;

    resultado = a == b; // Igualdade
    System.out.printf("\nResultado de (a == b): %b\n", resultado);
    System.out.printf("Negação (!resultado): %b\n", !resultado);

    /* Experimente substituir por:
       resultado = a != b;     // Diferente
       resultado = a < b;      // Menor que
       resultado = a > b;      // Maior que
       resultado = a <= b;     // Menor ou igual
       resultado = a >= b;     // Maior ou igual
    */

    }
}