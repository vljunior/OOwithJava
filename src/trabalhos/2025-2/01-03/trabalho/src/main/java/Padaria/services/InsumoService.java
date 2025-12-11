package Padaria.services;

import java.util.List;
import java.util.function.Consumer;

import Padaria.model.entities.Insumo;
import Padaria.repository.RepositorioBinario;
import Padaria.utilitarios.Teclado;
import Padaria.utilitarios.Video;

public class InsumoService {
    private final RepositorioBinario<Insumo> repoInsumos;

    public InsumoService() {
        this.repoInsumos = new RepositorioBinario<>("insumos");
    }

    public void checarEstoqueInsumos() {
        Video.cabecalho("ESTOQUE DE INSUMOS");
        List<Insumo> insumos = repoInsumos.listarTodos();
        if (insumos.isEmpty()) {
            Video.mensagemInfo("Nenhum insumo cadastrado.");
            return;
        }
        insumos.forEach(i -> Video.mensagem(i.toString()));
    }

    public void cadastrarInsumo() {
        Video.cabecalho("CADASTRAR INSUMO");
        Insumo insumo = criarInsumoViaInput();
        repoInsumos.salvar(insumo);
        Video.mensagemOk("Insumo cadastrado! ID: " + insumo.getId());
    }
    
    public void listarInsumosCadastrados() {
        Video.cabecalho("INSUMOS CADASTRADOS");
        List<Insumo> insumos = repoInsumos.listarTodos();
        if (insumos.isEmpty()) {
            Video.mensagemInfo("Nenhum insumo cadastrado.");
            return;
        }
        insumos.forEach(i -> Video.mensagem(i.toString() + " - " + (i.precisaReposicao() ? "REPOR" : "OK")));
    }

    public void editarInsumo() {
        Video.cabecalho("EDITAR INSUMO");
        Insumo insumo = selecionarInsumo();
        if (insumo == null) return;
        processarEdicao(insumo);
    }
    
    public void removerInsumo() {
        Video.cabecalho("REMOVER INSUMO");
        Insumo insumo = selecionarInsumo();
        if (insumo == null) return;
        processarRemocao(insumo);
    }
    
    public void adicionarEstoqueInsumo() {
        Video.cabecalho("ADICIONAR ESTOQUE INSUMO");
        Insumo insumo = selecionarInsumo();
        if (insumo == null) return;
        processarAdicaoEstoque(insumo);
    }
    
    public void diagnosticoInsumos() {
        Video.cabecalho("DIAGNÓSTICO DE INSUMOS");
        List<Insumo> insumos = repoInsumos.listarTodos();
        if (insumos.isEmpty()) {
            Video.mensagemInfo("Nenhum insumo cadastrado.");
            return;
        }
        exibirDiagnostico(insumos);
    }
    
    public List<Insumo> listarTodosInsumos() {
        return repoInsumos.listarTodos();
    }

    public Insumo buscarInsumoPorId(int id) {
        return repoInsumos.buscarPorId(id);
    }

    public void atualizarInsumo(Insumo insumo) {
        repoInsumos.atualizar(insumo);
    }
    
    // MÉTODOS PRIVADOS
    private Insumo criarInsumoViaInput() {
        String nome = Teclado.readString("Nome do insumo:");
        String unidade = Teclado.readString("Unidade de medida:");
        int estoqueMinimo = Teclado.readInt("Estoque mínimo:");
        return new Insumo(nome, unidade, estoqueMinimo);
    }
    
    private Insumo selecionarInsumo() {
        List<Insumo> insumos = repoInsumos.listarTodos();
        if (insumos.isEmpty()) {
            Video.mensagemErro("Nenhum insumo cadastrado.");
            return null;
        }
        exibirInsumosParaSelecao(insumos);
        return buscarInsumoPorInput();
    }
    
