package exemplos.designpatterns.comportamentais.observer;

import java.util.ArrayList;
import java.util.List;



public class Observantes { //Repositorio dos assinantes, programação pra implementação
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