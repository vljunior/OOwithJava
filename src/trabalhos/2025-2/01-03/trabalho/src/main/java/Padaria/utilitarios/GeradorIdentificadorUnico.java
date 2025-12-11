package Padaria.utilitarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Classe responsável por gerar identificadores únicos.
 * Esta classe contém métodos para gerar identificadores únicos
 * em diferentes formatos: String (UUID), int e long.
 */

public final class GeradorIdentificadorUnico {

    // ALTERAÇÃO: Mapa para armazenar os últimos IDs por tipo de entidade
    private static final ConcurrentHashMap<String, AtomicInteger> contadoresPorTipo = new ConcurrentHashMap<>();
    
    // Tipos de entidade suportados
    private static final String TIPO_CLIENTE = "cliente";
    private static final String TIPO_PRODUTO = "produto";
    private static final String TIPO_INSUMO = "insumo";
    
    // Contador atômico para IDs long (mantido para compatibilidade)
    private static final AtomicLong contadorLong = new AtomicLong(0);
    
    static {
        // Inicializa os contadores carregando dos arquivos
        inicializarContadores();
    }

    private GeradorIdentificadorUnico() {
        // Impede instanciação
    }

    private static synchronized void inicializarContadores() {
        contadoresPorTipo.put(TIPO_CLIENTE, new AtomicInteger(encontrarProximoIDDisponivel("clientes")));
        contadoresPorTipo.put(TIPO_PRODUTO, new AtomicInteger(encontrarProximoIDDisponivel("produtos")));
        contadoresPorTipo.put(TIPO_INSUMO, new AtomicInteger(encontrarProximoIDDisponivel("insumos")));
    }

    private static int encontrarProximoIDDisponivel(String tipoArquivo) {
        List<Integer> idsExistentes = new ArrayList<>();
        File arquivo = new File("data/" + tipoArquivo + ".dat");
        
        if (!arquivo.exists()) {
            return 1; 
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            List<?> itens = (List<?>) ois.readObject();
            
            for (Object item : itens) {
                try {
                    java.lang.reflect.Field campoId = item.getClass().getDeclaredField("id");
                    campoId.setAccessible(true);
                    int id = campoId.getInt(item);
                    idsExistentes.add(id);
                } catch (Exception e) {
                    // Ignora se não encontrar campo id
                }
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao ler " + arquivo.getName() + ": " + e.getMessage());
            return 1; // Em caso de erro, começa do 1
        }
        
        // Se não há itens, começa do 1
        if (idsExistentes.isEmpty()) {
            return 1;
        }
        
        int maiorID = idsExistentes.stream().max(Integer::compareTo).orElse(0);
        
        // Procura por IDs faltantes (buracos na sequência)
        for (int i = 1; i <= maiorID; i++) {
            if (!idsExistentes.contains(i)) {
                return i; // Retorna o primeiro ID disponível
            }
        }
        // Se não há buracos, usa o próximo sequencial
        return maiorID + 1;
    }

    /**
     * Gera um identificador único para Cliente.
     */
    public static synchronized int gerarIDCliente() {
        AtomicInteger contador = contadoresPorTipo.get(TIPO_CLIENTE);
        int novoID = contador.get();
        
        // Atualiza para o próximo ID disponível
        contador.set(encontrarProximoIDDisponivel("clientes"));
        return novoID;
    }

    /**
     * Gera um identificador único para Produto.
     */
    public static synchronized int gerarIDProduto() {
        AtomicInteger contador = contadoresPorTipo.get(TIPO_PRODUTO);
        int novoID = contador.get();
        
        contador.set(encontrarProximoIDDisponivel("produtos"));
        return novoID;
    }

    /**
     * Gera um identificador único para Insumo.
     */
    public static synchronized int gerarIDInsumo() {
        AtomicInteger contador = contadoresPorTipo.get(TIPO_INSUMO);
        int novoID = contador.get();
        
        // Atualiza para o próximo ID disponível
        contador.set(encontrarProximoIDDisponivel("insumos"));
        return novoID;
    }

    /**
     * Gera um identificador único em formato String (UUID).
     */
    public static String gerarIDUnicoString() {
        return UUID.randomUUID().toString();
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
    
    /**
     * MÉTODO NOVO: Força a recarga dos contadores (útil após deleções)
     */
    public static synchronized void recarregarContadores() {
        inicializarContadores();
    }
}