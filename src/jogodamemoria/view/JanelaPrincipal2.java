package view;

import model.Carta2;
import model.GerenciadorJogo2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JanelaPrincipal2 extends JFrame {
    private GerenciadorJogo2 jogo;
    private List<BotaoCarta2> botoes;
    private JPanel painelTabuleiro;
    private Timer timer;

    public JanelaPrincipal2() {
        setTitle("Jogo da Memória Unespiano");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Inicia o jogo com 8 pares (16 cartas no total)
        jogo = new GerenciadorJogo2(8);
        botoes = new ArrayList<>();
        
        painelTabuleiro = new JPanel();
        painelTabuleiro.setLayout(new GridLayout(4, 4, 10, 10)); // Grid 4x4
        painelTabuleiro.setBackground(new Color(20, 20, 20));
        painelTabuleiro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Cria os botões baseados nas cartas do modelo
        for (Carta2 carta : jogo.getCartas()) {
            BotaoCarta2 botao = new BotaoCarta2(carta);
            botao.addActionListener(new CliqueCartaListener());
            botoes.add(botao);
            painelTabuleiro.add(botao);
        }

        add(painelTabuleiro);
    }

    private void atualizarTela() {
        for (BotaoCarta2 botao : botoes) {
            botao.atualizarGrafico();
        }
    }

    // Listener para o clique do mouse
    private class CliqueCartaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BotaoCarta2 botaoClicado = (BotaoCarta2) e.getSource();
            Carta2 carta = botaoClicado.getCartaModelo();

            boolean duasCartasViradas = jogo.selecionarCarta(carta);
            atualizarTela();

            if (duasCartasViradas) {
                // Trava a tela por 1 segundo (1000ms) para o jogador ver as cartas
                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        jogo.checarPar();
                        atualizarTela();
                        timer.stop();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }
}