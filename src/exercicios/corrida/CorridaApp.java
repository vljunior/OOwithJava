package exercicios.corrida;

/*
    Abstração do sistema:
    O sistema é um simulador simples de corrida. 
    Ele abstrai três entidades principais:
    - Piloto: identificado apenas pelo nome.
    - Carro: possui escuderia, número, piloto, combustível e velocidade. 
      Permite acelerar, frear e reabastecer, respeitando limites definidos.
    - Pista: possui nome, distância de uma volta e número de voltas.

    Regras principais:
    - A cada aceleração, o carro ganha 25 km/h e perde 2 de combustível.
    - A cada frenagem, o carro perde 20 km/h.
    - O carro não pode ultrapassar sua velocidade máxima km/h nem ficar abaixo de 0 km/h.
    - O combustível varia entre 0 e 100.
    - Distância percorrida = velocidade (km/h) * 3.6 * tempo (s). 
      O tempo é fixo em 0,5 segundos por aceleração.
    - A corrida termina quando o carro completar todas as voltas ou ficar sem combustível.
*/

import utilitarios.*;

public class CorridaApp {
    public static void main(String[] args) {        

        // Definição da pista                
        String nomePista =  Teclado.readString("Digite o nome da pista: ");        
        int distanciaPorVolta = Teclado.readInt("Digite a distância por volta (em metros): ");        
        int voltas = Teclado.readInt("Digite o número de voltas: ");
        
        Pista pista = new Pista(nomePista, distanciaPorVolta, voltas);

        // Definição do piloto e carro        
        String nomePiloto = Teclado.readString("Digite o nome do piloto: ");
        Piloto piloto = new Piloto(nomePiloto);      
        String escuderia = Teclado.readString("Digite a escuderia do carro: ");        
        int numeroCarro = Teclado.readInt("Digite o número do carro: ");
        Carro carro = new Carro(escuderia, numeroCarro, piloto, 310); //fixado 310

        // Variáveis de corrida
        double distanciaPercorrida = 0;
        int voltaAtual = 1;

        Video.limparTela();
        Video.cabecalho("Corrida iniciada em " + pista.getNome());

        boolean corridaAtiva = true;

        while (corridaAtiva) {

            //Separar o menu em método static

            System.out.println("\nEscolha uma ação: ");
            System.out.println("1 - Acelerar");
            System.out.println("2 - Frear");
            System.out.println("3 - Reabastecer total");
            System.out.println("4 - Reabastecer parcial");
            System.out.println("5 - Status");
            System.out.println("6 - Sair");
            int opcao = Teclado.readInt();

            switch (opcao) {
                case 1:
                    carro.acelerar();
                    if (carro.temCombustivel()) {
                        double distancia = carro.getVelocidade() * 3.6 * 0.5;
                        distanciaPercorrida += distancia;

                        // verificar volta concluída
                        if (distanciaPercorrida >= pista.getDistanciaPorVolta() * voltaAtual) {
                            voltaAtual++;
                        }
                    }
                    break;
                case 2:
                    carro.frear();
                    break;
                case 3:
                    carro.reabastecerTotal();
                    Video.barraProgresso(20, 20);
                    break;
                case 4:                    
                    int qtd = Teclado.readInt("Digite a quantidade de combustível a adicionar: ");
                    carro.reabastecer(qtd);
                    Video.barraProgresso(20, 50);
                    break;
                case 5:
                    mostrarStatus(carro, pista, distanciaPercorrida, voltaAtual, piloto);
                    break;
                case 6:
                    corridaAtiva = false;
                    break;
            }

            // Fim da corrida
            if (!carro.temCombustivel()) {
                Video.mensagemErro("\nO carro ficou sem combustível! Corrida encerrada.");
                corridaAtiva = false;
            } else if (voltaAtual > pista.getVoltasTotais()) {
                Video.mensagemOk("\nO carro completou todas as voltas! Corrida encerrada.");
                corridaAtiva = false;
            }
        }        
    }

    //Fazer em toString de cada entidade! Separar reponsabilidades!

    private static void mostrarStatus(Carro carro, Pista pista, double distanciaPercorrida, int voltaAtual, Piloto piloto) {
        double distanciaVoltaAtual = distanciaPercorrida % pista.getDistanciaPorVolta();
        double porcentagemVolta = (distanciaVoltaAtual / pista.getDistanciaPorVolta()) * 100;

        Video.rodape("Status do carro!");

        System.out.println(carro.toString());
        System.out.println("Conduzido por: " + piloto.toString());                                          
        System.out.println("Volta " + voltaAtual + " de " + pista.getVoltasTotais());
        System.out.printf("Percorreu %.0f/ %d metros - %.2f%% da volta\n", 
                          distanciaVoltaAtual, pista.getDistanciaPorVolta(), porcentagemVolta);
        Video.pausa();             
        
    }
}
