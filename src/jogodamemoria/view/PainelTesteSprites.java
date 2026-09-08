package jogodamemoria.view;

public class PainelTestesSprites {
    
}
package jogodamemoria.view;

import jogodamemoria.util.SpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PainelTesteSprites extends JPanel {

    private final SpriteSheet spriteSheet;

    // Tamanho de cada carta na sprite sheet
    private static final int LARGURA_CARTA = 96;
    private static final int ALTURA_CARTA = 144;

    public PainelTesteSprites() {

        spriteSheet = new SpriteSheet();

        setBackground(new Color(30, 120, 30)); // verde mesa de cartas

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int x = 20;
        int y = 20;

        for (int i = 0; i < 25; i++) {

            int colunaSprite = i % 5;
            int linhaSprite = i / 5;

            BufferedImage carta =
                    spriteSheet.getSprite(
                            colunaSprite,
                            linhaSprite,
                            LARGURA_CARTA,
                            ALTURA_CARTA
                    );

            g.drawImage(
                    carta,
                    x,
                    y,
                    LARGURA_CARTA,
                    ALTURA_CARTA,
                    null
            );

            x += 110;

            if ((i + 1) % 5 == 0) {

                x = 20;
                y += 160;

            }

        }

    }

}