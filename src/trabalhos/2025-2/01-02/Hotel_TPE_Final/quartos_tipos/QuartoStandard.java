package quartos_tipos;

import gerencia_quarto.Quarto;

public class QuartoStandard extends Quarto {
    public QuartoStandard(int numero, String status) {
        super(numero, "Standard", status);
    }

    public double getValorDiaria() {
        return 150.0; 
    }
    
    @Override
    public String exibirInfo() {
        return "Quarto Standard " + getNumero() + " - " + getStatus();
    }

    @Override
    public String toString() {  
        return getNumero() + ";Standard;" + getStatus();
    }
}
