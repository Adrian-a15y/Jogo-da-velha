package jdv;

import javax.swing.*;

public class JDV {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "Digite o tamanho do tabuleiro (N >= 3):", "Jogo da Velha Inverso", JOptionPane.QUESTION_MESSAGE);
        
        if (input == null) {
            return;
        }

        try {
            int tamanho = Integer.parseInt(input);
            if (tamanho < 3) {
                JOptionPane.showMessageDialog(null, "O tamanho mínimo do tabuleiro é 3.");
                return;
            }

            SwingUtilities.invokeLater(() -> {
                JogoDaVelha jogo = new JogoDaVelha(tamanho);
                jogo.setVisible(true);
            });

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite um número inteiro válido.");
        }
    }
}