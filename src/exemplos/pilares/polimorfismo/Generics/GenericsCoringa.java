package exemplos.pilares.polimorfismo.Generics;

import java.util.*;

public class GenericsCoringa {
    // Método que aceita listas de qualquer tipo
    public static void imprimirLista(List<?> lista) {
        for (Object obj : lista) {
            System.out.println(obj);
        }
    }

    public static void main(String[] args) {
        List<String> nomes = Arrays.asList("Ana", "João", "Maria");
        List<Integer> numeros = Arrays.asList(1, 2, 3);

        imprimirLista(nomes);   // funciona
        imprimirLista(numeros); // também funciona
    }
}
