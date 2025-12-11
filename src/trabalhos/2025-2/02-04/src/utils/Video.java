package utils;

/**
 * Implementação simples de utilitários de vídeo/console usados pelos Menus.
 */
public class Video {

    public static void limparTela() {
        // Simples: gera várias linhas em branco
        for (int i = 0; i < 40; i++) System.out.println();
    }

    public static void cabecalho(String titulo) {
        System.out.println("================ " + titulo + " ================");
    }

    public static void mensagemAlerta(String msg) {
        System.out.println("[ALERTA] " + msg);
    }
}
