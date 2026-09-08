package jogodamemoria.view.componentes;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GerenciadorSprites {

    private static BufferedImage spritesheet;

    private static void carregarSpritesheet() {
        if (spritesheet == null) {
            try {
                
                spritesheet = ImageIO.read(GerenciadorSprites.class.getResourceAsStream("/jogodamemoria/recursos/imagens/cartas_sprites.png"));
            } catch (Exception e) {
                System.out.println("Erro ao carregar o spritesheet de cartas: " + e.getMessage());
            }
        }
    }

    
    public static ImageIcon obterIconePorValor(String valorCarta, int larguraCarta, int alturaCarta) {
        carregarSpritesheet();
        
        if (spritesheet == null) {
            return null; 
        }

        int coluna = 0;
        int linha = 0;

        // Mapeia o valor da carta para a posição exata (coluna e linha) na sua grade de imagens
        switch (valorCarta.toLowerCase()) {
            // Linha 0 
            case "unesp":       coluna = 0; linha = 0; break;
            case "gato":        coluna = 1; linha = 0; break;
            case "marmita":     coluna = 2; linha = 0; break;
            case "chapeu":      coluna = 3; linha = 0; break; 
            case "diploma":     coluna = 4; linha = 0; break;

            // Linha 1 
            case "computador":  coluna = 0; linha = 1; break;
            case "notebook":    coluna = 1; linha = 1; break;
            case "livro":       coluna = 2; linha = 1; break; 
            case "mouse":       coluna = 3; linha = 1; break;
            case "professor":   coluna = 4; linha = 1; break;

            // Linha 2 
            case "lampada":     coluna = 1; linha = 2; break; 
            case "pilhalivros": coluna = 2; linha = 2; break; 
            case "mochila":     coluna = 3; linha = 2; break;
            
            default:
                coluna = 0; linha = 0; // Padrão de segurança se não achar o nome
                break;
        }

        try {
            BufferedImage pedaco = spritesheet.getSubimage(coluna * larguraCarta, linha * alturaCarta, larguraCarta, alturaCarta);
            return new ImageIcon(pedaco);
        } catch (Exception e) {
            System.out.println("Erro ao recortar a carta: " + valorCarta);
            return null;
        }
    }

    public static ImageIcon obterCarta(int coluna, int linha, int larguraCarta, int alturaCarta) {
        carregarSpritesheet();
        try {
            BufferedImage pedaco = spritesheet.getSubimage(coluna * larguraCarta, linha * alturaCarta, larguraCarta, alturaCarta);
            return new ImageIcon(pedaco);
        } catch (Exception e) {
            System.out.println("Erro ao recortar coordenada exata.");
            return null;
        }
    }
}