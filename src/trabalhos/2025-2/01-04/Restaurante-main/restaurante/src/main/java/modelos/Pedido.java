package modelos;
import java.util.ArrayList;

import utilitarios.Video;

public class Pedido implements iterface.Pagavel {
    private Cliente cliente;
    private Garcom garcom;
    private ArrayList<Prato> pratos = new ArrayList<>();
    private boolean taxaServico = true;
    
    
    public Pedido(Cliente cliente, Garcom garcom) {
        setCliente(cliente);
        setGarcom(garcom);
    }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Garcom getGarcom() { return garcom; }
    public void setGarcom(Garcom garcom) { this.garcom = garcom; }

    public void adicionarPrato(Prato prato) {
        pratos.add(prato);
    }

    public void setIncluirTaxaServico(boolean taxaServico) {
        this.taxaServico = taxaServico;
    }

    @Override
    public double calcularPagamento() {
        double total = 0;
        for (Prato prato : pratos) {
            total += prato.getPreco();
        }
        if (taxaServico) {
            total += total * garcom.getTaxaServico();
        }
        return total;
    }

    public void exibirDetalhesPedido() {

        Video.limparTela();
        Video.cabecalho("Resumo do Pedido");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Garçom: " + garcom.getNome());
        System.out.println("Pratos pedidos:");
        for (Prato prato : pratos) {
            System.out.println("- " + prato.getNome() + " - $" + prato.getPreco());
        }
        double total = calcularPagamento();
        System.out.println("\nTotal do pedido: $" + total + " com taxa de serviço");
    }

    public ArrayList<Prato> getPratos() {
        return pratos;
    }
}