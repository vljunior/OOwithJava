/**
 * Programa com o objetivo de:
 * - Apresentar operadores aritméticos em Java
 * - Explicar divisão inteira, divisão real e operador módulo (%)
 * 
 * Autor: Professor Lorenzon, 2025-2
 */

package sintaxes;
   
public class D_OperadoresAritmeticos { //inicio da classe

    public static void main (String[] args)  { 

        //divisão inteira e flutuante
        int div;
        int resto;        
        int v1, v2 = 0;

        //fazendo uma divisão inteira
        v1 = 10;
        v2 = 3; //v2 não pode ser 0
        div = v1 / v2;
        System.out.printf ("\n\nO resultado da divisão inteira de %d / %d é %d", v1, v2, div);

        //Extraindo o resto com o operador módulo
        v1 = 51;
        v2 = 2; //v2 não pode ser 0
        resto = v1 % v2;
        System.out.printf ("\n\nO resto da divisão inteira de %d / %d é %d", v1, v2, resto);

        //Pegadinha: divisão com int retorna int mesmo se atribuído a double
        double real;
        v1 = 10;
        v2 = 3;
        real = v1 / v2; // Ainda será 3.0 (pois v1 e v2 são int)
        System.out.printf("\nDivisão decimal esperada? %d / %d = %.3f ← Ops!", v1, v2, real);

        //Correto: forçar pelo menos um valor a ser double (casting)
        real = (double) v1 / v2;
        System.out.printf("\nAgora sim: divisão decimal de %d / %d = %.3f", v1, v2, real);

        //Contagem e Acumulação: estrutura base de qualquer algoritmo de repetição
        int atributo = 9; // valor inicial
        int contador = 0;
        int acumulador = 0;

        //Acumulador: somando valores de forma explícita e simplificada
        acumulador = acumulador + atributo; // forma tradicional
        acumulador += atributo;             // forma compacta

        //Contador: incrementos equivalentes
        contador = contador + 1;
        contador += 1;
        ++contador;
        contador++; // todas resultam no mesmo efeito: +1

        // Observação: os operadores +=, -=, *=, /= funcionam também com outros tipos

        //Operação unária: sinal negativo
        System.out.printf("\nValor original de atributo: %d\n", atributo);
        atributo = -atributo; // torna negativo
        atributo = atributo * -1; // inverte novamente (negativo * -1 = positivo)
        System.out.printf("Valor final de atributo após inversões: %d\n", atributo);

    }
   }

