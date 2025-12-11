package quartos_tipos;
import gerencia_quarto.Quarto;

public class QuartoPremium extends Quarto {
    public QuartoPremium(int numero, String status) {
        super(numero, "Premium", status);
    }

    public double getValorDiaria() {
        return 300.0; 
    }
    
    @Override
    public String exibirInfo() {
        return "Quarto Premium " + getNumero() + " - " + getStatus();
    }

    @Override
    public String toString() {  
        return getNumero() + ";Premium;" + getStatus();
    }
    
}
