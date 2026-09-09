package jogodamemoria.view.componentes;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GerenciadorSprites {

    private static BufferedImage spritesheet;
    private static int larguraCartaCalculada = 0;
    private static int alturaCartaCalculada = 0;

    private static void carregarSpritesheet() {
        if (spritesheet == null) {
            try {
                java.io.InputStream is = GerenciadorSprites.class.getResourceAsStream("/jogodamemoria/recursos/imagens/cartas_sprites.png");
                
                if (is != null) {
                    spritesheet = ImageIO.read(is);
                    larguraCartaCalculada = spritesheet.getWidth() / 5;
                    alturaCartaCalculada = spritesheet.getHeight() / 3;
                    
                    System.out.println("Spritesheet carregado! Cada carta tem tamanho: " + larguraCartaCalculada + "x" + alturaCartaCalculada);
                } else {
                    System.out.println("ERRO: InputStream do Spritesheet é nulo!");
                }
            } catch (Exception e) {
                System.out.println("ERRO CRÍTICO AO CARREGAR SPRITESHEET: " + e.getMessage());
            }
        }
    }

    public static ImageIcon obterIconePorValor(String valorCarta, int larguraDesejada, int alturaDesejada) {
        carregarSpritesheet();

        if (spritesheet == null) {
            return null; 
        }

        int coluna = 0;
        int linha = 0;

        //mapa
        switch (valorCarta.toLowerCase()) {
            case "unesp":      coluna = 0; linha = 0; break;
            case "gato":        coluna = 1; linha = 0; break;
            case "marmita":     coluna = 2; linha = 0; break;
            case "chapeu":     coluna = 3; linha = 0; break; 
            case "diploma":     coluna = 4; linha = 0; break;

            case "computador":  coluna = 0; linha = 1; break;
            case "notebook":    coluna = 1; linha = 1; break;
            case "livro":       coluna = 2; linha = 1; break; 
            case "mouse":       coluna = 3; linha = 1; break;
            case "professor":   coluna = 4; linha = 1; break;

            case "lampada":     coluna = 1; linha = 2; break; 
            case "pilhalivros": coluna = 2; linha = 2; break; 
            case "mochila":     coluna = 3; linha = 2; break;
            
            default:
                coluna = 0; linha = 0; 
                break;
        }

        try {
            int margem = 2; //teste
            int x = (coluna * larguraCartaCalculada) + margem;
            int y = (linha * alturaCartaCalculada) + margem;
            int larguraRealCorte = larguraCartaCalculada - (margem * 2);
            int alturaRealCorte = alturaCartaCalculada - (margem * 2);
            
            BufferedImage pedaco = spritesheet.getSubimage(x, y, larguraRealCorte, alturaRealCorte);
            Image imagemRedimensionada = pedaco.getScaledInstance(larguraDesejada, alturaDesejada, Image.SCALE_SMOOTH);
            
            return new ImageIcon(imagemRedimensionada);
        } catch (Exception e) {
            System.out.println("Erro ao recortar a carta: " + valorCarta + " -> " + e.getMessage());
            return null;
        }
    }

    public static ImageIcon obterCarta(int coluna, int linha, int larguraDesejada, int alturaDesejada) {
        carregarSpritesheet();
        try {
            int margem = 2; 
            int x = (coluna * larguraCartaCalculada) + margem;
            int y = (linha * alturaCartaCalculada) + margem;
            int larguraRealCorte = larguraCartaCalculada - (margem * 2);
            int alturaRealCorte = alturaCartaCalculada - (margem * 2);
            
            BufferedImage pedaco = spritesheet.getSubimage(x, y, larguraRealCorte, alturaRealCorte);
            Image imagemRedimensionada = pedaco.getScaledInstance(larguraDesejada, alturaDesejada, Image.SCALE_SMOOTH);
            
            return new ImageIcon(imagemRedimensionada);
        } catch (Exception e) {
            System.out.println("Erro ao recortar coordenada exata: " + e.getMessage());
            return null;
        }
    }
}