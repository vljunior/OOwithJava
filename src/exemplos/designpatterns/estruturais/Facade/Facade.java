package exemplos.designpatterns.estruturais.Facade;

/*public*/ class SistemaAudio {
    public void ligarAudio() {
        System.out.println("Áudio ligado.");
    }
}

/*public*/ class SistemaVideo {
    public void ligarVideo() {
        System.out.println("Vídeo ligado.");
    }
}

/*public*/ class SistemaRede {
    public void conectar() {
        System.out.println("Rede conectada.");
    }
}

/*O Facade não adiciona novas funcionalidades; ele organiza 
  e simplifica o acesso às já existentes.
  Não elimina a possibilidade de acessar diretamente as classes internas, 
  mas orienta o uso pelo ponto de entrada único.
  É bastante usado em APIs, frameworks e integrações para 
  esconder detalhes complexos do cliente.
*/

/*public*/ class HomeTheaterFacade {
    private SistemaAudio audio;
    private SistemaVideo video;
    private SistemaRede rede;

    public HomeTheaterFacade() {
        this.audio = new SistemaAudio();
        this.video = new SistemaVideo();
        this.rede = new SistemaRede();
    }

    public void assistirFilme() {
        System.out.println("Preparando para assistir ao filme...");
        audio.ligarAudio();
        video.ligarVideo();
        rede.conectar();
        System.out.println("Filme pronto para iniciar!");
    }
}

public class Facade {
    public static void main(String[] args) {
        // Cliente acessa apenas a fachada
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();
        homeTheater.assistirFilme();
    }
}