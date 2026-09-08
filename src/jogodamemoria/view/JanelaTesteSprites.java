package jogodamemoria.view;

import javax.swing.JFrame;

public class JanelaTesteSprites extends JFrame {

    public JanelaTesteSprites() {

        setTitle("Teste Sprite Sheet");

        setSize(650, 900);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new PainelTesteSprites());

        setVisible(true);

    }

}