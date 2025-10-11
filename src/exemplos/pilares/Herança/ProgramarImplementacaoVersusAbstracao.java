package exemplos.pilares.Herança;

// Interface define um contrato
interface MetodoPagamento {
    void pagar(double valor);
}

// Classe abstrata define comportamento parcial, implementa o contrato e acrescenta o que é também comum
abstract class Pagamento implements MetodoPagamento {
    protected double valor;

    public Pagamento(double valor) {
        this.valor = valor;
    }

    // Método comum a todos
    public void mostrarValor() {
        System.out.println("Valor a pagar: R$ " + valor);
    }

    // Cada tipo de pagamento define sua forma de pagar, deixando isso pra especialização
    public abstract void pagar(double valor);
}

// Classe concreta implementa os detalhes
class CartaoCredito extends Pagamento {
    public CartaoCredito(double valor) {
        super(valor);
    }

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$ " + valor + " realizado com Cartão de Crédito.");
    }
}

class Boleto extends Pagamento {
    public Boleto(double valor) {
        super(valor);
    }

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$ " + valor + " realizado com Boleto Bancário.");
    }
}


class Pix extends Pagamento {
    public Pix(double valor) {
        super(valor);
    }

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$ " + valor + " realizado com Pix.");
    }
}

// Programa de teste
public class ProgramarImplementacaoVersusAbstracao { //TestePagamentos
    public static void main(String[] args) {

        // Programando para abstração-concreta (dependência direta da classe concreta)
        CartaoCredito c1 = new CartaoCredito(100.0);
        c1.mostrarValor();
        c1.pagar(100.0);

        // Programando para implentação (usando a interface como referência)
        MetodoPagamento pagamento1 = new Boleto(250.0);
        pagamento1.pagar(250.0); // decisão concreta é feita pela subclasse Boleto

        // Programando para abstração-abstract (usando a abstract como referência)
        Pagamento pagamento2 = pagamento2 = new Pix(250.0);
        pagamento2.pagar(350.0); // decisão concreta é feita pela subclasse Boleto


        //Note, em todos os casos, a referência é por implements ou por extends e o new é pra classe abstração, 
        //a concreta. Aí que esta o detalhe que o polimorfismo traz: cada método responderá de acordo com a
        //sua especialização.... ;)

    }
}
