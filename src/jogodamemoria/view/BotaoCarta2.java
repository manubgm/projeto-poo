package view;

import model.Carta2;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

public class BotaoCarta2 extends JButton {
    private Carta2 cartaModelo;

    public BotaoCarta2(Carta2 cartaModelo) {
        this.cartaModelo = cartaModelo;
        setPreferredSize(new Dimension(96, 144));
        setBackground(new Color(40, 40, 40));
        setFocusPainted(false);
        setBorderPainted(false);
        atualizarGrafico();
    }

    public Carta2 getCartaModelo() {
        return cartaModelo;
    }

    public void atualizarGrafico() {
        setIcon(cartaModelo.getImagemAtual());
    }
}