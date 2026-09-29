
package jdv;

import javax.swing.*;

public class JDV {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "Digite o tamanho do tabuleiro (mínimo 3):");

        if (input != null) {
            try {
                int tamanho = Integer.parseInt(input);

                if (tamanho < 3) {
                    JOptionPane.showMessageDialog(null, "O tamanho do tabuleiro deve ser 3 ou maior.");
                } else {
                    SwingUtilities.invokeLater(() -> {
                        JogoDaVelha jogo = new JogoDaVelha(tamanho);
                        jogo.setVisible(true); // Abre a janela com o painel gráfico
                    });
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, digite um número inteiro válido.");
            }
        }
    }
}