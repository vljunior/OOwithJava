package apresentacao.menu;
import serviços.TipoPagamento;
import utilitarios.*;
import modelos.Pedido;

public class MenuPagamento {

    public static TipoPagamento exibir(Pedido pedido) {

        if(pedido.calcularPagamento() == 0) {
            Video.mensagemErro("O pedido nao possui itens. Adicione pratos antes de finalizar o pagamento.");
            return null;
        }
        
        String resposta = Teclado.readString("Deseja incluir taxa de serviço? s/n: ");
        if(Character.toLowerCase(resposta.charAt(0)) == 's') {
            pedido.setIncluirTaxaServico(true);
            Video.mensagemOk("Taxa de serviço sera incluida no pedido.");
            Video.pausa();

        } else {
            pedido.setIncluirTaxaServico(false);
            Video.mensagemOk("Taxa de serviço nao sera incluida no pedido.");
            Video.pausa();
        }
       
        Video.limparTela();
        Video.mensagemInfo("Valor total: $" + pedido.calcularPagamento());
        System.out.println("Selecione o metodo de pagamento:\n");
        System.out.println("1 - Dinheiro");
        System.out.println("2 - Cartao de Credito");
        System.out.println("3 - Cartao de Debito");
        System.out.println("4 - Pix");
        int opcao = Teclado.readInt("\nEscolha uma opção: ");

        switch (opcao) {
            case 1:
                Video.mensagemOk("Pedido finalizado. Total pago: R$ " +  pedido.calcularPagamento()+ " via Dinheiro.");
                return TipoPagamento.DINHEIRO;
            
            case 2:
                Video.mensagemOk("Pedido finalizado. Total pago: R$ " +  pedido.calcularPagamento()+ " via Cartao de Credito.");
                return TipoPagamento.CARTAO_CREDITO;

            case 3:
                Video.mensagemOk("Pedido finalizado. Total pago: R$ " +  pedido.calcularPagamento()+ " via Cartao de Debito.");
                return TipoPagamento.CARTAO_DEBITO;
            
            case 4:
                Video.mensagemOk("Pedido finalizado. Total pago: R$ " +  pedido.calcularPagamento()+ " via Pix.");
               return TipoPagamento.PIX;

            default:
                System.out.println("Opção invalida. Tente novamente.");
                return exibir(pedido);
        }
    }
}
