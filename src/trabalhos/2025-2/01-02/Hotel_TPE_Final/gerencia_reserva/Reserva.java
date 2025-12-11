package gerencia_reserva;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Reserva {
    private String id;
    private String cpfHospede;
    private int numeroQuarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String status;
    private double valorTotal;

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Reserva(String cpfHospede, int numeroQuarto) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.cpfHospede = cpfHospede;
        this.numeroQuarto = numeroQuarto;
        this.dataEntrada = LocalDate.now();
        this.status = "Ativa";
        this.valorTotal = 0.0;
    }

    public Reserva(String id, String cpf, int quarto, LocalDate entrada, LocalDate saida, String status, double valor) {
        this.id = id;
        this.cpfHospede = cpf;
        this.numeroQuarto = quarto;
        this.dataEntrada = entrada;
        this.dataSaida = saida;
        this.status = status;
        this.valorTotal = valor;
    }

    public void finalizarReserva(int dias, double valorDiaria) {
        this.dataSaida = LocalDate.now();
        this.status = "Finalizada";
        this.valorTotal = dias * valorDiaria;
    }

    public String getId() { return id; }
    public int getNumeroQuarto() { return numeroQuarto; }
    public String getStatus() { return status; }
    public String getCpfHospede() { return cpfHospede; }
    public LocalDate getDataEntrada() { return dataEntrada; }
    public double getValorTotal() { return valorTotal; }

    @Override
    public String toString() {
        String saidaStr = (dataSaida != null) ? dataSaida.format(FORMATO) : "null";
        return id + ";" + cpfHospede + ";" + numeroQuarto + ";" + 
               dataEntrada.format(FORMATO) + ";" + saidaStr + ";" + 
               status + ";" + valorTotal;
    }

    public static Reserva fromString(String linha) {
        String[] p = linha.split(";", -1);
        String id = p[0];
        String cpf = p[1];
        int quarto = Integer.parseInt(p[2]);
        LocalDate entrada = LocalDate.parse(p[3], FORMATO);
        
        LocalDate saida = null;
        if (!p[4].equals("null")) {
            saida = LocalDate.parse(p[4], FORMATO);
        }
        
        String status = p[5];
        double valor = Double.parseDouble(p[6]);

        return new Reserva(id, cpf, quarto, entrada, saida, status, valor);
    }
}