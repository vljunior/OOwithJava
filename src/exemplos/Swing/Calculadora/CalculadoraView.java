package exemplos.Swing.Calculadora;

import javax.swing.*;
import java.awt.*;

public class CalculadoraView extends JFrame {

    private final JTextField campoNumero1;
    private final JTextField campoNumero2;
    private final JLabel labelResultado;

    private final Calculadora calculadora;

    public CalculadoraView() {
        super("Calculadora Simples");
        this.calculadora = new Calculadora();

        // Configuração da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        
        // Usando BorderLayout para a janela principal
        setLayout(new BorderLayout(10, 10)); // Espaçamento entre componentes

        // --- Painel de Exibição (Números e Resultado) ---
        JPanel displayPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margens internas

        campoNumero1 = new JTextField();
        campoNumero2 = new JTextField();
        labelResultado = new JLabel("Resultado: ", SwingConstants.RIGHT); // Alinha o texto à direita

        // Configura a fonte para os números e resultado
        Font displayFont = new Font("Arial", Font.PLAIN, 18);
        campoNumero1.setFont(displayFont);
        campoNumero2.setFont(displayFont);
        labelResultado.setFont(new Font("Arial", Font.BOLD, 22));

        displayPanel.add(new JLabel("Número 1:"));
        displayPanel.add(campoNumero1);
        displayPanel.add(new JLabel("Número 2:"));
        displayPanel.add(campoNumero2);
        displayPanel.add(new JLabel(" ")); // Espaço em branco
        displayPanel.add(labelResultado);

        add(displayPanel, BorderLayout.CENTER);

        // --- Painel de Botões ---
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton btnAdicao = new JButton("+");
        JButton btnSubtracao = new JButton("-");
        JButton btnMultiplicacao = new JButton("*");
        JButton btnDivisao = new JButton("/");

        // Configura a fonte para os botões
        Font buttonFont = new Font("Arial", Font.BOLD, 24);
        btnAdicao.setFont(buttonFont);
        btnSubtracao.setFont(buttonFont);
        btnMultiplicacao.setFont(buttonFont);
        btnDivisao.setFont(buttonFont);

        // Adicionando os botões ao painel de botões
        buttonPanel.add(btnAdicao);
        buttonPanel.add(btnSubtracao);
        buttonPanel.add(btnMultiplicacao);
        buttonPanel.add(btnDivisao);

        add(buttonPanel, BorderLayout.SOUTH);

        // Adicionando ações aos botões
        btnAdicao.addActionListener(e -> realizarCalculo(new Adicao()));
        btnSubtracao.addActionListener(e -> realizarCalculo(new Subtracao()));
        btnMultiplicacao.addActionListener(e -> realizarCalculo(new Multiplicacao()));
        btnDivisao.addActionListener(e -> realizarCalculo(new Divisao()));
    }

    private void realizarCalculo(OperacaoMatematica operacao) {
        try {
            double numero1 = Double.parseDouble(campoNumero1.getText());
            double numero2 = Double.parseDouble(campoNumero2.getText());

            double resultado = calculadora.executarOperacao(operacao, numero1, numero2);
            labelResultado.setText("Resultado: " + resultado);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, insira números válidos.",
                    "Erro de Entrada",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Erro de Operação",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraView().setVisible(true);
        });
    }
}