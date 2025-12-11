package org.example;

import java.util.List;

public class FuncionarioController {

    private FuncionarioService funcionarioService;

    public FuncionarioController() {
        this.funcionarioService = new FuncionarioService();
    }


    public void criarFuncionario(String nome, String cargo) {
        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome(nome);
        novoFuncionario.setCargo(cargo);

        funcionarioService.salvar(novoFuncionario);
    }


    public List<Funcionario> listarFuncionarios() {
        return funcionarioService.listarTodos();
    }


    public Funcionario buscarFuncionario(Long id) {
        return funcionarioService.buscarPorId(id);
    }


    public void atualizarFuncionario(Long id, String novoNome, String novoCargo) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);

        if (funcionario != null) {
            funcionario.setNome(novoNome);
            funcionario.setCargo(novoCargo);

            funcionarioService.atualizar(funcionario);
        } else {
            System.out.println("CONTROLLER: Funcionário com ID " + id + " não encontrado.");
        }
    }

    public void removerFuncionario(Long id) {
        funcionarioService.remover(id);
    }
}