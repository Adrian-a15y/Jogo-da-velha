package jdv;

import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro extends JPanel {
    private JLabel[][] celulas;

    public PainelTabuleiro(int tamanho) {
        setLayout(new GridLayout(tamanho, tamanho, 2, 2));
        setBackground(Color.BLACK);
        celulas = new JLabel[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                JLabel celula = new JLabel("", SwingConstants.CENTER);
                celula.setFont(new Font("Arial", Font.BOLD, 28));
                celula.setOpaque(true);
                celula.setBackground(Color.WHITE);

                celulas[i][j] = celula;
                add(celula);
            }
        }
    }

    public void marcarJogada(int linha, int coluna, char simbolo) {
        celulas[linha][coluna].setText(String.valueOf(simbolo));
    }
}