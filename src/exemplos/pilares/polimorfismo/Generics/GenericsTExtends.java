package exemplos.pilares.polimorfismo.Generics;

// Classe base
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

// Classe genérica que só aceita T que seja Animal (ou subclasses)
class Zoologico<T extends Animal> {
    private T animal;

    public Zoologico(T animal) {
        this.animal = animal;
    }

    public void ouvirSom() {
        animal.fazerSom();
    }
}

public class GenericsTExtends {
    public static void main(String[] args) {
        Zoologico<Cachorro> zoo = new Zoologico<>(new Cachorro());
        zoo.ouvirSom(); // Saída: Au au!
    }
}
