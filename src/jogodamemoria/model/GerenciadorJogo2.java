package model;

import util.SpriteLoader;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GerenciadorJogo2 {
    private List<Carta2> cartas;
    private Carta2 primeiraCartaSelecionada;
    private Carta2 segundaCartaSelecionada;

    public GerenciadorJogo2(int pares) {
        cartas = new ArrayList<>();
        SpriteLoader loader = new SpriteLoader();
        
        ImageIcon verso = loader.getVerso();
        List<ImageIcon> frentes = loader.getFrentes(pares);

        // Cria os pares
        for (int i = 0; i < pares; i++) {
            cartas.add(new Carta2(i, frentes.get(i), verso));
            cartas.add(new Carta2(i, frentes.get(i), verso)); // O Par
        }
        
        // Embaralha as cartas
        Collections.shuffle(cartas);
    }

    public List<Carta2> getCartas() {
        return cartas;
    }

    public boolean selecionarCarta(Carta2 carta) {
        if (carta.isVirada() || carta.isEncontrada() || segundaCartaSelecionada != null) {
            return false; // Não faz nada se já estiver virada ou se 2 já foram clicadas
        }

        carta.virar();

        if (primeiraCartaSelecionada == null) {
            primeiraCartaSelecionada = carta;
            return false; // Esperando a segunda carta
        } else {
            segundaCartaSelecionada = carta;
            return true; // Duas cartas viradas, precisa checar
        }
    }

    public boolean checarPar() {
        if (primeiraCartaSelecionada.getId() == segundaCartaSelecionada.getId()) {
            primeiraCartaSelecionada.setEncontrada(true);
            segundaCartaSelecionada.setEncontrada(true);
            resetarSelecao();
            return true; // Acertou
        } else {
            primeiraCartaSelecionada.virar();
            segundaCartaSelecionada.virar();
            resetarSelecao();
            return false; // Errou
        }
    }

    private void resetarSelecao() {
        primeiraCartaSelecionada = null;
        segundaCartaSelecionada = null;
    }
}