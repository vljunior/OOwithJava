package exemplos.designpatterns.arquitetural.MVC;

import exemplos.designpatterns.arquitetural.MVC.model.*;
import exemplos.designpatterns.arquitetural.MVC.controller.*;
import exemplos.designpatterns.arquitetural.MVC.view.*;


public class App {
    public static void main(String[] args) {
        EstudanteModel model = new Aluno("Maria", 20);
        EstudanteView view = new AlunoConsoleView();
        EstudanteControllerInterface controller = new
   							EstudanteController(model, view);

        view.exibirDetalhesAluno (model);   // Maria, 20
        controller.atualizarNome("João");
        view.exibirDetalhesAluno(model);   // João, 20
    }
}

