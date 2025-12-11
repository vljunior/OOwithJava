package org.example.view;

import org.example.Cliente;
import org.example.ClienteController;
import org.example.Funcionario;
import org.example.FuncionarioController;
import org.example.menus.MenuPessoa;
import org.example.utilitarios.Teclado;
import org.example.utilitarios.Video;

import java.util.List;

public class PessoaView {

    private ClienteController clienteController;
    private FuncionarioController funcionarioController;

    public PessoaView() {
        this.clienteController = new ClienteController();
        this.funcionarioController = new FuncionarioController();
    }

    public void executar() {
        boolean executando = true;
        while (executando) {
            int opcao = MenuPessoa.exibir();
            switch (opcao) {
                case 1:
                    listar();
                    break;
                case 2:
                    remover();
                    break;
                case 3:
                    cadastrar();
                    break;
                case 4:
                    atualizar();
                    break;
                case 5:
                    executando = false;
                    break;
            }
        }
    }

    private void listar() {
        Video.cabecalho("Listar Pessoas");

        System.out.println("Clientes");
        List<Cliente> clientes = clienteController.listarClientes();
        if (clientes.isEmpty()) System.out.println("Nenhum cliente cadastrado.");
        for (Cliente c : clientes) {
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome() + " | CPF: " + c.getCpf());
        }

        System.out.println("\n--- Funcionários ---");
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        if (funcionarios.isEmpty()) System.out.println("Nenhum funcionário cadastrado.");
        for (Funcionario f : funcionarios) {
            System.out.println("ID: " + f.getId() + " | Nome: " + f.getNome() + " | Cargo: " + f.getCargo());
        }
        Video.pausa();
    }

    private void remover() {
        Video.cabecalho("Remover Pessoa");
        int tipo = Teclado.readInt("Remover 1- Cliente ou 2- Funcionário? ");

        if (tipo == 1) {
            long id = Teclado.readInt("ID do Cliente: ");
            if (clienteController.buscarCliente(id) != null) {
                clienteController.removerCliente(id);
                Video.mensagemOk("Cliente removido!");
            } else {
                Video.mensagemErro("Cliente não encontrado.");
            }
        } else if (tipo == 2) {
            long id = Teclado.readInt("ID do Funcionário: ");
            if (funcionarioController.buscarFuncionario(id) != null) {
                funcionarioController.removerFuncionario(id);
                Video.mensagemOk("Funcionário removido!");
            } else {
                Video.mensagemErro("Funcionário não encontrado.");
            }
        }
        Video.pausa();
    }

    private void cadastrar() {
        Video.cabecalho("Cadastrar Pessoa");
        int tipo = Teclado.readInt("Cadastrar 1- Cliente ou 2- Funcionário? ");

        if (tipo == 1) {
            String nome = Teclado.readString("Nome: ");
            String cpf = Teclado.readString("CPF: ");
            clienteController.criarCliente(nome, cpf);
            Video.mensagemOk("Cliente salvo");
        } else if (tipo == 2) {
            String nome = Teclado.readString("Nome: ");
            String cargo = Teclado.readString("Cargo: ");
            funcionarioController.criarFuncionario(nome, cargo);
            Video.mensagemOk("Funcionário salvo");
        }
        Video.pausa();
    }

    private void atualizar() {
        Video.cabecalho("Atualizar Pessoa");
        int tipo = Teclado.readInt("1 - Cliente ou 2 - Funcionário");

        if (tipo == 1) {
            long id = Teclado.readInt("ID do Cliente: ");
            Cliente c = clienteController.buscarCliente(id);
            if (c != null) {
                String nome = Teclado.readString("Novo Nome: ");
                String cpf = Teclado.readString("Novo CPF: ");
                clienteController.atualizarCliente(id, nome, cpf);
                Video.mensagemOk("Atualizado!");
            } else {
                Video.mensagemErro("Não encontrado.");
            }
        } else if (tipo == 2) {
            long id = Teclado.readInt("ID do Funcionário: ");
            Funcionario f = funcionarioController.buscarFuncionario(id);
            if (f != null) {
                String nome = Teclado.readString("Novo Nome: ");
                String cargo = Teclado.readString("Novo Cargo: ");
                funcionarioController.atualizarFuncionario(id, nome, cargo);
                Video.mensagemOk("Atualizado!");
            } else {
                Video.mensagemErro("Não encontrado.");
            }
        }
        Video.pausa();
    }
}