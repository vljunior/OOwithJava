package exemplos.pilares.polimorfismo.Generics;

// Interface
interface Vendavel {
    double getPreco();
}

class Produto implements Vendavel {
    private double preco;
    public Produto(double preco) { this.preco = preco; }
    public double getPreco() { return preco; }
}

// Classe genérica só aceita T que implemente Vendavel
class Carrinho<T extends Vendavel> {
    private T item;

    public Carrinho(T item) {
        this.item = item;
    }

    public void mostrarPreco() {
        System.out.println("Preço: " + item.getPreco());
    }
}

public class GenericsTImplements {
    public static void main(String[] args) {
        Carrinho<Produto> c = new Carrinho<>(new Produto(15.5));
        c.mostrarPreco(); // Saída: Preço: 15.5
    }
}