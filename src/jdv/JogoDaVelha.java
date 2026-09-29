package jdv;

import javax.swing.*;
import java.awt.*;

public class JogoDaVelha extends JFrame {

    private Tabuleiro tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogadorAtual;
    
    private PainelTabuleiro painelTabuleiro;
    private JLabel statusLabel;

    public JogoDaVelha(int tamanho) {
        this.tabuleiro = new Tabuleiro(tamanho);
        this.jogador1 = new Jogador("Jogador 1", 'X');
        this.jogador2 = new Jogador("Jogador 2", 'O');
        this.jogadorAtual = jogador1;
        
        setTitle("Jogo da Velha Inverso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        statusLabel = new JLabel("Vez de: " + jogadorAtual.getNome() + " (" + jogadorAtual.getSimbolo() + ")", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(statusLabel, BorderLayout.NORTH);

        painelTabuleiro = new PainelTabuleiro(tamanho, this);
        add(painelTabuleiro, BorderLayout.CENTER);

        setSize(120 * tamanho, 120 * tamanho);
        setLocationRelativeTo(null);
    }

    public void fazerJogada(int linha, int coluna) {
        if (!tabuleiro.posicaoDisponivel(linha, coluna)) return;

        tabuleiro.colocarPeca(linha, coluna, jogadorAtual.getSimbolo());
        painelTabuleiro.marcarJogada(linha, coluna, jogadorAtual.getSimbolo());

        if (verificarFimDeJogo()) {
            painelTabuleiro.desabilitarTabuleiro();
            return;
        }

        trocarJogador();
        statusLabel.setText("Vez de: " + jogadorAtual.getNome() + " (" + jogadorAtual.getSimbolo() + ")");
    }

    private void trocarJogador() {
        jogadorAtual = (jogadorAtual == jogador1) ? jogador2 : jogador1;
    }

    private boolean verificarFimDeJogo() {
        // 1. Quem acabou de jogar formou linha -> Perdeu
        if (tabuleiro.verificarAlinhamento(jogadorAtual.getSimbolo())) {
            Jogador vencedor = (jogadorAtual == jogador1) ? jogador2 : jogador1;
            JOptionPane.showMessageDialog(this, jogadorAtual.getNome() + " formou linha e perdeu!\n" + vencedor.getNome() + " venceu!");
            return true;
        }

        // 2. Tabuleiro totalmente preenchido
        if (tabuleiro.estaCheio()) {
            JOptionPane.showMessageDialog(this, "Empate! Deu velha.");
            return true;
        }

        // 3. Checa se o próximo jogador tem jogadas seguras
        Jogador proximo = (jogadorAtual == jogador1) ? jogador2 : jogador1;
        if (!tabuleiro.existeJogadaSegura(proximo.getSimbolo())) {
            JOptionPane.showMessageDialog(this, proximo.getNome() + " não tem nenhuma jogada segura!\n" + jogadorAtual.getNome() + " venceu!");
            return true;
        }

        return false;
    }
}