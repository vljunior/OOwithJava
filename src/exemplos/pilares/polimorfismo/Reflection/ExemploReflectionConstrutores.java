package exemplos.pilares.polimorfismo.Reflection;

import java.lang.reflect.Constructor;

class Produto {
    private String nome;
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

public class ExemploReflectionConstrutores {
    public static void main(String[] args) throws Exception {
        
        // Criando objeto normalmente
        Class<?> classe = Produto.class;

        // Listando construtores
        Constructor<?>[] construtores = classe.getConstructors();
        System.out.println("Construtores da classe Produto:");
        for (Constructor<?> c : construtores) {
            System.out.println("- " + c);
        }

        // Obtendo construtor específico
        Constructor<?> construtorComArgs = classe.getConstructor(String.class, double.class);

        // Criando objeto dinamicamente com Reflection
        Object produto = construtorComArgs.newInstance("Caderno", 12.5);

        // Chamando método mostrar() normalmente
        Produto p = (Produto) produto;
        p.mostrar();
    }
}

