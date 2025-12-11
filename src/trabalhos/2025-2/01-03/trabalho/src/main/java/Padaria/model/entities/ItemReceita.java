package Padaria.model.entities;

import java.io.Serializable;

public class ItemReceita implements Serializable {

    private static final long serialVersionUID = 1L;

    private Insumo insumo;
    private float quantidadeNecessaria; 

    public ItemReceita(Insumo insumo, float quantidadeNecessaria) {
        this.insumo = insumo;
        this.quantidadeNecessaria = quantidadeNecessaria;
    }

    public Insumo getInsumo() { return insumo; }
    public float getQuantidadeNecessaria() { return quantidadeNecessaria; } 

    @Override
    public String toString() {
        return String.format("%s - %.2f %s", 
            insumo.getNome(), quantidadeNecessaria, insumo.getUnidadeMedida());
    }
}