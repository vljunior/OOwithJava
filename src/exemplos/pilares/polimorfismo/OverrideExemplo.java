package exemplos.pilares.polimorfismo;

//Autor: Prof. Lorenzon

class Animal {
    public void fazerSom() {
        System.out.println("Som genérico de animal");
    }
}

class Cachorro extends Animal {
    @Override
    public void fazerSom() {
        System.out.println("Au au!");
    }
}

public class OverrideExemplo {
    public static void main(String[] args) {
        Animal a1 = new Animal();
        Animal a2 = new Cachorro();

        a1.fazerSom(); // Saída: Som genérico de animal
        a2.fazerSom(); // Saída: Au au!
    }
}