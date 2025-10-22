package exemplos.designpatterns.arquitetural.MVC.view;

import exemplos.designpatterns.arquitetural.MVC.model.EstudanteModel;

public class AlunoConsoleView implements EstudanteView {
    
    @Override
    public void exibirDetalhesAluno(EstudanteModel estudante) {
        System.out.println("Estudante: " + estudante.getNome() + ", Idade: " +
 							estudante.getIdade());
    }
}

