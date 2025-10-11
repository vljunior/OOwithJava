package exemplos.pilares.polimorfismo.Generics;


/*public*/ class Caixa<T> {
    private T conteudo;

    public void colocar(T objeto) {
        this.conteudo = objeto;
    }
    
    public T retirar() {
        return conteudo;
    }
}

public class CaixaT {
    public static void main(String[] args) {
        Caixa<Integer> caixaInteiro = new Caixa<Integer>();
        caixaInteiro.colocar(10);
        System.out.println
         ("Conteúdo da caixa de inteiros: " + 
  				caixaInteiro.retirar());

        Caixa<String> caixaString = new Caixa<>();
        caixaString.colocar("Olá, mundo!");
        System.out.println
 	           ("Conteúdo da caixa de strings: " +    
                                     caixaString.retirar());
    }
}

/*
 Exemplo método T 

public class Utils {    
 	  public static <T> void imprimirElemento(T elemento) {
        System.out.println(elemento);
    }
} 
  
 */

