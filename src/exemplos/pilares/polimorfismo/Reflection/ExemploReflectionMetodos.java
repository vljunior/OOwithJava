package exemplos.pilares.polimorfismo.Reflection;

import java.lang.reflect.Method;


class Pessoa {
    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public void apresentar() {
        System.out.println("Olá, meu nome é " + nome);
    }
}


public class ExemploReflectionMetodos {
    public static void main(String[] args) throws Exception {
        // Criando objeto normalmente
        Pessoa p = new Pessoa("Maria");

        // Usando Generics e Reflection para descobrir métodos da classe
        Class<?> classe = p.getClass(); 
        System.out.println("Nome da classe: " + classe.getName());

        Method[] metodos = classe.getDeclaredMethods();
        System.out.println("Métodos encontrados:");
        for (Method m : metodos) {
            System.out.println("- " + m.getName());
        }

        // Invocando método via Reflection
        Method metodoApresentar = classe.getMethod("apresentar");
        metodoApresentar.invoke(p); 
    }
}

