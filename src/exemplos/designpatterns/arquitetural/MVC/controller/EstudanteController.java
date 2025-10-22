package exemplos.designpatterns.arquitetural.MVC.controller;

import exemplos.designpatterns.arquitetural.MVC.model.*;
import exemplos.designpatterns.arquitetural.MVC.view.*;

public class EstudanteController implements EstudanteControllerInterface {
    private EstudanteModel model;
    private EstudanteView view;

    public EstudanteController(EstudanteModel model, EstudanteView view) {
        this.model = model;
        this.view = view;
    }

    public void atualizarNome(String nome) {
        model.setNome (nome);
    }
    
}
