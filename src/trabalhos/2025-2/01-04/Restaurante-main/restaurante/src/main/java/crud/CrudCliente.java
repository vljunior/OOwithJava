package crud;
import java.util.ArrayList;
import utilitarios.*;
import modelos.Cliente;
import java.io.*;

public class CrudCliente {
    private ArrayList<Cliente> clientes;
    private static final String ARQUIVO_DADOS = "clientes.dat";

    public CrudCliente() {
        clientes = new ArrayList<>();
        carregarClientes();
    }

    public void adicionarCliente() {
        Video.limparTela();
        Video.separador();
        Video.cabecalho("Cadastro Cliente");

        String nome = Teclado.readString("Digite seu nome: ");
        int idade;
        while (true) {
            try {
                idade = Teclado.readInt("Digite sua idade: ");
                if (idade <= 0 || idade >= 100) {
                    Video.mensagemErro("A idade deve ser maior que 0 e menor que 100. Tente novamente.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                Video.mensagemErro("Entrada inválida! Digite apenas números.");
            }
        }
        
        String cpf = Teclado.readString("Digite seu CPF: ");
        if (buscarClientePorCpf(cpf) != null) {
            Video.mensagemErro("CPF já cadastrado!");
            Video.pausa();
            return;
        }
        
        String numeroTelefone = Teclado.readString("Digite seu número de telefone:");
        Cliente novoCliente = new Cliente(nome, idade, cpf, numeroTelefone);
        clientes.add(novoCliente);
        
        salvarClientes();

        System.out.println();
        Video.barraProgresso(20,80);
        System.out.println();
        Video.mensagemOk("Cliente adicionado com sucesso!");
        Video.finalizarTela();
    }

    public void listarClientes() {
        Video.limparTela();
        Video.separador();
        Video.cabecalho("Lista de Clientes");

        if (clientes.isEmpty()) {
            Video.mensagemAlerta("Nenhum cliente cadastrado.");
        } else {
            for (int i = 0; i < clientes.size(); i++) {
                Cliente cliente = clientes.get(i);
                System.out.println((i + 1) + ". " + cliente);
            }
        }
        Video.pausa();
    }

    public void atualizarCliente() {
        Video.limparTela();
        Video.separador();
        Video.cabecalho("Atualizar Cliente");

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            Video.finalizarTela();
            return;
        }

        listarClientes();
        String cpfCliente = Teclado.readString("Digite o número do cpf cliente que deseja atualizar: ");
        
        if (buscarClientePorCpf(cpfCliente)==null) {
            Video.mensagemErro("Cliente inválido.");
        } else {
            Cliente cliente = clientes.get(clientes.indexOf(buscarClientePorCpf(cpfCliente)));

            String nome = Teclado.readString("Digite o novo nome (" + cliente.getNome() + "): ");
            int idade = Teclado.readInt("Digite a nova idade (" + cliente.getIdade() + "): ");
            String cpf = Teclado.readString("Digite o novo CPF (" + cliente.getCpf() + "): ");
            String numeroTelefone = Teclado.readString("Digite o novo número de telefone (" + cliente.getNumeroTelefone() + "): ");

            if (!nome.isEmpty()) cliente.setNome(nome);
            if (idade > 0) cliente.setIdade(idade);
            if (!cpf.isEmpty()) cliente.setCpf(cpf);
            if (!numeroTelefone.isEmpty()) cliente.setNumeroTelefone(numeroTelefone);

            salvarClientes();

            Video.mensagemOk("Cliente atualizado com sucesso!");
        }
        Video.finalizarTela();
    }

    public void deletarCliente() {
        Video.limparTela();
        Video.separador();
        Video.cabecalho("Deletar Cliente");

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            Video.finalizarTela();
            return;
        }

        listarClientes();
        String cpfCliente = Teclado.readString("Digite o número do cpf cliente que deseja deletar: ");

        if (buscarClientePorCpf(cpfCliente)==null) {
            Video.mensagemErro("Cliente inválido.");
        } else {
            clientes.remove(buscarClientePorCpf(cpfCliente));
            salvarClientes();
            Video.mensagemOk("Cliente deletado com sucesso!");
        }
        
        Video.pausa();
    }

    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
 
                return cliente;
            }
        }
        return null;
    }

    public int getQuantidadeClientes() {
        return clientes.size();
    }

    private void salvarClientes() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DADOS))) {
            outputStream.writeObject(clientes);
            Video.mensagemOk("Dados dos clientes salvos com sucesso.");
        } catch (IOException e) {
            Video.mensagemErro("Erro ao salvar dados dos clientes: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarClientes() {
        File arquivo = new File(ARQUIVO_DADOS);
        if (!arquivo.exists()) {
            Video.mensagemAlerta("Arquivo de dados dos clientes não encontrado. Iniciando com lista vazia.");
            return;
        }
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ARQUIVO_DADOS))) {
            clientes = (ArrayList<Cliente>) inputStream.readObject();
            Video.mensagemOk("Dados dos clientes carregados com sucesso." + clientes.size() + " clientes encontrados.");
        } catch (IOException | ClassNotFoundException e) {
            Video.mensagemErro("Erro ao carregar dados dos clientes: " + e.getMessage());
            clientes = new ArrayList<>();
        }
    }
}