package exemplos.Swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

/*public*/ class AppMensagemJanela {
    public static void main(String[] args) {
        // Criar a janela (frame)
        JFrame frame = new JFrame("Minha Primeira Janela");

        // Criar um rótulo com a mensagem
        JLabel label = new JLabel("Olá, bem-vindo ao Java Swing!", SwingConstants.CENTER);

        // Configurar a janela
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(label);
        frame.setLocationRelativeTo(null); // Centraliza na tela

        // Exibir a janela
        frame.setVisible(true);
    }
}
