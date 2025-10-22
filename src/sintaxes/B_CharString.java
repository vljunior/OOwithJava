/**
 * Programa para exemplificar sintaxe e fluxo em Java
 * Objetivo: fazer uso da classe String e manipular entradas e saídas com a mesma.
 * Também explora conceitos como imutabilidade e métodos úteis da classe String.
 * 
 * Autor: Professor Lorenzon, 2025-2
 */
 
 package sintaxes;
 import utilitarios.Teclado;
 //import java.utils.String; //Não é necessário pois String está implícita em Java

   public class B_CharString { //inicio da classe
    
    public static void main (String[] args) { // Método principal
 
            //Tipos primitivos de dados com camelCase
            char     umaLetra            = 'A'; 
              
            //Objetos de classe String
            String   linha               = ""; //Cadeia de caracteres inicialmente vazia
            
            // Entrada de dados do usuário
            System.out.println("\nInsira alguma informação de caracteres:");
            linha = Teclado.readString(); // Leitura da linha completa              
            System.out.println ("\n\nA cadeia completa de caracteres informada foi " + linha);
            umaLetra         = linha.charAt(0); //vamos pegar a primeira letra                             

            System.out.printf ( "O primeiro caractere digitado foi: %c\n", umaLetra);                     
            //Acionando um método do objeto da classe String            
            System.out.printf ( "O tamanho da cadeia de caracteres é de : %d\n", linha.length());

            // Manipulação de strings
            String aux = linha; // Aponta para o mesmo conteúdo de 'linha' (não faz cópia profunda)
            linha = "Valor"; // Redefine o conteúdo de 'linha'; 'aux' mantém o valor antigo

            // Comparação de strings
            boolean iguais = aux.equals(linha);
            System.out.printf("As strings 'aux' e 'linha' são iguais? %b" + iguais);

            // Imutabilidade: métodos que alteram strings retornam novas instâncias
            String maiuscula = aux.toUpperCase(); // Não altera aux, apenas retorna novo valor
            
            // Demonstração de alguns métodos úteis da classe String
            System.out.println("\nDemonstrando manipulações com a string original:");
            System.out.println("Maiúsculas: " + aux.toUpperCase());
            System.out.println("Minúsculas: " + aux.toLowerCase());
            System.out.println("Contém a letra 'a'? " + aux.contains("a"));
            System.out.println("Substituindo 'a' por '@': " + aux.replace("a", "@"));
            System.out.println("Sem espaços nas bordas: [" + aux.trim() + "]");
            if (aux.length() >= 2) {
                  System.out.println("Primeiros 2 caracteres: " + aux.substring(0, 2));
            }


             /*
              * RESUMO – Métodos úteis da classe String:
              * ----------------------------------------
              * .length()              → Tamanho da string
              * .charAt(i)            → Caractere na posição i
              * .equals(str)          → Compara conteúdos
              * .equalsIgnoreCase(str)→ Compara ignorando maiúsculas/minúsculas
              * .toUpperCase()        → Retorna versão maiúscula
              * .toLowerCase()        → Retorna versão minúscula
              * .substring(i, j)      → Recorte da string (do índice i até j-1)
              * .contains(str)        → Verifica se contém substring
              * .replace(a, b)        → Substitui caracteres/texto
              * .trim()               → Remove espaços das bordas
             */
    }
   }
          
