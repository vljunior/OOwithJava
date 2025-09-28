package exemplos.pilares.polimorfismo;

//Autor: Valdemar Lorenzon Junior
import java.util.ArrayList;
import java.util.List;

// Classe de exceção para carrinho vazio
/*public*/ class ExceptionCarrinhoVazio extends Exception {
    public ExceptionCarrinhoVazio() {
        super("Carrinho Vazio");
        System.out.println("Carrinho Vazio");
    }
}

// Interface que obriga todo item vendável a ter um preço
/*public*/ interface ItemVendavel {
    public double getPreco();
}

// Classe pai abstrata para garantir que todo "Aparelho" é um ItemVendavel
/*public*/ abstract class Aparelho implements ItemVendavel {
    // se não implementar getPreco, será abstrato
}

// Classes filhas com implementação de preço
/*public*/ class Tv extends Aparelho {
    private double preco = 1050.00;

    @Override
    public double getPreco() {
        return preco;
    }

    // Método específico da TV (exemplo para coerção explícita depois)
    public void ligarSmart() {
        System.out.println("Abrindo Netflix...");
    }
}

/*public*/ class TvCabo extends Aparelho {
    private double preco = 501.00;

    @Override
    public double getPreco() {
        return preco;
    }

    // Método específico da TvCabo
    public void ligarCabo() {
        System.out.println("Sintonizando canais a cabo...");
    }
}

// Classe Compras que gerencia o carrinho
/*public*/ class Compras {
    private List<ItemVendavel> carrinho; // programando para a interface

    public Compras() {
        carrinho = new ArrayList<>();
    }

    public void addCompra(ItemVendavel item) {
        carrinho.add(item); // coerção implícita (upcasting)
    }

    public double total() throws ExceptionCarrinhoVazio {
        if (carrinho.isEmpty()) {
            throw new ExceptionCarrinhoVazio();
        }

        double t = 0;
        for (ItemVendavel item : carrinho) {
            t += item.getPreco();
        }
        return t;
    }
}

// Classe principal
/*public*/ public class CoercaoeSubtipo {
    public static void main(String[] args) {
        try {
            Compras minhasCompras = new Compras();

            // Coerção implícita: Tv e TvCabo são subtipos de ItemVendavel
            minhasCompras.addCompra(new Tv());
            minhasCompras.addCompra(new TvCabo());
            minhasCompras.addCompra(new TvCabo());

            // Descobrindo o total
            try {
                System.out.println("O total: " + minhasCompras.total());
            } catch (ExceptionCarrinhoVazio e) {
                System.out.println("Não há compras no carrinho!");
            }

            // Exemplo de coerção explícita (downcasting)
            ItemVendavel item = new Tv();
            // Não consigo chamar ligarSmart() direto, só getPreco()
            System.out.println("Preço do item: " + item.getPreco());

            // Downcasting para acessar comportamento específico
            if (item instanceof Tv) {
                ((Tv) item).ligarSmart();
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            System.out.println("Fim de execução.");
        }
    }
}
