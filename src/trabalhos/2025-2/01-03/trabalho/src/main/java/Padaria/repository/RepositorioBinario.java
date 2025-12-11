package Padaria.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import Padaria.model.interfaces.Repositorio;

public class RepositorioBinario<T> implements Repositorio<T> {
    private final String arquivo;
    private List<T> dados;
    
    public RepositorioBinario(String nomeArquivo) {
        this.arquivo = "data/" + nomeArquivo + ".dat";
        this.dados = new ArrayList<>(); 
        criarDiretorioData();
        carregarDados();
    }

    private void criarDiretorioData() {
        File diretorio = new File("data");
        if (!diretorio.exists()) {
            boolean criado = diretorio.mkdirs();
            if (criado) {
                System.out.println("Diretório 'data/' criado com sucesso!");
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File file = new File(arquivo);
        System.out.println("Tentando carregar: " + file.getAbsolutePath());
        
        if (!file.exists()) {
            System.out.println("Arquivo não encontrado. Iniciando com lista vazia.");
            this.dados = new ArrayList<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            this.dados = (List<T>) ois.readObject();
            System.out.println("Dados carregados: " + dados.size() + " registros de " + arquivo);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados de " + arquivo + ": " + e.getMessage());
            this.dados = new ArrayList<>();
        }
    }
    
    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(dados);
            System.out.println("Dados salvos com sucesso em: " + arquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados em " + arquivo + ": " + e.getMessage());
        }
    }
    
    @Override
    public void salvar(T entidade) {
        dados.add(entidade);
        salvarDados();
    }
    
    @Override
    public T buscarPorId(int id) {
        return dados.stream()
            .filter(entidade -> {
                try {
                    Field campoId = entidade.getClass().getDeclaredField("id");
                    campoId.setAccessible(true);
                    return campoId.getInt(entidade) == id;
                } catch (Exception e) {
                    return false;
                }
            })
            .findFirst()
            .orElse(null);
    }
    
    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(dados); 
    }
    
    @Override
    public void atualizar(T entidadeAtualizada) {
        try {
            // CORREÇÃO: Obter o Field uma vez para o objeto atualizado
            Field campoIdAtualizado = entidadeAtualizada.getClass().getDeclaredField("id");
            campoIdAtualizado.setAccessible(true);
            int id = campoIdAtualizado.getInt(entidadeAtualizada);
            
            for (int i = 0; i < dados.size(); i++) {
                T entidadeExistente = dados.get(i);
                
                // CORREÇÃO: Obter um Field novo para cada objeto existente
                Field campoIdExistente = entidadeExistente.getClass().getDeclaredField("id");
                campoIdExistente.setAccessible(true);
                int idExistente = campoIdExistente.getInt(entidadeExistente);
                
                if (idExistente == id) {
                    dados.set(i, entidadeAtualizada);
                    salvarDados();
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
            e.printStackTrace(); // Mostra stack trace completo para debug
        }
    }
    
    @Override
    public void deletar(int id) {
        boolean removido = dados.removeIf(entidade -> {
            try {
                Field campoId = entidade.getClass().getDeclaredField("id");
                campoId.setAccessible(true);
                return campoId.getInt(entidade) == id;
            } catch (Exception e) {
                return false;
            }
        });
        
        if (removido) {
            salvarDados();
            System.out.println("Item removido com sucesso!");
        } else {
            System.out.println("Item não encontrado!");
        }
    }
    
    @Override
    public int quantidade() {
        return dados.size();
    }
}