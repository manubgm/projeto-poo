package jogodamemoria.main; // Mantive a estrutura exata que apareceu no seu terminal

import view.JanelaPrincipal2;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal2 janela = new JanelaPrincipal2();
            janela.setVisible(true);
        });
    }
}