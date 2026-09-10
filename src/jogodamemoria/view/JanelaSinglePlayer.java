package jogodamemoria.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import jogodamemoria.controller.AudioController;
import jogodamemoria.controller.JogoController;
import jogodamemoria.controller.JogoController.ResultadoJogada;
import jogodamemoria.controller.NavegacaoController;
import jogodamemoria.model.Jogador;
import jogodamemoria.model.Tabuleiro;
import jogodamemoria.view.componentes.GerenciadorFontes;
import jogodamemoria.view.componentes.GerenciadorSprites;

public class JanelaSinglePlayer extends JPanel implements ActionListener {

    private Tabuleiro tabuleiro;
    private Jogador jogador;

    private NavegacaoController navegacaoController;
    private JogoController gerenciador;
    private int indexPrimeiraCarta = -1;

    private JLabel lblPontos;
    private boolean tabuleiroBloqueado = false;

    private JLabel lblTempo;
    private int segundosDecorridos = 0;
    private Timer cronometro;

    private JPanel painelTabuleiro;
    ArrayList<JButton> botoesCartas = new ArrayList<>();

    // Tamanho fixo ideal para cada carta na tela (ajuste se precisar de botões maiores/menores)
    private static final int LARGURA_CARTA = 110;
    private static final int ALTURA_CARTA = 200;

    public JanelaSinglePlayer(Tabuleiro tabuleiro, Jogador jogador, NavegacaoController navegacaoController) {
        this.tabuleiro = tabuleiro;
        this.jogador = jogador;
        this.navegacaoController = navegacaoController;
        this.gerenciador = new JogoController(tabuleiro, jogador);

        setLayout(new BorderLayout(20, 20));

        String textoTitulo = "";
        int colunas = 0;
        int lines = 0;

        if (tabuleiro.getTamanho() == 12) {
            textoTitulo = "UM JOGADOR - MODO FÁCIL";
            colunas = 4;
            lines = 3;
        } else {
            textoTitulo = "UM JOGADOR - MODO PADRÃO";
            colunas = 5;
            lines = 4;
        }

        // SUPERIOR
        JLabel labelTitulo = new JLabel(textoTitulo, JLabel.CENTER);
        labelTitulo.setFont(GerenciadorFontes.obterFonte(Font.BOLD, 24f));

        JPanel superior = new JPanel(new GridLayout(1, 1));
        superior.add(labelTitulo);
        add(superior, BorderLayout.NORTH);

        // CENTRO
        painelTabuleiro = new JPanel(new GridLayout(lines, colunas, 15, 15));

        for (int i = 0; i < tabuleiro.getTamanho(); i++) {
            JButton botao = new JButton();
            
            // Define um tamanho fixo para o botão não deformar
            botao.setPreferredSize(new Dimension(LARGURA_CARTA, ALTURA_CARTA));
            
            // Carrega o verso da carta (coluna 0, linha 0 do spritesheet ou outra coordenada de verso)
            ImageIcon versoCarta = GerenciadorSprites.obterCarta(0, 0, LARGURA_CARTA, ALTURA_CARTA);
            botao.setIcon(versoCarta);
            
            // Configurações visuais para limpar a borda padrão do botão do Java
            botao.setBorderPainted(false);
            botao.setFocusPainted(false);
            botao.setContentAreaFilled(false);

            botoesCartas.add(botao);
            painelTabuleiro.add(botao);
            botao.addActionListener(this);
        }

        add(painelTabuleiro, BorderLayout.CENTER);

        // INFERIOR
        JLabel lblNome = new JLabel("Jogador: " + jogador.getNome(), JLabel.CENTER);
        lblPontos = new JLabel("Pares Feitos: 0", JLabel.CENTER);
        lblTempo = new JLabel("Tempo: 00:00", JLabel.CENTER);

        Font fonteHUD = new Font("Arial", Font.PLAIN, 18);
        lblNome.setFont(fonteHUD);
        lblPontos.setFont(fonteHUD);
        lblTempo.setFont(fonteHUD);

        JPanel inferior = new JPanel(new GridLayout(1, 3));
        inferior.add(lblNome);
        inferior.add(lblPontos);
        inferior.add(lblTempo);

        add(inferior, BorderLayout.SOUTH);

        cronometro = new Timer(1000, evento -> {
            segundosDecorridos++;

            int minutos = segundosDecorridos / 60;
            int segundos = segundosDecorridos % 60;
            String tempoFormatado = String.format("Tempo: %02d:%02d", minutos, segundos);

            lblTempo.setText(tempoFormatado);
        });

        cronometro.start();
        configurarBotaoEsc();
    }

