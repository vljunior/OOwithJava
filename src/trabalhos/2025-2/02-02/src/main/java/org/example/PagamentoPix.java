package org.example;

public class PagamentoPix implements MetodoPagamento {

    @Override
    public boolean processarPagamento(double valor) {
        System.out.println("Processando PIX no valor de R$" + valor + "...");
        System.out.println("Pagamento PIX aprovado com sucesso.");
        return true;
    }
}