package gerencia_reserva;

import gerencia_quarto.GerenciadorQuarto;
import gerencia_quarto.Quarto;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import utilitarios.Teclado;
import utilitarios.Video;

public class GerenciadorReserva {

    private final Path repoDir = Paths.get("repositorio");
    private final Path arquivoReservas = repoDir.resolve("reservas.txt");
    private final Path arquivoHospedes = repoDir.resolve("hospedes.txt");
    private GerenciadorQuarto gerenciadorQuarto;

    public GerenciadorReserva(GerenciadorQuarto gerenciadorQuarto) {
        this.gerenciadorQuarto = gerenciadorQuarto;
        inicializarArquivos();
    }

    private void inicializarArquivos() {
        try {
            if (Files.notExists(repoDir)) Files.createDirectories(repoDir);
            if (Files.notExists(arquivoReservas)) Files.createFile(arquivoReservas);
            if (Files.notExists(arquivoHospedes)) Files.createFile(arquivoHospedes);
        } catch (IOException e) {
            Video.mensagemErro("Erro crítico no sistema de arquivos: " + e.getMessage());
        }
    }

    public void realizarCheckIn() {
        Video.cabecalho("Check-in");

        int numeroQuarto = Teclado.readInt("Número do quarto:");
        
        Quarto quarto = gerenciadorQuarto.buscarQuarto(numeroQuarto);
        if (quarto == null) {
            Video.mensagemErro("Quarto não existe!");
            return;
        }

        if (!quarto.getStatus().equalsIgnoreCase("Livre")) {
            Video.mensagemAlerta("Quarto indisponível. Status: " + quarto.getStatus());
            return;
        }

        String cpf = Teclado.readString("CPF do Hóspede:");
        if (!hospedeExiste(cpf)) {
            Video.mensagemErro("Hóspede não cadastrado.");
            return;
        }

        Reserva novaReserva = new Reserva(cpf, numeroQuarto);
        salvarNovaReserva(novaReserva);

        try {
            atualizarStatusQuartoArquivo(numeroQuarto, "Ocupado");
        } catch (IOException e) {
            Video.mensagemErro("Erro ao atualizar status do quarto: " + e.getMessage());
        }
        
        Video.mensagemOk("Check-in registrado no banco de dados!");
        Video.mensagemInfo("Reserva ID: " + novaReserva.getId());
        Video.pausa();
    }

    public void realizarCheckOut() {
        Video.cabecalho("Check-out");

        int numeroQuarto = Teclado.readInt("Número do quarto para baixa:");

        Reserva reservaEncontrada = buscarReservaAtiva(numeroQuarto);

        if (reservaEncontrada == null) {
            Video.mensagemErro("Não foi encontrada nenhuma reserva ATIVA para o quarto " + numeroQuarto);
            return;
        }

        Quarto quarto = gerenciadorQuarto.buscarQuarto(numeroQuarto);
        
        System.out.println("---------------------------------------");
        System.out.println("Reserva encontrada: " + reservaEncontrada.getId());
        System.out.println("Hóspede: " + reservaEncontrada.getCpfHospede());
        System.out.println("Data Entrada: " + reservaEncontrada.getDataEntrada());
        System.out.println("Tipo Quarto: " + quarto.getTipo() + " (Diária: R$ " + quarto.getValorDiaria() + ")");
        System.out.println("---------------------------------------");

        int dias = Teclado.readInt("Quantas diárias serão cobradas?");
        if (dias < 1) dias = 1;

        reservaEncontrada.finalizarReserva(dias, quarto.getValorDiaria());

        atualizarReservaNoArquivo(reservaEncontrada);

        try {
             atualizarStatusQuartoArquivo(numeroQuarto, "Em Limpeza");
        } catch (Exception e) {
             System.out.println("Erro ao atualizar status do quarto: " + e.getMessage());
        }

        Video.limparTela();
        Video.mensagemOk("Check-out finalizado com sucesso!");
        Video.mensagemInfo("Valor Total Cobrado: R$ " + reservaEncontrada.getValorTotal());
        Video.pausa();
    }

    private void salvarNovaReserva(Reserva r) {
        try {
            Files.write(arquivoReservas, 
                       (r.toString() + System.lineSeparator()).getBytes(StandardCharsets.UTF_8), 
                       StandardOpenOption.APPEND);
        } catch (IOException e) {
            Video.mensagemErro("Erro ao gravar no banco: " + e.getMessage());
        }
    }

    private Reserva buscarReservaAtiva(int numeroQuarto) {
        try {
            List<String> linhas = Files.readAllLines(arquivoReservas, StandardCharsets.UTF_8);
            for (String linha : linhas) {
                if (linha.trim().isEmpty()) continue;
                
                try {
                    Reserva r = Reserva.fromString(linha);
                    if (r.getNumeroQuarto() == numeroQuarto && r.getStatus().equalsIgnoreCase("Ativa")) {
                        return r;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (IOException e) {
            Video.mensagemErro("Erro ao ler banco de reservas.");
        }
        return null;
    }

    private void atualizarReservaNoArquivo(Reserva reservaAtualizada) {
        try {
            List<String> linhas = Files.readAllLines(arquivoReservas, StandardCharsets.UTF_8);
            List<String> novasLinhas = new ArrayList<>();

            for (String linha : linhas) {
                if (linha.trim().isEmpty()) continue;
                
                Reserva rTemp = Reserva.fromString(linha);
                if (rTemp.getId().equals(reservaAtualizada.getId())) {
                    novasLinhas.add(reservaAtualizada.toString());
                } else {
                    novasLinhas.add(linha);
                }
            }

            Files.write(arquivoReservas, novasLinhas, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
            
        } catch (IOException e) {
            Video.mensagemErro("Erro ao atualizar banco de dados: " + e.getMessage());
        }
    }

    private boolean hospedeExiste(String cpf) {
        try {
            List<String> lines = Files.readAllLines(arquivoHospedes, StandardCharsets.UTF_8);
            for (String line : lines) {
                String[] p = line.split(";", -1);
                if (p.length >= 2 && p[1].trim().equals(cpf)) {
                    return true;
                }
            }
        } catch (IOException e) { return false; }
        return false;
    }
    
    private void atualizarStatusQuartoArquivo(int numero, String status) throws IOException {
        Path qFile = repoDir.resolve("quartos.txt");
        List<String> lines = Files.readAllLines(qFile, StandardCharsets.UTF_8);
        List<String> out = new ArrayList<>();
        for(String l : lines) {
            String[] p = l.split(";");
            if(p.length >= 3 && Integer.parseInt(p[0]) == numero) {
                out.add(p[0] + ";" + p[1] + ";" + status);
            } else {
                out.add(l);
            }
        }
        Files.write(qFile, out, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
    }
}