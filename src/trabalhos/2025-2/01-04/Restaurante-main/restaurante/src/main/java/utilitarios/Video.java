package utilitarios;

public class Video {

    private static final int WIDTH_TELA = 100;

    // --- Códigos ANSI para cores e estilos ---
    public static final String RESET = "\u001B[0m";
    public static final String NEGRITO = "\u001B[1m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String CIANO = "\u001B[36m";

    
      
    public static void limparTela() {
        System.out.println("\033[H\033[2J"); // limpar terminal antes de começar
        System.out.flush();
    }

    // --- Cabeçalho ---
    public static void cabecalho(String titulo) {
        limparTela();
        final int larguraTotal = WIDTH_TELA; 
        String tituloMaiusculo = titulo.toUpperCase();
        int espacos = (larguraTotal - tituloMaiusculo.length()) / 2;

        String linha = "=".repeat(larguraTotal);
        String espacosEsquerda = " ".repeat(espacos);

        System.out.println(NEGRITO + linha + RESET);
        System.out.println(espacosEsquerda + tituloMaiusculo);
        System.out.println(NEGRITO + linha + "\n" + RESET);
    }

    // --- Rodapé ---
    public static void rodape(String texto) {
        final int larguraTotal = WIDTH_TELA; 
        int espacos = (larguraTotal - texto.length()) / 2;

        String linha = "=".repeat(larguraTotal);
        String espacosEsquerda = " ".repeat(espacos);

        System.out.println("\n" + NEGRITO + linha + RESET);
        System.out.println(espacosEsquerda + texto);
        System.out.println(NEGRITO + linha + RESET);
    }

    // --- Linha separadora ---
    public static void separador(int largura) {
        if (largura > 0) {
            String linha = "-".repeat(largura);
            System.out.println(linha);
        }
    }

    public static void separador() {
        separador(WIDTH_TELA);
    }

    // --- Pausa para leitura ---
    public static void pausa() {
        try {
            System.out.println("\nPressione ENTER para continuar...");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- Escrever texto com efeito de digitação ---
    public static void escreverLento(String texto, int delayMs) {
        for (char c : texto.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    // --- Barra de progresso ---
    public static void barraProgresso(int total, int delayMs) {
        for (int i = 0; i <= total; i++) {
            String barra = "[" + "#".repeat(i) + " ".repeat(total - i) + "]";
            System.out.print("\r" + barra + " " + (i * 100 / total) + "%");
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    // --- Mensagens comum sem cor ---
    public static void mensagem (String msg){
        System.out.println (msg);
    }

    // --- Mensagens coloridas ---
    public static void mensagemOk(String msg) {
        System.out.println(VERDE + "[OK] " + msg + RESET);
    }

    public static void mensagemErro(String msg) {
        System.out.println(VERMELHO + "[ERRO] " + msg + RESET);
    }

    public static void mensagemAlerta(String msg) {
        System.out.println(AMARELO + "[ALERTA] " + msg + RESET);
    }

    public static void mensagemInfo(String msg) {
        System.out.println(AZUL + "[INFO] " + msg + RESET);
    }

    public static void finalizarTela() {
        Video.pausa();
        Video.limparTela();
    }

}
