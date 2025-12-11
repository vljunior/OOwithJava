package org.example;

import org.example.menus.MenuPrincipal;
import org.example.view.PessoaView;
import org.example.view.VendaView;
import org.example.utilitarios.Video;

public class Main {

    public static void main(String[] args) {
        loopMenuPrincipal();
        System.out.println("Obrigado por usar o sistema da padaria!");
    }

    private static void loopMenuPrincipal() {
        boolean executando = true;

        while (executando) {
            try {
                int opcao = MenuPrincipal.exibir();

                switch (opcao) {
                    case 1:
                        new PessoaView().executar();
                        break;
                    case 2:
                        new VendaView().executar();
                        break;
                    case 3:
                        executando = false;
                        break;
                }

            } catch (Exception e) {
                Throwable causa = e.getCause();
                if (causa != null && causa.getMessage().contains("ConstraintViolationException")) {
                    Video.mensagemErro("Este registro não pode ser removido pois está sendo usado em Vendas/Pedidos.");
                }
                else if (e.getMessage().contains("ConstraintViolationException")) {
                    Video.mensagemErro("Não é possível excluir: Existem dados dependentes.");
                }
                else {
                    Video.mensagemErro("Ocorreu um erro inesperado: " + e.getMessage());
                }

                Video.pausa();
            }
        }
    }
}