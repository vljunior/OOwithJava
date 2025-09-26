package exemplos.designpatterns.comportamentais.observer;

public class AssinanteApp implements Observador {
    private String nome;

    public AssinanteApp(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(String noticia) {
        System.out.println("Notificação push para " + nome + ": " + noticia);
    }
}