package quartos_tipos;

import gerencia_quarto.Quarto;

public class QuartoPlus extends Quarto {
    public QuartoPlus(int numero, String status) {
        super(numero, "Plus", status);
    }

    public double getValorDiaria() {
        return 225.0; 
    }
    
    @Override
    public String exibirInfo() {
        return "Quarto Plus " + getNumero() + " - " + getStatus();
    }

    @Override
    public String toString() {  
        return getNumero() + ";Plus;" + getStatus();
    }
    
}
