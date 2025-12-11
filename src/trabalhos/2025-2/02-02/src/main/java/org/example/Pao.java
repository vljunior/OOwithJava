package org.example;

import jakarta.persistence.Entity;

@Entity
public class Pao extends Produto {

    public Pao() {
    }

    @Override
    public String getDescricao() {
        return "";
    }
}