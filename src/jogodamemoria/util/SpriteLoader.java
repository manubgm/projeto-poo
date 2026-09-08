package util;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpriteLoader {
    private BufferedImage folha;
    
    // Dimensões aproximadas da carta baseadas na imagem (você pode ajustar)
    private final int LARGURA_CARTA = 96;
    private final int ALTURA_CARTA = 144;

    public SpriteLoader() {
        try {
            // Carrega a imagem principal (ajuste o caminho se necessário)
            folha = ImageIO.read(new File("src/assets/cartas.png"));
        } catch (IOException e) {
            System.out.println("Erro ao carregar cartas.png: " + e.getMessage());
        }
    }

    public ImageIcon getVerso() {
        // Recorta a carta de verso (coordenadas aproximadas do canto inferior esquerdo)
        // Você precisará ajustar os valores de X e Y para bater exatamente no recorte da sua imagem
        BufferedImage verso = folha.getSubimage(20, folha.getHeight() - 160, LARGURA_CARTA, ALTURA_CARTA);
        return new ImageIcon(verso);
    }

    public List<ImageIcon> getFrentes(int quantidadePares) {
        List<ImageIcon> frentes = new ArrayList<>();
        // Pega as primeiras cartas da primeira linha como exemplo
        int xInicial = 10;
        int yInicial = 10;
        int espacamento = 100; // Largura + espaço entre as cartas

        for (int i = 0; i < quantidadePares; i++) {
            BufferedImage frente = folha.getSubimage(xInicial + (i * espacamento), yInicial, LARGURA_CARTA, ALTURA_CARTA);
            frentes.add(new ImageIcon(frente));
        }
        return frentes;
    }
}