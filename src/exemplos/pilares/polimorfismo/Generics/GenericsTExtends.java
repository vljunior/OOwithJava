package exemplos.pilares.polimorfismo.Generics;

//O contrato
public interface Sonoro {
    public void fazerSom();
}

// Classe base
class Animal implements Sonoro {
    @Override
    public abstract void fazerSom();
}

class Cachorro extends Animal {
    @Override
    public void fazerSom() {
        System.out.println("Au au!");
    }
}

// Classe genérica que só aceita T que seja Animal (ou subclasses)
class Gaiola<T extends Animal & Sonoro> {
    private T animal;

    public Gaiola(T animal) {
        this.animal = animal;
    }

    public void ouvirSom() {
        animal.fazerSom();
    }
}

public class GenericsTExtends {
    public static void main(String[] args) {
        Gaiola<Cachorro> gaiola = new Gaiola<>(new Cachorro());
        gaiola.ouvirSom(); // Saída: Au au!
    }
}
