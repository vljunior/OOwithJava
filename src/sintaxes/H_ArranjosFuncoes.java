/**
 * Programa com o objetivo:
 * - Apresentar a estrutura de arrays (vetores e matrizes)
 * - Aplicar o laço for como forma organizada de repetição
 * - Introduzir noções de escopo e exceções
 * 
 * Autor: Professor Lorenzon – 2025-2
 */

package sintaxes;
import utilitarios.Teclado;

public class H_ArranjosFuncoes {

    //uma função é um método de classe
    
    static void apresentarVetor (int v []){

        //length é atributo final de um objeto do tipo arranjo
        for (int i = 0; i < v.length; i++){ //não se altera i no bloco
            //i só existe neste bloco
            System.out.printf("%d\n", v[i]);
            //i = i + 1; //jamais!!!!
        }   
        //i = 2; fora de escopo

    }

    public static void main(String[] args) {
       
        int vetor [];
        int matriz [][];
        int lista [] = {0,1,2,3,4,5,6,7,8,9}; //instanciando com os valores

        vetor   = new int [5]; //instanciar o objeto arranjo
        matriz  = new int [2][2];

        //usando for para ler o vetor;
        int i;
        
        //o for é um while diferenciado, cm cabeçalho de propósito organizável de laço!

        for (i = 0; //inicialização, podendo ser duas ou mais instruções com ","
             i < 5; //condição única, booleana
             i++){  //"incremento" ou teste de nova condição de laço for, i não deve ser alterado no bloco

                System.out.printf("Informe o elemento [%d] do vetor: ", i);
                vetor[i] = Teclado.readInt();

        }        
        System.out.println(""); //quebrar linha
        apresentarVetor (vetor); //chamada de "função" com passagem de argumentos

        System.out.println("");
        apresentarVetor (lista); 

        vetor [10] = 0;
        //Nosso contato inicial com uma exceção!!!

        //For Each

        for (int elemento : vetor){ //tipo, nome vetor e um nome para o elemento em valor

            System.out.println(elemento);

        }

    }
}
