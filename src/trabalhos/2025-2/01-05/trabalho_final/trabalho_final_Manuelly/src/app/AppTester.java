package app;

import temadogrupo.Menus.*;
import servico.*;
import modelo.*;
import util.*;

public class AppTester {
    private static ServicoEvento servicoEvento = new ServicoEvento();
    private static ServicoParticipante servicoParticipante = new ServicoParticipante();
    private static ServicoArtista servicoArtista = new ServicoArtista();
    
    public static void main(String[] args) {
        int opcao;
        // mostra o menu principal na tela
        do {
            opcao = MenuPrincipal.exibir();
            switch (opcao) {
                case 1: menuParticipantes(); break;
                case 2: menuArtistas(); break;
                case 3: menuEventos(); break;
                case 4: menuIngressos(); break;
                case 5: 
                    servicoEvento.salvar();
                    servicoParticipante.salvar();
                    servicoArtista.salvar();
                    System.out.println("Encerrando"); 
                    break;
                default: System.out.println("Opção inválida");
            }
        } while (opcao != 5);
    }
    // Pausa a tela, e continua só se der enter
    private static void pausar() {
        System.out.print("\n Enter para continuar");
        try {
            System.in.read();
            while (System.in.available() > 0) System.in.read();
        } catch (Exception e) {}
    }
    //Participantes 
    private static void menuParticipantes() { 
        int op;
        do {
            op = MenuParticipante.exibir();
            switch (op) {
                case 1: cadastrarParticipante(); break;
                case 2: listarParticipantes(); break;
                case 3: atualizarParticipante(); break;
                case 4: excluirParticipante(); break;
            }
        } while (op != 5);
    }
    private static void cadastrarParticipante() {
        System.out.println("\nCadastrar Participante");
        String nome = Teclado.readString("Nome: ");
        int idade = Teclado.readInt("Idade: ");
        servicoParticipante.cadastrar(new Participante(nome, idade));
        servicoParticipante.salvar(); 
        System.out.println("Participante cadastrado");
        pausar();
    }
    private static void listarParticipantes() {
        System.out.println("\nParticipantes");
        // mostra todos cadastrados da lista
        for (Participante p : servicoParticipante.listarTodos()) 
            System.out.println("  " + p);
        pausar();
    }
    private static void atualizarParticipante() {
        System.out.println("\nAtualizar Participante");
        try {
            String nome = Teclado.readString("Nome do participante: ");
            // Busca e altera para o novo participante
            Participante p = servicoParticipante.buscar(nome); 
            p.setIdade(Teclado.readInt("Nova idade: "));
            servicoParticipante.atualizar(p);
            servicoParticipante.salvar(); 
            System.out.println("Participante atualizado");
            
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        pausar();
    }
    private static void excluirParticipante() {
        System.out.println("\nExcluir Participante");
        try {
            String nome = Teclado.readString("Nome para excluir: ");
            servicoParticipante.buscar(nome);
            if (Teclado.readString("Confirma exclusão (s/n): ").equalsIgnoreCase("s")) {
                servicoParticipante.excluir(nome);
                servicoParticipante.salvar(); 
                System.out.println("Participante excluído com sucesso");
            } else {
                System.out.println("Operação cancelada.");
            }
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        pausar();
    }
    //Artistas
    private static void menuArtistas() { 
        int op;
        do {
            op = MenuArtista.exibir();
            switch (op) {
                case 1: cadastrarArtista(); break;
                case 2: listarArtistas(); break;
            }
        } while (op != 3);
    }
    private static void cadastrarArtista() {
        System.out.println("\n Cadastrar Artista");
        String nome = Teclado.readString("Nome: ");
        String genero = Teclado.readString("Gênero musical: ");
        servicoArtista.cadastrar(new Artista(nome, genero));
        servicoArtista.salvar(); 
        System.out.println("Artista cadastrado");
        pausar();
    }
    private static void listarArtistas() {
        System.out.println("\nArtistas");
        for (Artista a : servicoArtista.listarTodos()) 
            System.out.println("  " + a);
        pausar();
    }
    //Eventos
    private static void menuEventos() { 
        int op;
        do {
            op = MenuEvento.exibir();
            switch (op) {
                case 1: cadastrarEvento(); break;
                case 2: listarEventos(); break;
                case 3: adicionarApresentacao(); break; 
                case 4: listarApresentacoes(); break;
            }
        } while (op != 5);
    }
    private static void cadastrarEvento() {
        System.out.println("\nCadastrar Evento");
        String nome = Teclado.readString("Nome: ");
        String data = Teclado.readString("Data: ");
        String local = Teclado.readString("Local: ");
        servicoEvento.cadastrar(new Evento(nome, data, local));
        servicoEvento.salvar(); 
        System.out.println("Evento cadastrado ");
        pausar();
    }
    private static void listarEventos() {
        System.out.println("\nEventos");
        for (Evento e : servicoEvento.listarTodos()) 
            System.out.println("  " + e);
        pausar();
    }
    private static void adicionarApresentacao() {
        System.out.println("\n Adicionar Apresentação");
        
        try {
            String nomeEvento = Teclado.readString("Evento: ");
            String nomeArtista = Teclado.readString("Artista: ");
            
            Artista artista = servicoArtista.buscar(nomeArtista);
            String horario = Teclado.readString("Horário: ");
            int ingressos = Teclado.readInt("Quantidade de ingressos: ");
            
            servicoEvento.adicionarApresentacao(nomeEvento, 
                new Apresentacao(horario, artista, ingressos));
            servicoEvento.salvar(); 
            System.out.println("Apresentação e ingressos feitos ");
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        pausar();
    }
    private static void listarApresentacoes() {
        System.out.println("\nApresentações");
        try {
            String nomeEvento = Teclado.readString("Evento: ");
            Evento evento = servicoEvento.buscar(nomeEvento);
            // Lista apresentações dentro do Evento
            for (Apresentacao a : evento.getApresentacoes()) 
                System.out.println("  " + a);
            
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        pausar();
    }
    //Ingressos
    private static void menuIngressos() { 
        int op;
        do {
            op = MenuIngresso.exibir();
            switch (op) {
                case 1: venderIngresso(); break;
                case 2: listarIngressos(); break;
            }
        } while (op != 3);
    }
    private static void venderIngresso() {
        System.out.println("\nVender Ingresso");
        
        try {
            // Busca Evento e Apresentação
            String nomeEvento = Teclado.readString("Evento: ");
            Evento evento = servicoEvento.buscar(nomeEvento);
            String horario = Teclado.readString("Horário da Apresentação: ");
            Apresentacao apresentacao = evento.buscarApresentacao(horario);
            
            // verifica disponibilidade
            if (apresentacao == null || !apresentacao.temIngressosDisponiveis()) {
                System.out.println("Apresentação indisponível ou lotada.");
                pausar();
                return;
            }
            String nomeParticipante = Teclado.readString("Nome do Participante: ");
            Participante participante = servicoParticipante.buscar(nomeParticipante);
            // define tipo e valor
            int tipo = Teclado.readInt("Tipo (1, 2 ou 3): ");
            String tipoNome;
            double valor;
            switch (tipo) {
                case 1: tipoNome = "Inteira"; valor = 100.0; break;
                case 2: tipoNome = "Meia"; valor = 50.0; break;
                case 3: tipoNome = "VIP"; valor = 200.0; break;
                default:
                    System.out.println("Tipo inválido! Venda cancelada.");
                    pausar();
                    return;
            }
            // Cria o Ingresso
            Ingresso ingresso = new Ingresso(participante, tipoNome, valor); 
            apresentacao.adicionarIngresso(ingresso);
            servicoEvento.salvar(); // Salva a venda
            
            System.out.println("Ingresso vendido!");
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        pausar();
    }
    private static void listarIngressos() {
        System.out.println("\nIngressos Vendidos");
        int total = 0;
        for (Evento evento : servicoEvento.listarTodos()) {
            for (Apresentacao apresentacao : evento.getApresentacoes()) {
                for (Ingresso i : apresentacao.getIngressos()) {
                    System.out.println("  " + i);
                    total++;
                }
            }
        }
        System.out.println("Total de ingressos vendidos: " + total);
        pausar();
    }
}