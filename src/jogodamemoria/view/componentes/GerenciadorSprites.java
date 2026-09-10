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
                    
                    System.out.println("Spritesheet carregado! Célula base: " + larguraCartaCalculada + "x" + alturaCartaCalculada);
                } else {
                    System.out.println("ERRO: InputStream do Spritesheet é nulo!");
                }
            } catch (Exception e) {
                System.out.println("ERRO CRÍTICO AO CARREGAR SPRITESHEET: " + e.getMessage());
            }
        }
    }

    public static ImageIcon obterIconePorValor(String valorCarta, int larguraDesejada, int alturaDesejada) {
        System.out.println(">>> CHAMOU obterIconePorValor PARA A CARTA: " + valorCarta);
        carregarSpritesheet();

        if (spritesheet == null || valorCarta == null) {
            return null; 
        }

        int coluna = 0;
        int linha = 0;

        //ajuste fino para todas as cartas
        int offsetX = 0; 
        int offsetY = 0; 
        int larguraCorte = larguraCartaCalculada;
        int alturaCorte = alturaCartaCalculada;

        // Mapeamento individual por nome da carta
        switch (valorCarta.toLowerCase()) {
            case "unesp":      
                coluna = 0; linha = 0; 
                offsetY= 100;
                alturaCorte = alturaCartaCalculada - 50;
                break;
            case "gato":        //sp
                coluna = 1; linha = 0; 
                offsetX=10;
                
                offsetY=150;
                alturaCorte = alturaCartaCalculada - 70;

                break;
            case "marmita":     //sp
                coluna = 2; linha = 0; 
                offsetY=150;
                alturaCorte = alturaCartaCalculada -70;
                
                break;
            case "chapeu":     //sp
                coluna = 3; linha = 0; 
                offsetY=150;
                alturaCorte = alturaCartaCalculada -70;
                break; 
            case "diploma":     //sp
                coluna = 4; linha = 0;
                offsetY=150;
                alturaCorte = alturaCartaCalculada -70;
                 

                break;

            case "computador":  
                coluna = 0; linha = 1; 
               
                offsetY=70;
                alturaCorte = alturaCartaCalculada - 30;
                
               break;
            case "notebook":    //sp
                coluna = 1; linha = 1; 
                offsetY=70;
                offsetX=10;
                alturaCorte = alturaCartaCalculada - 100;
                break;
            case "livro":       //pd
                coluna = 2; linha = 1; 
                offsetY=10;
                break; 
            case "mouse":       //pd
                coluna = 3; linha = 1; 
                offsetY=10;
                break;
            case "professor":   //pd
                coluna = 4; linha = 1; 
                offsetY=10;
                break;

            case "lampada":     
                coluna = 1; linha = 2; 
                offsetY=50;
                break; 
            case "pilhalivros": 
                coluna = 2; linha = 2;
                offsetY=-30; 
                alturaCorte = alturaCartaCalculada -80;
                break; 
            case "mochila":     
                coluna = 3; linha = 2; 
                offsetY=50;
                
                break;
            
            default:
                // Tratamento caso venha algum número em formato de string do multiplayer antigo
                try {
                    int num = Integer.parseInt(valorCarta);
                    coluna = (num - 1) % 5;
                    linha = (num - 1) / 5;
                } catch (NumberFormatException e) {
                    coluna = 0; linha = 0;
                }
                break;
        }

        try {
            int x = (coluna * larguraCartaCalculada) + offsetX;
            int y = (linha * alturaCartaCalculada) + offsetY;
            
            // Validações de segurança para limites da imagem
            if (x < 0) x = 0;
            if (y < 0) y = 0;
            if (x + larguraCorte > spritesheet.getWidth()) larguraCorte = spritesheet.getWidth() - x;
            if (y + alturaCorte > spritesheet.getHeight()) alturaCorte = spritesheet.getHeight() - y;

            BufferedImage pedaco = spritesheet.getSubimage(x, y, larguraCorte, alturaCorte);
            Image imagemRedimensionada = pedaco.getScaledInstance(larguraDesejada, alturaDesejada, Image.SCALE_SMOOTH);
            
            return new ImageIcon(imagemRedimensionada);
        } catch (Exception e) {
            System.out.println("Erro ao recortar a carta " + valorCarta + ": " + e.getMessage());
            return null;
        }
    }

    public static ImageIcon obterCarta(int coluna, int linha, int larguraDesejada, int alturaDesejada) {
        carregarSpritesheet();
        try {
            int x = coluna * larguraCartaCalculada;
            int y = linha * alturaCartaCalculada;
            
            BufferedImage pedaco = spritesheet.getSubimage(x, y, larguraCartaCalculada, alturaCartaCalculada);
            Image imagemRedimensionada = pedaco.getScaledInstance(larguraDesejada, alturaDesejada, Image.SCALE_SMOOTH);
            
            return new ImageIcon(imagemRedimensionada);
        } catch (Exception e) {
            System.out.println("Erro ao recortar coordenada exata: " + e.getMessage());
            return null;
        }
    }
}