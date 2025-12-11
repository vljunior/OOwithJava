package Padaria.services;

import java.util.List;
import java.util.function.Consumer;

import Padaria.model.entities.Cliente;
import Padaria.repository.RepositorioBinario;
import Padaria.utilitarios.Teclado;
import Padaria.utilitarios.Video;

public class ClienteService {
    private final RepositorioBinario<Cliente> repoClientes;
    
    public ClienteService() {
        this.repoClientes = new RepositorioBinario<>("clientes");
    }
    
    public void cadastrarCliente() {
        Video.cabecalho("CADASTRAR CLIENTE");
        Cliente cliente = criarClienteViaInput();
        repoClientes.salvar(cliente);
        Video.mensagemOk("Cliente cadastrado! ID: " + cliente.getId());
    }
    
    public void listarClientes() {
        Video.cabecalho("CLIENTES CADASTRADOS");
        List<Cliente> clientes = repoClientes.listarTodos();
        if (clientes.isEmpty()) {
            Video.mensagemInfo("Nenhum cliente cadastrado.");
            return;
        }
        clientes.forEach(c -> Video.mensagem(c.toString()));
    }
    
    public void listarClientesDetalhado() {
        Video.cabecalho("CLIENTES - VISÃO DETALHADA");
        List<Cliente> clientes = repoClientes.listarTodos();
        if (clientes.isEmpty()) {
            Video.mensagemInfo("Nenhum cliente cadastrado.");
            return;
        }
        clientes.forEach(cliente -> {
            Video.mensagem(cliente.toStringCompleto());
            Video.separador(40);
        });
    }
    
    public void editarCliente() {
        Video.cabecalho("EDITAR CLIENTE");
        Cliente cliente = selecionarCliente();
        if (cliente == null) return;
        processarEdicao(cliente);
    }
    
    public void removerCliente() {
        Video.cabecalho("REMOVER CLIENTE");
        Cliente cliente = selecionarCliente();
        if (cliente == null) return;
        processarRemocao(cliente);
    }
    
    public void buscarClientePorId() {
        Video.cabecalho("BUSCAR CLIENTE POR ID");
        List<Cliente> clientes = repoClientes.listarTodos();
        if (clientes.isEmpty()) {
            Video.mensagemInfo("Nenhum cliente cadastrado.");
            return;
        }
        buscarPorId(clientes);
    }
    
    public void buscarClientePorNome() {
        Video.cabecalho("BUSCAR CLIENTE POR NOME");
        List<Cliente> clientes = repoClientes.listarTodos();
        if (clientes.isEmpty()) {
            Video.mensagemInfo("Nenhum cliente cadastrado.");
            return;
        }
        buscarPorNome(clientes);
    }
    
    private Cliente criarClienteViaInput() {
        String nome = Teclado.readString("Nome:");
        String telefone = Teclado.readString("Telefone:");
        String email = Teclado.readString("Email:");
        return new Cliente(nome, telefone, email, "");
    }
    
    private Cliente selecionarCliente() {
        List<Cliente> clientes = repoClientes.listarTodos();
        if (clientes.isEmpty()) {
            Video.mensagemErro("Nenhum cliente cadastrado.");
            return null;
        }
        exibirClientesParaSelecao(clientes);
        return buscarClientePorInput();
    }
    
    private void exibirClientesParaSelecao(List<Cliente> clientes) {
        Video.mensagem("CLIENTES DISPONÍVEIS:");
        clientes.forEach(c -> Video.mensagem("ID: " + c.getId() + " - " + c.getNome() + " - " + c.getTelefone()));
    }
    
    private Cliente buscarClientePorInput() {
        int id = Teclado.readInt("ID do cliente:");
        Cliente cliente = repoClientes.buscarPorId(id);
        if (cliente == null) {
            Video.mensagemErro("Cliente não encontrado!");
            return null;
        }
        return cliente;
    }
    
    private void processarEdicao(Cliente cliente) {
        Video.mensagem("Editando: " + cliente.getNome());
        Video.mensagem("Deixe em branco para manter valor atual:");
        editarCamposCliente(cliente);
        repoClientes.atualizar(cliente);
        Video.mensagemOk("Cliente atualizado!");
    }
    
    private void editarCamposCliente(Cliente cliente) {
        editarCampo("nome", cliente.getNome(), cliente::setNome);
        editarCampo("telefone", cliente.getTelefone(), cliente::setTelefone);
        editarCampo("email", cliente.getEmail(), cliente::setEmail);
    }
    
    private void processarRemocao(Cliente cliente) {
        Video.mensagemAlerta("Remover: " + cliente.getNome());
        String confirmacao = Teclado.readString("Confirmar? (S/N):");
        if (!confirmacao.equalsIgnoreCase("S")) {
            Video.mensagemInfo("Remoção cancelada.");
            return;
        }
        repoClientes.deletar(cliente.getId());
        Video.mensagemOk("Cliente removido!");
    }
    
    private void buscarPorId(List<Cliente> clientes) {
        int id = Teclado.readInt("ID do cliente:");
        Cliente cliente = repoClientes.buscarPorId(id);
        if (cliente == null) {
            Video.mensagemErro("Cliente não encontrado!");
            return;
        }
        Video.mensagemOk("CLIENTE ENCONTRADO:");
        Video.mensagem(cliente.toStringCompleto());
    }
    
    private void buscarPorNome(List<Cliente> clientes) {
        String nomeBusca = Teclado.readString("Nome:").toLowerCase();
        List<Cliente> resultados = filtrarClientesPorNome(clientes, nomeBusca);
        if (resultados.isEmpty()) {
            Video.mensagemInfo("Nenhum cliente encontrado.");
            return;
        }
        exibirResultadosBusca(resultados);
    }
    
    private List<Cliente> filtrarClientesPorNome(List<Cliente> clientes, String nomeBusca) {
        return clientes.stream()
            .filter(c -> c.getNome().toLowerCase().contains(nomeBusca))
            .toList();
    }
    
    private void exibirResultadosBusca(List<Cliente> resultados) {
        Video.mensagemOk(resultados.size() + " cliente(s) encontrado(s):");
        resultados.forEach(c -> Video.mensagem("ID: " + c.getId() + " - " + c.getNome()));
    }
    
    private void editarCampo(String nomeCampo, String valorAtual, Consumer<String> setter) {
        String novoValor = Teclado.readString("Novo " + nomeCampo + " [" + valorAtual + "]:");
        if (novoValor.trim().isEmpty()) return;
        setter.accept(novoValor);
    }
    
    public Cliente buscarCliente(int id) {
        return repoClientes.buscarPorId(id);
    }
    
    public List<Cliente> listarTodosClientes() {
        return repoClientes.listarTodos();
    }
}