    private void exibirInsumosParaSelecao(List<Insumo> insumos) {
        Video.mensagem("INSUMOS DISPONÍVEIS:");
        insumos.forEach(i -> Video.mensagem("ID: " + i.getId() + " - " + i.getNome() + " (" + i.getQuantidadeAtual() + " " + i.getUnidadeMedida() + ")"));
    }
    
    private Insumo buscarInsumoPorInput() {
        int id = Teclado.readInt("ID do insumo:");
        Insumo insumo = repoInsumos.buscarPorId(id);
        if (insumo == null) {
            Video.mensagemErro("Insumo não encontrado!");
            return null;
        }
        return insumo;
    }
    
    private void processarEdicao(Insumo insumo) {
        Video.mensagem("Editando: " + insumo.getNome() + " (" + insumo.getQuantidadeAtual() + " " + insumo.getUnidadeMedida() + ")");
        Video.mensagem("Deixe em branco para manter valor atual:");
        editarCamposInsumo(insumo);
        repoInsumos.atualizar(insumo);
        Video.mensagemOk("Insumo atualizado!");
    }
    
    private void editarCamposInsumo(Insumo insumo) {
        editarCampo("nome", insumo.getNome(), insumo::setNome);
        editarCampo("unidade", insumo.getUnidadeMedida(), insumo::setUnidadeMedida);
        editarCampoInt("estoque mínimo", insumo.getEstoqueMinimo(), insumo::setEstoqueMinimo);
    }
    
    private void processarRemocao(Insumo insumo) {
        Video.mensagemAlerta("Remover: " + insumo.getNome() + " (" + insumo.getQuantidadeAtual() + " " + insumo.getUnidadeMedida() + ")");
        String confirmacao = Teclado.readString("Confirmar remoção? (S/N):");
        if (!confirmacao.equalsIgnoreCase("S")) {
            Video.mensagemInfo("Remoção cancelada.");
            return;
        }
        repoInsumos.deletar(insumo.getId());
        Video.mensagemOk("Insumo removido!");
    }
    
    private void processarAdicaoEstoque(Insumo insumo) {
        int quantidade = Teclado.readInt("Quantidade a adicionar:");
        if (quantidade <= 0) {
            Video.mensagemErro("Quantidade deve ser maior que zero!");
            return;
        }
        
        insumo.adicionarEstoque(quantidade);
        repoInsumos.atualizar(insumo);
        Video.mensagemOk("Estoque adicionado! Total: " + insumo.getQuantidadeAtual() + " " + insumo.getUnidadeMedida());
    }
    
    private void exibirDiagnostico(List<Insumo> insumos) {
        Video.mensagem("Total de insumos: " + insumos.size());
        Video.separador(40);
        insumos.forEach(this::exibirDiagnosticoInsumo);
    }
    
    private void exibirDiagnosticoInsumo(Insumo insumo) {
        Video.mensagem("ID: " + insumo.getId() + " - " + insumo.getNome());
        Video.mensagem("  Estoque: " + insumo.getQuantidadeAtual() + " " + insumo.getUnidadeMedida());
        Video.mensagem("  Mínimo: " + insumo.getEstoqueMinimo() + " " + insumo.getUnidadeMedida());
        Video.mensagem("  Status: " + (insumo.precisaReposicao() ? "⚠️ REPOR" : "✅ OK"));
        Video.mensagem("");
    }
    
    private void editarCampo(String nomeCampo, String valorAtual, Consumer<String> setter) {
        String novoValor = Teclado.readString("Novo " + nomeCampo + " [" + valorAtual + "]:");
        if (novoValor.trim().isEmpty()) return;
        setter.accept(novoValor);
    }
    
    private void editarCampoInt(String nomeCampo, int valorAtual, Consumer<Integer> setter) {
        String novoValorStr = Teclado.readString("Novo " + nomeCampo + " [" + valorAtual + "]:");
        if (novoValorStr.trim().isEmpty()) return;
        
        try {
            setter.accept(Integer.parseInt(novoValorStr));
        } catch (NumberFormatException e) {
            Video.mensagemErro("Valor inválido.");
        }
    }
}