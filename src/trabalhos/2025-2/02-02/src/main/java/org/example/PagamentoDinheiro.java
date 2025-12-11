package org.example;

public class PagamentoDinheiro implements MetodoPagamento {

    @Override
    public boolean processarPagamento(double valor) {
        System.out.println("Pagamento em dinheiro de R$" + valor + " recebido.");
        return true;
    }
}