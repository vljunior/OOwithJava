package exemplos.pilares.polimorfismo;

interface Sonoro {
    public void fazerSom();
}

class abstract Animal implements Sonoro {
    @Override
    public abstract void fazerSom();
}

class Cachorro extends Animal {
    
    @Override
    public void fazerSom() {
        System.out.println("Au au!");
    }
}

class Gato extends Animal {
    @Override
    public void fazerSom() {
        System.out.println("Miau!");
    }
}


public class Coercao {
    public static void main(String[] args) {

        //Programando pra implementação             
        Sonoro animal1 = new Gato();     //coerção implícita

        //Programando pra implementação
        Animal animal2 = new Gato();
        Animal animal3 = new Cachorro();  //coerção implícita    

        
        Gato animal4 = new Gato();

        //Os objetos irão responder de acordo com sua especialização, mas
        //o compilador entende pela referência
        
        animal1.fazerSom();
        animal2.fazerSom();
        animal3.fazerSom();        
        
    }
}

