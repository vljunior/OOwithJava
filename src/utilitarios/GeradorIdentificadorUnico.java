package utilitarios;

import java.util.UUID;/* Importa a classe UUID do pacote java.util */

/**
 * Classe responsável por gerar identificadores únicos.
 * Esta classe contém métodos para gerar identificadores únicos.
 */

public final class GeradorIdentificadorUnico {
    
    /**
     * Gera um identificador único.
     *
     * @return Uma string representando um identificador único.
     */
    public static String gerarIDUnicoString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}