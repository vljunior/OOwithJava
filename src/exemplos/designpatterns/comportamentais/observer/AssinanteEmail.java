package exemplos.designpatterns.comportamentais.observer;

public class AssinanteEmail implements Observador {
    private String email;

    public AssinanteEmail(String email) {
        this.email = email;
    }

    @Override
    public void atualizar(String noticia) {
        System.out.println("Notificação enviada para " + email + ": " + noticia);
    }
}