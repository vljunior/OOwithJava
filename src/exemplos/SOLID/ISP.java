package exemplos.SOLID;

/*O problema:
 
interface Trabalhador {
    void trabalhar();
    void comer();
}

class Operario implements Trabalhador {
    @Override
    public void trabalhar() {
        System.out.println("Operário está trabalhando...");
    }

    @Override
    public void comer() {
        System.out.println("Operário está comendo...");
    }
}

class Robo implements Trabalhador {
    @Override
    public void trabalhar() {
        System.out.println("Robô está trabalhando...");
    }

    //Violando o princípio
    @Override
    public void comer() {
        // Problema: um robô não come!
        throw new UnsupportedOperationException
 					("Robô não precisa comer!");
    }
} 
*/

//A solução

/*public*/ interface Trabalhador {
    void trabalhar();
}

/*public*/ interface Alimentavel {
    void comer();
}

/*public*/ class Operario implements Trabalhador, Alimentavel {
    @Override
    public void trabalhar() {
        System.out.println("Operário está trabalhando...");
    }

    @Override
    public void comer() {
        System.out.println("Operário está comendo...");
    }
}

/*public*/ class Robo implements Trabalhador {
    @Override
    public void trabalhar() {
        System.out.println("Robô está trabalhando...");
    }
}

public class ISP {
    public static void main(String[] args) {
        Trabalhador t1 = new Operario();
        Trabalhador t2 = new Robo();

        t1.trabalhar();
        t2.trabalhar();

        // Usando a interface específica
        Alimentavel a1 = new Operario();
        a1.comer();

        // Robo não é "Alimentavel", então não pode ser usado assim
        // Alimentavel a2 = new Robo(); -> Erro de compilação (correto!)
    }
}
