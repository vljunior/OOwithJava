package exemplos.pilares.polimorfismo.Reflection;

import java.lang.reflect.Field;

class Produto {
    private String nome; //Veja, é privado, mas....
    private double preco;

    public Produto() {
        this.nome = "Sem nome";
        this.preco = 0.0;
    }
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
    public void mostrar() {
        System.out.println("Produto: " + nome + " - R$ " + preco);
    }
}


public class ExemploReflectionField {
    public static void main(String[] args) throws Exception {
        Produto prod = new Produto("Caneta", 2.5);
        Class<?> classe = prod.getClass();

        // Acessando atributo privado "nome"
        Field campoNome = classe.getDeclaredField("nome");
        campoNome.setAccessible(true); // quebra encapsulamento, cuidado!

        // Alterando valor do atributo dinamicamente
        campoNome.set(prod, "Caneta Azul");
        prod.mostrar(); // Produto: Caneta Azul - R$ 2.5
    }
}

