package org.example;

import jakarta.persistence.Entity;

@Entity
public class Bolo extends Produto {

    public Bolo() {
    }

    @Override
    public String getDescricao() {
        return "";
    }
}