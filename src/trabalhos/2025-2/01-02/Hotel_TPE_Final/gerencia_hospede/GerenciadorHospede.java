package gerencia_hospede;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import javax.naming.NameNotFoundException;
import utilitarios.Teclado;
import utilitarios.Video;  


public class GerenciadorHospede {
    private List<Hospede> hospedes;
    private final Path repoDir = Paths.get("repositorio");
    private final Path caminhoArquivo = repoDir.resolve("hospedes.txt");

    public GerenciadorHospede() {
        hospedes = new ArrayList<>();
        prepararRepositorio();
        carregarHospedes();
    }

    private void prepararRepositorio() {
        try {
            if (Files.notExists(repoDir)) {
                Files.createDirectories(repoDir);
            }
            if (Files.notExists(caminhoArquivo)) {
                Files.createFile(caminhoArquivo);
            }
        } catch (IOException e) {
            Video.mensagemErro("Erro ao preparar repositório: " + e.getMessage());
        }
    }

    private void carregarHospedes() {
        try (BufferedReader br = Files.newBufferedReader(caminhoArquivo, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 3) {
                    try {
                        String nome = dados[0].trim();
                        String cpf = dados[1].trim();
                        String telefone = dados[2].trim();
                        
                        if (nome.isEmpty()) {
                            throw new NameNotFoundException();
                        }

                        hospedes.add(new Hospede(nome, cpf, telefone));

                    } catch (NameNotFoundException ex) {
                        Video.mensagemErro("Nome de hóspede inexistente na linha: " + linha);
                    }
                }
            }

        } catch (IOException e) {
            Video.mensagemErro("Erro ao carregar hóspedes: " + e.getMessage());
        }
    }

    public void listarHospedes() {
        if (hospedes.isEmpty()) {
            Video.mensagemErro("Nenhum hóspede carregado.");
            return;
        }
        Video.cabecalho("\n===== Lista de Hóspedes =====");
        for (Hospede h : hospedes) {
            Video.mensagemInfo(h.exibirInfo());
        }
    }

    public boolean cadastrarHospede(String nome, String cpf, String telefone) {
        for (Hospede h : hospedes) {
            if (h.getCpf().equals(cpf)) {
                Video.mensagemInfo("Hóspede com CPF " + cpf + " já cadastrado.");
                return false;
            }
        }

        Hospede novoHospede = new Hospede(nome, cpf, telefone);
        
        hospedes.add(novoHospede);
        
        salvarHospedes();
        return true;
    }

    private void salvarHospedes() {
        try (BufferedWriter bw = Files.newBufferedWriter(caminhoArquivo, StandardCharsets.UTF_8, 
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Hospede h : hospedes) {
                bw.write(h.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            Video.mensagemErro("Erro ao salvar hóspedes: " + e.getMessage());
        }
    }

    public boolean excluirHospede(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) return false;
        String cpfNorm = cpf.trim();

        for (Iterator<Hospede> it = hospedes.iterator(); it.hasNext(); ) {
            Hospede h = it.next();
            if (h.getCpf().equalsIgnoreCase(cpfNorm)) {
                it.remove();
                salvarHospedes();
                return true;
            }
        }
        return false;
    }

    public void buscarHospede(String cpf) {
        Video.cabecalho("\n===== Buscar Hóspede =====");
        for (Hospede h : hospedes) {
            if (h.getCpf().equals(cpf)) {
                Video.mensagemOk("\n===== Hóspede Encontrado =====");
                Video.mensagemInfo(h.exibirInfo());
                break;
            } else {
                Video.mensagemAlerta("Hóspede com CPF " + cpf + " não encontrado.");    
            }
        }
    }

    public void cadastrarNovoHospede() {
        Video.cabecalho("\n===== Cadastro de Novo Hóspede =====");
        
        String nome = Teclado.readString("Nome:");
        String cpf = Teclado.readString("CPF:");
        String telefone = Teclado.readString("Telefone:");
        
        if(nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            Video.mensagemAlerta("Dados inválidos. Tente novamente.");
            return;
        }

        if (cadastrarHospede(nome, cpf, telefone)) {
            Video.mensagemOk("Hóspede cadastrado com sucesso!");
        } else {
            Video.mensagemErro("Falha ao cadastrar (CPF já existe ou erro no arquivo).");
        }
    }

    public void excluirHospede() {
        Video.cabecalho("\n===== Exclusão de Hóspede =====");
        
        String cpf = Teclado.readString("CPF do hóspede a excluir:");
        
        String confirmacao = Teclado.readString("Tem certeza que deseja excluir o hóspede CPF " + cpf + "? (S/N):");
        
        if (confirmacao.equalsIgnoreCase("S")) {
            if (excluirHospede(cpf)) {
                Video.mensagemAlerta("Hóspede removido!");
            } else {
                Video.mensagemErro("Hóspede não encontrado.");
            }
        } else {
            Video.mensagemInfo("Operação cancelada.");
        }
    }

    
    public void buscarHospede() {
        Video.cabecalho("\n===== Buscar Hospede =====");
        String cpf = Teclado.readString("Digite o CPF:");
        buscarHospede(cpf);
    }

}
