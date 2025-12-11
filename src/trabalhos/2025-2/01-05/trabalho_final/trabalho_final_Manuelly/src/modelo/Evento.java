package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String data;
    private String local;
    
    // Se o evento for excluído, as apresentações também são, pois apresentação e evento são ligadas
    private ArrayList<Apresentacao> apresentacoes;

    public Evento() {
        this.apresentacoes = new ArrayList<>();
    }
    public Evento(String nome, String data, String local) {
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.apresentacoes = new ArrayList<>();
    }
    public String getNome() { 
        return nome; 
    }
    public void setNome(String nome) { 
        this.nome = nome; 
    }
    public String getData() { 
        return data; 
    }
    public void setData(String data) { 
        this.data = data; 
    }
    public String getLocal() { 
        return local; 
    }
    public void setLocal(String local) { 
        this.local = local; 
    }
    public ArrayList<Apresentacao> getApresentacoes() {
        return apresentacoes;
    }
    public void adicionarApresentacao(Apresentacao apresentacao) {
        apresentacoes.add(apresentacao);
    }
    public void removerApresentacao(Apresentacao apresentacao) {
        apresentacoes.remove(apresentacao);
    }
    public Apresentacao buscarApresentacao(String horario) {
        for (Apresentacao a : apresentacoes) {
            if (a.getHorario().equals(horario)) {
                return a;
            }
        }
        return null;
    }
    public void limparApresentacoes() {
        apresentacoes.clear();
    }
    @Override
    public String toString() {
        return nome + " - " + data + " - " + local + 
               " | Apresentações: " + apresentacoes.size();
    }
}
