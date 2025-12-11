package servico;

import modelo.Evento;
import modelo.Apresentacao;
import java.io.*;
import java.util.ArrayList;

public class ServicoEvento implements IGerenciavel<Evento> {
    private ArrayList<Evento> eventos;
    private static final String ARQUIVO = "eventos.dat"; // Nome do arquivo onde os dados serão gravados


    public ServicoEvento() {
        eventos = new ArrayList<>();
        carregar();
    }
    @Override
    public void cadastrar(Evento evento) {
        eventos.add(evento);
        salvar();
    }
    @Override
    public Evento buscar(String nome) throws EntidadeNaoEncontradaException {
        for (Evento e : eventos) {
            if (e.getNome().equalsIgnoreCase(nome)) {
                return e;
            }
        }
        throw new EntidadeNaoEncontradaException("Evento não encontrado");
    }
    @Override
    public ArrayList<Evento> listarTodos() {
        return eventos;
    }
    @Override
    public void atualizar(Evento evento) throws EntidadeNaoEncontradaException {
        Evento e = buscar(evento.getNome());
        e.setData(evento.getData());
        e.setLocal(evento.getLocal());
        salvar();
    }
    @Override
    public void excluir(String nome) throws EntidadeNaoEncontradaException {
        Evento e = buscar(nome);
        eventos.remove(e);
        salvar();
    }
    public void adicionarApresentacao(String nomeEvento, Apresentacao apresentacao) 
            throws EntidadeNaoEncontradaException {
        Evento evento = buscar(nomeEvento);
        evento.adicionarApresentacao(apresentacao);
        salvar();
    }

    public void removerApresentacao(String nomeEvento, String horario) 
            throws EntidadeNaoEncontradaException {
        Evento evento = buscar(nomeEvento);
        Apresentacao apresentacao = evento.buscarApresentacao(horario);
        if (apresentacao != null) {
            evento.removerApresentacao(apresentacao);
            salvar();
        }
    }
    @Override
    public void salvar() {
        try {
            FileOutputStream arquivo = new FileOutputStream(ARQUIVO);
            ObjectOutputStream obj = new ObjectOutputStream(arquivo);
            obj.writeObject(eventos);
            obj.close();
            arquivo.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar eventos");
        }
    }
    @Override
    @SuppressWarnings("unchecked")// seria algo para silenciar 
    public void carregar() {
        try {
            FileInputStream arquivo = new FileInputStream(ARQUIVO);
            ObjectInputStream obj = new ObjectInputStream(arquivo);
            eventos = (ArrayList<Evento>) obj.readObject();
            obj.close();
            arquivo.close();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            System.out.println("Erro ao carregar eventos");
        }
    }
}