package exemplos.pilares.polimorfismo;

interface Sonoro {
    public void fazerSom();
}

class Animal implements Sonoro {
    @Override
    public void fazerSom() {
        System.out.println("Som genérico de animal!");
    }
}

class Cachorro extends Animal {
    public void latir() {
        System.out.println("Au au!");
    }
}

class Gato extends Animal {
    public void miar() {
        System.out.println("Miau!");
    }
}


public class Coercao {
    public static void main(String[] args) {

        //Programando pra implementação             
        Sonoro animal1 = new Animal();     //coerção implícita

        //Programando pra implementação
        Animal animal2 = new Animal();
        Animal animal3 = new Cachorro();  //coerção implícita    

        
        Gato animal4 = new Gato();

        //Os objetos irão responder de acordo com sua especialização, mas
        //o compilador entende pela referência
        
        animal1.fazerSom();
        animal2.fazerSom();
        animal3.fazerSom();

        //Porque animal 3 não late, na verdade ele late
        //animal3.latir();

        //assim
        ((Cachorro)animal3).latir();

        if (animal3 instanceof Cachorro){
            ((Cachorro)animal3).latir(); // coerção explícita
        }

        animal4.fazerSom();
        animal4.miar();

        //E se colocarmos o animal4 em algum lugar?

        animal1 = animal4;
        /*como ele poderia fazer isso
        animal1.fazerSom();
        animal1.miar();*/

        if (animal1 instanceof Gato){
            animal1.fazerSom();
            ((Gato)animal1).miar(); n
        }
        
    }
}

