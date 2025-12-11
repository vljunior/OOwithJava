package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Apresentacao implements Serializable {
    private static final long serialVersionUID = 1L;
    private String horario;
    private Artista artista;
    private int quantidadeIngressos;
    private ArrayList<Ingresso> ingressos;

    public Apresentacao() {
        this.ingressos = new ArrayList<>();
    }

    public Apresentacao(String horario, Artista artista, int quantidadeIngressos) {
        this.horario = horario;
        this.artista = artista;
        this.quantidadeIngressos = quantidadeIngressos;
        this.ingressos = new ArrayList<>();
    }

    public String getHorario() { 
        return horario; 
    }
    
    public void setHorario(String horario) { 
        this.horario = horario; 
    }

    public Artista getArtista() { 
        return artista; 
    }
    
    public void setArtista(Artista artista) { 
        this.artista = artista; 
    }

    public int getQuantidadeIngressos() { 
        return quantidadeIngressos; 
    }
    
    public void setQuantidadeIngressos(int quantidade) { 
        this.quantidadeIngressos = quantidade; 
    }
    
    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }
    public void adicionarIngresso(Ingresso ingresso) {
        if (temIngressosDisponiveis()) {
            ingressos.add(ingresso);
            quantidadeIngressos--;
        }
    }
    public void removerIngresso(Ingresso ingresso) {
        if (ingressos.remove(ingresso)) {
            quantidadeIngressos++;
        }
    }
    public boolean temIngressosDisponiveis() {
        return quantidadeIngressos > 0;
    }   
    public int getIngressosVendidos() {
        return ingressos.size();
    }
    @Override
    public String toString() {
        return "Horário: " + horario + 
               " | Artista: " + artista.getNome() + 
               " | Disponíveis: " + quantidadeIngressos +
               " | Vendidos: " + getIngressosVendidos();
    }
}

