package jogodamemoria.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpriteSheet {

    private BufferedImage spriteSheet;

    private final ArrayList<BufferedImage> cartas = new ArrayList<>();

    private BufferedImage verso;

    private BufferedImage cartaEspecial;

    /*
     * Essas medidas precisam ser ajustadas UMA ÚNICA VEZ
     * para coincidir exatamente com a imagem.
     */

    private static final int LARGURA = 105;
    private static final int ALTURA = 182;

    private static final int X_INICIAL = 18;
    private static final int Y_INICIAL = 58;

    private static final int ESPACO_X = 8;
    private static final int ESPACO_Y = 10;

    public SpriteSheet() {

        try {

            spriteSheet = ImageIO.read(
                    new File("resources/assets/cartas.png"));

            carregarCartas();

            carregarVerso();

            carregarCartaEspecial();

            System.out.println("Sprite Sheet carregada!");

        }

        catch (IOException e) {

            e.printStackTrace();

        }

    }

    private void carregarCartas() {

        cartas.clear();

        for (int linha = 0; linha < 4; linha++) {

            for (int coluna = 0; coluna < 12; coluna++) {

                int x = X_INICIAL + coluna * (LARGURA + ESPACO_X);

                int y = Y_INICIAL + linha * (ALTURA + ESPACO_Y);

                BufferedImage carta =
                        spriteSheet.getSubimage(
                                x,
                                y,
                                LARGURA,
                                ALTURA);

                cartas.add(carta);

            }

        }

    }

    private void carregarVerso() {

        /*
         * Ajustar depois.
         */

        verso = spriteSheet.getSubimage(
                70,
                845,
                LARGURA,
                ALTURA);

    }

    private void carregarCartaEspecial() {

        /*
         * Ajustar depois.
         */

        cartaEspecial = spriteSheet.getSubimage(
                430,
                845,
                LARGURA,
                ALTURA);

    }

    public BufferedImage getCarta(int indice) {

        return cartas.get(indice);

    }

    public BufferedImage getVerso() {

        return verso;

    }

    public BufferedImage getCartaEspecial() {

        return cartaEspecial;

    }

    public int quantidadeCartas() {

        return cartas.size();

    }

}