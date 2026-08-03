package jogodamemoria.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

    private BufferedImage sprite;

    public SpriteSheet() {

        try {

            sprite = ImageIO.read(
                getClass().getResource("/assets/cards_sheet.png")
            );

        }

        catch(IOException e){

            e.printStackTrace();

        }

    }

    public BufferedImage getSprite(int coluna,int linha,int largura,int altura){

        return sprite.getSubimage(
                coluna*largura,
                linha*altura,
                largura,
                altura
        );

    }

}