package exemplos.SOLID;

/* S – Single Responsibility Principle (Princípio da Responsabilidade Única)
 * Uma classe deve ter apenas uma razão para mudar, ou seja, ser responsável 
 * por apenas uma parte do sistema.
 */

//Problema:

/*public*/ class Relatorio {
    public void gerarRelatorio() {
        // lógica para gerar os dados do relatório
        System.out.println("Relatório gerado!");
    }
    public void salvarEmArquivo(String conteudo) {
        // lógica para salvar o relatório em arquivo
        System.out.println("Relatório salvo em arquivo!");
    }
    public void enviarPorEmail(String conteudo) {
        // lógica para enviar o relatório por email
        System.out.println("Relatório enviado por e-mail!");
    }
}

//Solução: Separação em classes distintas cada qual com sua responsabilidade:


// Classe responsável apenas por gerar relatórios
/*public*/  class Relatorio2 {
    public String gerarRelatorio() {
        return "Relatório gerado!";
    } 
}

// Classe responsável por salvar relatórios
/*public*/  class RelatorioArquivo {
    public void salvar(String conteudo) {
        System.out.println("Relatório salvo em arquivo: " + conteudo);
    }
}

// Classe responsável por enviar relatórios
/*public*/  class RelatorioEmail {
    public void enviar(String conteudo) {
        System.out.println("Relatório enviado por e-mail: " + conteudo);
    }
}
