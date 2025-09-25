package exemplos.Swing;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

/*public*/ class InputBox extends JDialog {

    private JTextField inputField;
    private String inputValue;
    private boolean confirmed = false;

    public InputBox(JFrame parent, String title, String message) {
        // 'true' no construtor torna o JDialog modal
        super(parent, title, true); 
        
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setResizable(false);
        
        // Inicializa a variável de retorno para null
        this.inputValue = null;

        // --- Painel principal com BorderLayout ---
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Rótulo com a mensagem
        JLabel messageLabel = new JLabel(message);
        mainPanel.add(messageLabel, BorderLayout.NORTH);

        // Campo de texto para a entrada do usuário
        inputField = new JTextField(20);
        mainPanel.add(inputField, BorderLayout.CENTER);

        // --- Painel de botões no sul ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("OK");
        
        okButton.addActionListener(e -> {
            this.inputValue = inputField.getText();
            this.confirmed = true;
            dispose(); // Fecha a janela
        });
        
        buttonPanel.add(okButton);
        
        // Adiciona o painel de botões ao painel principal
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona o painel principal à janela de diálogo
        setContentPane(mainPanel);
    }
    
    // Método público para obter o valor inserido
    public String getInputValue() {
        return inputValue;
    }

    // Método público para verificar se o usuário confirmou
    public boolean isConfirmed() {
        return confirmed;
    }
}

public class InputBoxApp extends JFrame {

    public InputBoxApp() {
        super("Aplicação Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton showInputButton = new JButton("Pedir Nome");
        showInputButton.addActionListener(e -> {
            // Cria uma nova instância de InputBox
            InputBox inputBox = new InputBox(this, "Nome do Usuário", "Por favor, digite seu nome:");
            
            // Exibe a janela. A execução para aqui até que a janela seja fechada.
            inputBox.setVisible(true);

            // A execução continua aqui quando a janela é fechada
            if (inputBox.isConfirmed()) {
                String nome = inputBox.getInputValue();
                if (nome != null && !nome.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Olá, " + nome + "!");
                } else {
                    JOptionPane.showMessageDialog(this, "Nenhum nome foi digitado.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Operação cancelada.");
            }
        });

        add(showInputButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InputBoxApp().setVisible(true);
        });
    }
}
