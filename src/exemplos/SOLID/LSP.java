package exemplos.SOLID;

/* O problema

class Retangulo {
    protected int largura;
    protected int altura;

    public void setLargura(int largura) { 
   this.largura = largura; 
    }
    public void setAltura(int altura) { 
        this.altura = altura; 
    }
    public int getArea() {
        return largura * altura;
    }
}

class Quadrado extends Retangulo {
    @Override
    public void setLargura(int largura) {
        this.largura = largura;
        this.altura = largura; // força os dois lados iguais
    }
    @Override
    public void setAltura(int altura) {
        this.largura = altura;
        this.altura = altura; // idem
    }
}

*/

//A solução

/*public*/ interface Forma {
    int getArea();
}

/*public*/ class Retangulo implements Forma {
    private int largura;
    private int altura;

    public Retangulo(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }

    @Override
    public int getArea() {
        return largura * altura;
    }
}

/*public*/ class Quadrado implements Forma {
    private int lado;
    public Quadrado(int lado) {
        this.lado = lado;
    }

    @Override
    public int getArea() {
        return lado * lado;
    }
}	


public class LSP {
    public static void main(String[] args) {

        //Referencia para a Interface, para provar o princípio
        Forma f1 = new Retangulo(10, 5);
        Forma f2 = new Quadrado(7);

        System.out.println("Área do Retângulo: " + f1.getArea());
        System.out.println("Área do Quadrado: " + f2.getArea());

        // O uso é consistente: qualquer "Forma" funciona corretamente
        exibirArea(f1);
        exibirArea(f2);
    }

    public static void exibirArea(Forma forma) {
        System.out.println("Área calculada: " + forma.getArea());
    }
}




