/* 
 Programa inicial de I/O Java
 Objetivo:
 - Fazer uso de entradas e saídas pela classe utilitária `Teclado`.
 - Apresentar estrutura básica de um programa Java.
 - Declarar e utilizar variáveis primitivas.
 - Demonstrar a leitura e saída formatada de dados.

 Conceitos abordados:
 - Organização de pacotes (`package`)
 - Importação de classes (`import`)
 - Método `main` como ponto de entrada
 - Declaração de variáveis primitivas
 - Entrada de dados customizada via classe `Teclado`
 - Saída de dados com `System.out.println` e `System.out.printf`
 
 Autoria: Professor Lorenzon, revisão 2025-2.
*/
   
package sintaxes;
import utilitarios.Teclado;

public class A_TiposVariaveis { //inicio da classe
    
    public static void main (String[] args)  {   
                                          
              //Tipos primitivos de dados com camelCase
              int      valorInteiro        = 0;      //padrão de inicialização é 0                            
              boolean  logicoVerdadeiro    = true;   //padrão de inicialização é false
              // OBS: Evitar o uso de float, prefira double para maior precisão.
              float    valorFlutuante      = 3.14f;  //ou double sem f 
              // ERRO COMUM: tentar usar vírgula como separador decimal sem tratamento. 
              double   valorDecimal        = 1.0;    //prefira double e use . para casa demimal 
              //char também é um tipo primitivo em Java, mas String não é.
              char     umaLetra            = 'A'; 

              umaLetra = 'B';          
              
              // float é menos preciso que double. Ao converter de double para float, é necessário "casting":
              // valorFlutuante = (float) Teclado.readDouble();

              // %d = inteiro, %.2f = float com 2 casas, %b = booleano
              //System.out.println: saída simples
              //System.out.printf: saída formatada

              System.out.printf ( "Valores inicializados:\n\nInteiro: %d\nDecimal: %.2f\nBooleano: %b", //uso de formatadores
                                   valorInteiro,
                                   valorFlutuante,                                   
                                   logicoVerdadeiro);

              System.out.println ("\nValores inicializados:\n\nInteiro: " + valorInteiro +
                                  "\nDecimal: " + valorFlutuante +
                                  "\nBooleano: " + logicoVerdadeiro                                    
              );
              

              //leitura dos valores e escrita dos mesmos via usuário
              
              //System.out.println ("\n\nInsira um valor inteiro:");
              valorInteiro     = Teclado.readInt("\n\nInsira um valor inteiro:");

              System.out.println ("Insira um valor decimal, entrada com \",\" :"); //curiosidade aqui
              valorDecimal     = Teclado.readDouble();            
              //valorFlutuante   = ((float)Teclado.readDouble()); //Casting
                       
              //Na saída abaixo os novos valores, observando os usos de print ln e f
              System.out.println ("\n\nOs novos valores ficaram:");

              //Para float e double, vale %f, não precisa %lf como em C
              System.out.printf ( "Novos valores:\n\nInteiro: %d\nDecimal: %.2f\nBooleano: %b", //uso de formatadores
                                   valorInteiro,
                                   valorDecimal,                                   
                                   logicoVerdadeiro);              
    } //fim de main
  
  } //fim da classe
  

