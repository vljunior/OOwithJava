package exemplos.designpatterns.comportamentais.observer;

public class ObserverTesterApp {
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