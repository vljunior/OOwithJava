package org.example;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;


    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItemPedido> itens = new ArrayList<>(); // 1. Lista de itens

    @Column(name = "valor_total")
    private double valorTotal;

    private boolean pago;

    @Transient
    private MetodoPagamento metodoPagamento;

    private LocalDate dataPedido;


    public Pedido() {
        this.pago = false;
        this.valorTotal = 0.0;
        this.dataPedido = LocalDate.now();
    }


    public void adicionarItem(ItemPedido item) {
        item.setPedido(this);
        this.itens.add(item);
        calcularTotal();
    }

    public void calcularTotal() {
        double total = 0.0;
        for (ItemPedido item : this.itens) {
            total += item.calcularSubtotal();
        }
        this.valorTotal = total;
    }

    public boolean finalizarPedido() {
        if (this.metodoPagamento == null) {
            System.out.println("Erro: Nenhum método de pagamento definido.");
            return false;
        }

        boolean pagamentoAprovado = this.metodoPagamento.processarPagamento(this.valorTotal);

        if (pagamentoAprovado) {
            this.pago = true;
            for (ItemPedido item : this.itens) {
                item.getProduto().darBaixaEstoque(item.getQuantidade());
            }
            System.out.println("Pedido finalizado com sucesso!");
            return true;
        } else {
            System.out.println("Pagamento recusado.");
            return false;
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}