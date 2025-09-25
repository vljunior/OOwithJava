package exemplos.designpatterns.comportamentais;

import java.util.ArrayList;
import java.util.List;

/*public*/ interface Observador {
    void atualizar(String noticia);
}

/*public*/ class Observantes { //Repositorio dos assinantes, programação pra implementação
    //Associação 0..N
    private List<Observador> assinantes = new ArrayList<>(); 

    public void adicionarAssinante(Observador o) {
        assinantes.add(o);
    }

    public void removerAssinante(Observador o) {
        assinantes.remove(o);
    }

    public void publicarNoticia(String noticia) {
        System.out.println("Canal publicou: " + noticia);
        notificarAssinantes(noticia);
    }

    private void notificarAssinantes(String noticia) { //varredura d comunicação
        for (Observador o : assinantes) {
            o.atualizar(noticia);
        }
    }
}

/*public*/ class AssinanteEmail implements Observador {
    private String email;

    public AssinanteEmail(String email) {
        this.email = email;
    }

    @Override
    public void atualizar(String noticia) {
        System.out.println("Notificação enviada para " + email + ": " + noticia);
    }
}

/*public*/ class AssinanteApp implements Observador {
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
        Observantes canal = new Observantes();

        Observador assinante1 = new AssinanteEmail("joao@email.com");
        Observador assinante2 = new AssinanteApp("Maria");
        Observador assinante3 = new AssinanteApp("Carlos");

        canal.adicionarAssinante(assinante1);
        canal.adicionarAssinante(assinante2);
        canal.adicionarAssinante(assinante3);

        canal.publicarNoticia("Nova edição do jornal disponível!");
        canal.publicarNoticia("Promoção especial para assinantes!");
    }
}