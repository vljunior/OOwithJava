package modelo;

import java.io.Serializable;

public class Ingresso implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Participante participante;
    private String tipo;  // Inteira, Meia, VIP
    private double valor;

    public Ingresso() {}
    public Ingresso(Participante participante, String tipo, double valor) {
        this.participante = participante;
        this.tipo = tipo;
        this.valor = valor;
    }
    public Participante getParticipante() { 
        return participante; 
    }  
    public void setParticipante(Participante participante) { 
        this.participante = participante; 
    }
    public String getTipo() { 
        return tipo; 
    }
    public void setTipo(String tipo) { 
        this.tipo = tipo; 
    }
    public double getValor() { 
        return valor; 
    }   
    public void setValor(double valor) { 
        this.valor = valor; 
    }
    @Override
    public String toString() {
        return "Participante: " + participante.getNome() + 
               " | Tipo: " + tipo + " | Valor: R$ " + String.format("%.2f", valor);
    }
}