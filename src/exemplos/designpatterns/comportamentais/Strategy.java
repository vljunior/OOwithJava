package exemplos.designpatterns.comportamentais;

/*public*/ interface PagamentoStrategy {
    void pagar(double valor);
}

/*public*/ class PagamentoCartaoCredito implements PagamentoStrategy {
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$" + valor + 
 				     " realizado com Cartão de Crédito.");
    }
}

/*public*/ class PagamentoPix implements PagamentoStrategy {
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$" + valor + " realizado via Pix.");
    }
}

/*public*/ class PagamentoBoleto implements PagamentoStrategy {
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$" + valor + " realizado com Boleto.");
    }
}

/*public*/ class CarrinhoDeCompras {
    private PagamentoStrategy estrategiaPagamento; //associação

    // Permite trocar a estratégia dinamicamente
    public void setEstrategiaPagamento(PagamentoStrategy estrategiaPagamento) {
        this.estrategiaPagamento = estrategiaPagamento;
    }

    public void finalizarCompra(double valor) {
        if (estrategiaPagamento == null) {
            System.out.println("Nenhuma forma de pagamento selecionada.");
        } else {
            estrategiaPagamento.pagar(valor);
        }
    }
}

public class Strategy {
    public static void main(String[] args) {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        // Pagamento com Cartão
        carrinho.setEstrategiaPagamento(new PagamentoCartaoCredito());
        carrinho.finalizarCompra(250.0);

        // Troca dinâmica para Pix
        carrinho.setEstrategiaPagamento(new PagamentoPix());
        carrinho.finalizarCompra(100.0);

        // Troca dinâmica para Boleto
        carrinho.setEstrategiaPagamento(new PagamentoBoleto());
        carrinho.finalizarCompra(300.0);
    }
}






