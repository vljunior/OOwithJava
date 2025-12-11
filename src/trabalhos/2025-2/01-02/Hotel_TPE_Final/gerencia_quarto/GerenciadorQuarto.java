package gerencia_quarto;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import quartos_tipos.QuartoPlus;
import quartos_tipos.QuartoPremium;
import quartos_tipos.QuartoStandard;
import utilitarios.Teclado;
import utilitarios.Video;

public class GerenciadorQuarto {
    private List<Quarto> quartos;
    private final Path repoDir = Paths.get("repositorio");
    private final Path caminhoArquivo = repoDir.resolve("quartos.txt");

    public GerenciadorQuarto() {
        quartos = new ArrayList<>();
        prepararRepositorio();
        carregarQuartos();
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

    private void carregarQuartos() {
        try (BufferedReader br = Files.newBufferedReader(caminhoArquivo, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 3) {
                    try {
                        int numero = Integer.parseInt(dados[0].trim());
                        String tipo = dados[1].trim();
                        String status = dados[2].trim();
                        if (status.equalsIgnoreCase("Em Limpeza")) status = "Em Limpeza";
                        Quarto q = criarQuartoPorTipo(tipo, numero, status);
                        if (q != null) {
                            quartos.add(q);
                        }
                    } catch (NumberFormatException ex) {
                        Video.mensagemErro("Número de quarto inválido na linha: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            Video.mensagemErro("Erro ao carregar quartos: " + e.getMessage());
        }
    }

    private Quarto criarQuartoPorTipo(String tipo, int numero, String status) {
        switch (tipo.toLowerCase()) {
            case "standard":
                return new QuartoStandard(numero, status);

            case "plus":
                return new QuartoPlus(numero, status);

            case "premium":
                return new QuartoPremium(numero, status);

            default:
                Video.mensagemErro("Tipo inválido: " + tipo);
                return null;
        }
    }



    public void listarQuartos() {
        if (quartos.isEmpty()) {
            Video.mensagemInfo("Nenhum quarto carregado.");
            return;
        }
        Video.cabecalho("\n===== Lista de Quartos =====");
        for (Quarto q : quartos) {
            Video.mensagemInfo(q.exibirInfo());
        }
    }

    
    public Quarto buscarQuarto(int numero) {
        for (Quarto q : quartos) {
            if (q.getNumero() == numero) {
                return q;
            }
        }
        return null;
    }


    public boolean liberarQuarto() {
        int numero = Teclado.readInt("Número do quarto a ser liberado:");
        

        for (Quarto q : quartos) {
            if (q.getNumero() == numero) {

                if (!q.getStatus().equalsIgnoreCase("Em Limpeza")) {
                    Video.mensagemErro("Erro: só é permitido liberar um quarto que esteja Em Limpeza.");
                    return false;
                }

                q.setStatus("Livre");
                Video.mensagemOk("Quarto " + numero + " liberado com sucesso!");
                salvarQuartos();
                return true;
            }
        }
        return false;
    }

    public boolean cadastrarQuarto(int numero, String tipo, String status) {
        for (Quarto q : quartos) {
            if (q.getNumero() == numero) {
                Video.mensagemInfo("Quarto " + numero + " já existe!");
                return false;
            }
        }

        if (status == null) {
            Video.mensagemAlerta("Status inválido! Use 'Livre', 'Ocupado' ou 'Em Limpeza'.");
            return false;
        }

        String s = status.trim();
        if (!s.equalsIgnoreCase("Livre") &&
            !s.equalsIgnoreCase("Ocupado") &&
            !s.equalsIgnoreCase("Em Limpeza")) {

            Video.mensagemAlerta("Status inválido! Use apenas 'Livre', 'Ocupado' ou 'Em Limpeza'.");
            return false;
        }

        Quarto novoQuarto = criarQuartoPorTipo(tipo, numero, status);

        int posicao = 0;
        for (int i = 0; i < quartos.size(); i++) {
            if (quartos.get(i).getNumero() > numero) {
                posicao = i;
                break;
            }
            posicao = i + 1;
        }
        quartos.add(posicao, novoQuarto);

        salvarQuartos();
        return true;
    }

    private void salvarQuartos() {
        try (BufferedWriter bw = Files.newBufferedWriter(caminhoArquivo, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Quarto q : quartos) {
                bw.write(q.getNumero() + ";" + q.getTipo() + ";" + q.getStatus());
                bw.newLine();
            }
        } catch (IOException e) {
            Video.mensagemErro("Erro ao salvar quartos: " + e.getMessage());
        }
    }

    public boolean excluirQuarto(int numero) {
        for (int i = 0; i < quartos.size(); i++) {
            if (quartos.get(i).getNumero() == numero) {
                quartos.remove(i);
                salvarQuartos();
                return true;
            }
        }
        return false;
    }

    public void cadastrarNovoQuarto() {
        Video.cabecalho("\n===== Cadastro de Novo Quarto =====");
        
        int numero = Teclado.readInt("Número do quarto:");
        if (numero <= 0) {
            Video.mensagemAlerta("Número deve ser positivo!");
            return;
        }

        String tipo;
        while(true) {
            tipo = Teclado.readString("Tipo (Standard/Plus/Premium):");
            if (tipo.matches("(?i)^(Standard|Plus|Premium)$")) {
                break;
            }
            Video.mensagemAlerta("Tipo inválido! Use Standard, Plus ou Premium.");
        }

        String status = "Livre";
        

        if (cadastrarQuarto(numero, tipo, status)) {
            Video.mensagemOk("Quarto cadastrado com sucesso!");
        } else {
            Video.mensagemErro("Falha ao cadastrar quarto (talvez já exista).");
        }

        Video.pausa();
    }

    public void excluirQuarto() {
        Video.cabecalho("\n===== Exclusão de Quarto =====");
        
        int numero = Teclado.readInt("Número do quarto a excluir:");
        
        String confirmacao = Teclado.readString("Tem certeza que deseja excluir o quarto " + numero + "? (S/N):");
        
        if (confirmacao.equalsIgnoreCase("S")) {
            if (excluirQuarto(numero)) {
                Video.mensagemOk("Quarto " + numero + " excluído com sucesso!");
            } else {
                Video.mensagemAlerta("Quarto " + numero + " não encontrado!");
            }
        } else {
            Video.mensagemInfo("Exclusão cancelada.");
        }
        Video.pausa();
    }
}

