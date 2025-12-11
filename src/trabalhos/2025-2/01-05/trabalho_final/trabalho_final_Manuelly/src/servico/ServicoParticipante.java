package servico;

import modelo.Participante;
import java.io.*;
import java.util.ArrayList;

public class ServicoParticipante implements IGerenciavel<Participante> {
    private ArrayList<Participante> participantes;
    // Nome do arquivo onde os dados serão gravados
    private static final String ARQUIVO = "participantes.dat";

    public ServicoParticipante() {
        participantes = new ArrayList<>();
        carregar();
    }
    
    @Override
    public void cadastrar(Participante participante) {
        // Adiciona o novo participante na lista
        participantes.add(participante);
        salvar();
    }
    
    @Override
    public Participante buscar(String nome) throws EntidadeNaoEncontradaException {
        // procura na lista para buscar o participante pelo nome
        for (Participante p : participantes) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p; // Retorna se encontrar
            }
        }
        // Se sair do loop sem encontrar, emite essa mensagem
        throw new EntidadeNaoEncontradaException("Participante não encontrado");
    }
    
    @Override
    public ArrayList<Participante> listarTodos() {
        return participantes;
    }
    
    @Override
    public void atualizar(Participante participante) throws EntidadeNaoEncontradaException {
        Participante p = buscar(participante.getNome());
        p.setIdade(participante.getIdade());
        salvar();
    }
    
    @Override
    public void excluir(String nome) throws EntidadeNaoEncontradaException {
        // Busca o objeto para ter certeza que existe
        Participante p = buscar(nome);
        // Remove o objeto da lista
        participantes.remove(p);
        salvar();
    }
    @Override
    public void salvar() {
        try {
            FileOutputStream arquivo = new FileOutputStream(ARQUIVO);
            ObjectOutputStream obj = new ObjectOutputStream(arquivo);
            obj.writeObject(participantes); 
            obj.close();
            arquivo.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar participantes");
        }
    }
    
    @Override
    @SuppressWarnings("unchecked") // é para avisar 
    public void carregar() {
        try {
            FileInputStream arquivo = new FileInputStream(ARQUIVO);
            ObjectInputStream obj = new ObjectInputStream(arquivo);
            participantes = (ArrayList<Participante>) obj.readObject();
            obj.close();
            arquivo.close();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            System.out.println("Erro ao carregar participantes");
        }
    }
}