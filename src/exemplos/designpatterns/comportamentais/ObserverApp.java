package exemplos.designpatterns.comportamentais;

import java.util.ArrayList;
import java.util.List;

/*public*/ interface Observer {
    void atualizar(String noticia);
}

/*public*/ class CanalNoticias {
    //Associação 0..N
    private List<Observer> assinantes = new ArrayList<>(); 

    public void adicionarAssinante(Observer o) {
        assinantes.add(o);
    }

    public void removerAssinante(Observer o) {
        assinantes.remove(o);
    }

    public void publicarNoticia(String noticia) {
        System.out.println("Canal publicou: " + noticia);
        notificarAssinantes(noticia);
    }

    private void notificarAssinantes(String noticia) {
        for (Observer o : assinantes) {
            o.atualizar(noticia);
        }
    }
}

/*public*/ class AssinanteEmail implements Observer {
    private String email;

    public AssinanteEmail(String email) {
        this.email = email;
    }

    @Override
    public void atualizar(String noticia) {
        System.out.println("Notificação enviada para " + email + ": " + noticia);
    }
}

/*public*/ class AssinanteApp implements Observer {
    private String nome;

    public AssinanteApp(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(String noticia) {
        System.out.println("Notificação push para " + nome + ": " + noticia);
    }
}

public class ObserverApp {
    public static void main(String[] args) {
        CanalNoticias canal = new CanalNoticias();

        Observer assinante1 = new AssinanteEmail("joao@email.com");
        Observer assinante2 = new AssinanteApp("Maria");
        Observer assinante3 = new AssinanteApp("Carlos");

        canal.adicionarAssinante(assinante1);
        canal.adicionarAssinante(assinante2);
        canal.adicionarAssinante(assinante3);

        canal.publicarNoticia("Nova edição do jornal disponível!");
        canal.publicarNoticia("Promoção especial para assinantes!");
    }
}