    public void reiniciarJogo() {
        this.tabuleiro = new Tabuleiro(this.tabuleiro.getTamanho() / 2, false);
        this.jogador.resetarPontos();

        this.gerenciador = new JogoController(this.tabuleiro, this.jogador);
        this.indexPrimeiraCarta = -1;
        this.tabuleiroBloqueado = false;

        cronometro.stop();
        segundosDecorridos = 0;
        lblTempo.setText("Tempo: 00:00");
        lblPontos.setText("Pares Feitos: 0");

        painelTabuleiro.removeAll();
        botoesCartas.clear();

        for (int i = 0; i < tabuleiro.getTamanho(); i++) {
            JButton botao = new JButton();
            botao.setPreferredSize(new Dimension(LARGURA_CARTA, ALTURA_CARTA));
            
            ImageIcon versoCarta = GerenciadorSprites.obterCarta(0, 0, LARGURA_CARTA, ALTURA_CARTA);
            botao.setIcon(versoCarta);
            
            botao.setBorderPainted(false);
            botao.setFocusPainted(false);
            botao.setContentAreaFilled(false);

            botoesCartas.add(botao);
            painelTabuleiro.add(botao);
            botao.addActionListener(this);
        }
        painelTabuleiro.revalidate();
        painelTabuleiro.repaint();

        cronometro.start();
    }

    private void configurarBotaoEsc() {
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "acaoEsc");

        actionMap.put("acaoEsc", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navegacaoController.solicitarVoltarAoMenu(
                        JanelaSinglePlayer.this,
                        () -> cronometro.stop(), 
                        () -> cronometro.start() 
                );
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (tabuleiroBloqueado)
            return;

        for (int i = 0; i < botoesCartas.size(); i++) {
            if (e.getSource() == botoesCartas.get(i)) {

                ResultadoJogada resultado = gerenciador.processarCliqueCarta(i);
                String valorCarta = tabuleiro.getCarta(i).getValor();

                switch (resultado) {
                    case PRIMEIRA_CARTA_VIRADA:
                        botoesCartas.get(i).setIcon(GerenciadorSprites.obterIconePorValor(valorCarta, LARGURA_CARTA, ALTURA_CARTA));
                        indexPrimeiraCarta = i;
                        break;

                    case ACERTOU_PAR:
                        AudioController.tocarEfeito("/jogodamemoria/recursos/sons/acerto.wav");
                        botoesCartas.get(i).setIcon(GerenciadorSprites.obterIconePorValor(valorCarta, LARGURA_CARTA, ALTURA_CARTA));
                        lblPontos.setText("Pares Feitos: " + jogador.getPontuacao());
                        indexPrimeiraCarta = -1;
                        break;

                    case ERROU_PAR:
                        botoesCartas.get(i).setIcon(GerenciadorSprites.obterIconePorValor(valorCarta, LARGURA_CARTA, ALTURA_CARTA));
                        tabuleiroBloqueado = true;

                        int pBotao = indexPrimeiraCarta;
                        int sBotao = i;

                        Timer timer = new Timer(1000, evento -> {
                            botoesCartas.get(pBotao).setIcon(GerenciadorSprites.obterCarta(0, 0, LARGURA_CARTA, ALTURA_CARTA));
                            botoesCartas.get(sBotao).setIcon(GerenciadorSprites.obterCarta(0, 0, LARGURA_CARTA, ALTURA_CARTA));
                            tabuleiroBloqueado = false;
                        });
                        timer.setRepeats(false);
                        timer.start();
                        break;

                    case VITORIA:
                        botoesCartas.get(i).setIcon(GerenciadorSprites.obterIconePorValor(valorCarta, LARGURA_CARTA, ALTURA_CARTA));
                        lblPontos.setText("Pares Feitos: " + jogador.getPontuacao());
                        cronometro.stop();
                        AudioController.tocarEfeito("/jogodamemoria/recursos/sons/vitoria.wav");
                        navegacaoController.exibirVitoria(this, gerenciador.getTentativas(), lblTempo.getText(),
                                jogador, tabuleiro);
                        break;

                    case IGNORAR:
                    default:
                        break;
                }
                break;
            }
        }
    }
}