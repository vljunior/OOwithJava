package Padaria.model.menus;

import Padaria.model.interfaces.Menu;
import Padaria.services.InsumoService;
import Padaria.services.ProducaoService;
import Padaria.services.ProdutoService;
import Padaria.utilitarios.Teclado;
import Padaria.utilitarios.Video;

public class MenuProducao implements Menu {  
    private ProdutoService produto;
    private InsumoService insumo;
    private ProducaoService aviso;

    public MenuProducao() {
        this.produto = new ProdutoService();
        this.insumo = new InsumoService();
        this.aviso = new ProducaoService();
    }
    
    @Override
    public void executarMenu() {  
        int opcao;
        do {
            exibirCabecalho();
            exibirOpcoes();
            opcao = Teclado.readInt("Opção: ");
            processarOpcao(opcao);
            
            if (opcao != 0) {
                Video.pausa();
            }
            
        } while (opcao != 0);
    }
    
    @Override
    public void exibirCabecalho() {
        Video.limparTela();
        Video.cabecalho(" ÁREA DE PRODUÇÃO");
        Video.escreverLento("Controle da fabricação", 40);
        Video.separador();
    }
    
    @Override
public void exibirOpcoes() {
    Video.mensagem("[1] Cadastrar Produto");
    Video.mensagem("[2] Cadastrar Insumo");
    Video.mensagem("[3] Listar Produtos Cadastrados");
    Video.mensagem("[4] Listar Insumos Cadastrados");
    Video.mensagem("[5] Editar Produto");
    Video.mensagem("[6] Editar Insumo");
    Video.mensagem("[7] Remover Produto");
    Video.mensagem("[8] Remover Insumo");
    Video.mensagem("[9] Adicionar Estoque Insumo");
    Video.mensagem("[10] Cadastrar Receita do Produto"); 
    Video.mensagem("[11] Fabricar Produtos");
    Video.mensagem("[12] Alertas de Reposição de Insumos");
    Video.mensagem("[0] Voltar ao Menu Principal");
}

@Override
public void processarOpcao(int opcao) {
    switch(opcao) {
        case 1:
            produto.cadastrarProduto();         
            break;
        case 2:
            insumo.cadastrarInsumo();           
            break;
        case 3:
            produto.listarProdutos();  
            break;
        case 4:
            insumo.listarInsumosCadastrados();   
            break;
        case 5:
            produto.editarProduto();
            break;
        case 6:
            insumo.editarInsumo();
            break;
        case 7:
            produto.removerProduto();
            break;
        case 8:
            insumo.removerInsumo();
            break;
        case 9:
            insumo.adicionarEstoqueInsumo();     
            break;
        case 10:
            produto.cadastrarReceita();           
            break;
        case 11:
            produto.fabricarProdutos();           
            break;
        case 12:
            aviso.alertasReposicao();      
            break;     
        case 0:
            Video.mensagem("Voltando ao menu principal...");
            break;
        default:
            Video.mensagemErro("Opção inválida!");
    }
}
}