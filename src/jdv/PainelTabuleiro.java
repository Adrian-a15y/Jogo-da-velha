package jdv;
import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro extends JPanel {

    private JButton[][] botoes;

    public PainelTabuleiro(int tamanho, JogoDaVelha jogo) {
        setLayout(new GridLayout(tamanho, tamanho, 5, 5));
        botoes = new JButton[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                JButton botao = new JButton("");
                botao.setFont(new Font("Arial", Font.BOLD, 24));
                botao.setFocusable(false);

                final int linha = i;
                final int coluna = j;

                botao.addActionListener(e -> jogo.fazerJogada(linha, coluna));

                botoes[i][j] = botao;
                add(botao);
            }
        }
    }

    public void marcarJogada(int linha, int coluna, char simbolo) {
        botoes[linha][coluna].setText(String.valueOf(simbolo));
        botoes[linha][coluna].setEnabled(false);
    }

    public void desabilitarTabuleiro() {
        for (JButton[] linha : botoes) {
            for (JButton btn : linha) {
                btn.setEnabled(false);
            }
        }
    }
}