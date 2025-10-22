package exemplos.Swing;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        setTitle("Main Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create the main content panel
        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Add the menu bar
        setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Create the "File" menu
        JMenu fileMenu = new JMenu("Arquivo");
        
        // Create a menu item to open the modal
        JMenuItem openModalItem = new JMenuItem("Abrir...");
        openModalItem.addActionListener(e -> {
            // Logic to open the modal window
            showModalDialog();
        });
        fileMenu.add(openModalItem);
        
        JMenuItem exitItem = new JMenuItem("Sair");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        return menuBar;
    }

    private void showModalDialog() {
        // Implementation for the modal dialog will go here
        // We'll create a JDialog instance in the next step
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainMenuFrame frame = new MainMenuFrame();
            frame.setVisible(true);
        });
    }
}
