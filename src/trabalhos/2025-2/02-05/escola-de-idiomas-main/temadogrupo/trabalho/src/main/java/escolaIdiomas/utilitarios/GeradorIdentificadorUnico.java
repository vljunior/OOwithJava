package escolaIdiomas.utilitarios;

import java.util.UUID;/* Importa a classe UUID do pacote java.util */
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Classe responsável por gerar identificadores únicos.
 * Esta classe contém métodos para gerar identificadores únicos
 * em diferentes formatos: String (UUID), int e long.
 */

public final class GeradorIdentificadorUnico {

    // Contadores atômicos para IDs inteiros
    private static final AtomicInteger contadorInt = new AtomicInteger(2025000);
    private static final AtomicLong contadorLong = new AtomicLong(2025000);

    private GeradorIdentificadorUnico() {
        // Impede instanciação
    }

    /**
     * Gera um identificador único em formato String (UUID).
     *
     * @return Uma string representando um identificador único.
     */
    public static String gerarIDUnicoString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * Gera um identificador único em formato int.
     * Reinicia a contagem ao ultrapassar Integer.MAX_VALUE.
     *
     * @return Um inteiro representando um identificador único.
     */
    public static int gerarIDUnicoInt() {
        return contadorInt.incrementAndGet();
    }

    public static void setValorInicialInt(int valor) {
    contadorInt.set(valor);
    }

    public static void setValorInicialLong(long valor) {
    contadorLong.set(valor);
    }


    /**
     * Gera um identificador único em formato long.
     * Mais seguro que int, pois suporta números muito maiores.
     *
     * @return Um long representando um identificador único.
     */
    public static long gerarIDUnicoLong() {
        return contadorLong.incrementAndGet();
    }
}