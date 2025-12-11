package servico;

import modelo.Artista;
import java.io.*;
import java.util.ArrayList;

public class ServicoArtista implements IGerenciavel<Artista> {
    private ArrayList<Artista> artistas;
    private static final String ARQUIVO = "artistas.dat"; // Nome do arquivo onde os dados serão gravados


    public ServicoArtista() {
        artistas = new ArrayList<>();
        carregar();
    }
    @Override
    public void cadastrar(Artista artista) {
        artistas.add(artista);
        salvar();
    }
    @Override
    public Artista buscar(String nome) throws EntidadeNaoEncontradaException {
        for (Artista a : artistas) {
            if (a.getNome().equalsIgnoreCase(nome)) {
                return a;
            }
        }
        throw new EntidadeNaoEncontradaException("Artista não encontrado");
    }
    @Override
    public ArrayList<Artista> listarTodos() {
        return artistas;
    }
    @Override
    public void atualizar(Artista artista) throws EntidadeNaoEncontradaException {
        Artista a = buscar(artista.getNome());
        a.setGeneroMusical(artista.getGeneroMusical());
        salvar();
    }

    @Override
    public void excluir(String nome) throws EntidadeNaoEncontradaException {
        for(int i = 0; i < artistas.size(); i++) {
            if(artistas.get(i).getNome().equalsIgnoreCase(nome)) {
                artistas.remove(i);
                salvar();
                return;
            }
        }
        throw new EntidadeNaoEncontradaException("Artista não encontrado para exclusão");
    }
    @Override
    public void salvar() {
        try {
            FileOutputStream arquivo = new FileOutputStream(ARQUIVO);
            ObjectOutputStream obj = new ObjectOutputStream(arquivo);
            obj.writeObject(artistas);
            obj.close();
            arquivo.close();
        } catch (Exception e) {
            System.out.println("Não foi possível salvar os dados dos artistas.");
        }
    }
    @Override
    @SuppressWarnings("unchecked") // seria algo para silenciar 
    public void carregar() {
        try {
            FileInputStream arquivo = new FileInputStream(ARQUIVO);
            ObjectInputStream obj = new ObjectInputStream(arquivo);
            artistas = (ArrayList<Artista>) obj.readObject();
            obj.close();
            arquivo.close();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            System.out.println("Erro ao carregar os dados dos artistas.");
        }
    }
}
