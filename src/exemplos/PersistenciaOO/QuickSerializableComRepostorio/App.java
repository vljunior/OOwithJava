public class App {
    public static void main(String[] args) throws Exception {
        
        Repositorio<Pessoa> repo = new RepositorioEntidade<>(Pessoa.class);
        ServicoEntidade<Pessoa> servico = new ServicoEntidade<>(repo); // injeção via construtor

        servico.adicionar(new Pessoa("Lorenzon"));       

        System.out.println("Entidades salvas:");

        servico.listar().forEach(System.out::println);
    }
}