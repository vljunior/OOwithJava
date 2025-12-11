package org.example;

import java.util.List;

public class ClienteController {

    private ClienteService clienteService;

    public ClienteController() {
        this.clienteService = new ClienteService();
    }

    public void criarCliente(String nome, String cpf) {
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(nome);
        novoCliente.setCpf(cpf);

        clienteService.salvar(novoCliente);
    }


    public List<Cliente> listarClientes() {
        return clienteService.listarTodos();
    }


    public Cliente buscarCliente(Long id) {
        return clienteService.buscarPorId(id);
    }

    public void atualizarCliente(Long id, String novoNome, String novoCpf) {
        Cliente cliente = clienteService.buscarPorId(id);

        if (cliente != null) {
            cliente.setNome(novoNome);
            cliente.setCpf(novoCpf);

            clienteService.atualizar(cliente);
        } else {
            System.out.println("Cliente com ID " + id + " não encontrado.");
        }
    }

    public void removerCliente(Long id) {
        clienteService.remover(id);
    }
}