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

    private JTextField txtLinha;
    private JTextField txtColuna;
    private JButton btnJogar;

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

        painelTabuleiro = new PainelTabuleiro(tamanho);
        add(painelTabuleiro, BorderLayout.CENTER);

        JPanel painelEntrada = new JPanel(new FlowLayout());
        txtLinha = new JTextField(3);
        txtColuna = new JTextField(3);
        btnJogar = new JButton("Jogar");

        painelEntrada.add(new JLabel("Linha (1 a " + tamanho + "):"));
        painelEntrada.add(txtLinha);
        painelEntrada.add(new JLabel("Coluna (1 a " + tamanho + "):"));
        painelEntrada.add(txtColuna);
        painelEntrada.add(btnJogar);

        add(painelEntrada, BorderLayout.SOUTH);

        btnJogar.addActionListener(e -> processarJogada());

        setSize(140 * tamanho, 140 * tamanho);
        setLocationRelativeTo(null);
    }

    private void processarJogada() {
        try {
            int linha = Integer.parseInt(txtLinha.getText().trim()) - 1;
            int coluna = Integer.parseInt(txtColuna.getText().trim()) - 1;

            if (!tabuleiro.posicaoDisponivel(linha, coluna)) {
                JOptionPane.showMessageDialog(this, "Posição inválida ou já ocupada!");
                return;
            }

            tabuleiro.colocarPeca(linha, coluna, jogadorAtual.getSimbolo());
            painelTabuleiro.marcarJogada(linha, coluna, jogadorAtual.getSimbolo());

            if (verificarFimDeJogo()) {
                btnJogar.setEnabled(false);
                txtLinha.setEnabled(false);
                txtColuna.setEnabled(false);
                return;
            }

            trocarJogador();
            statusLabel.setText("Vez de: " + jogadorAtual.getNome() + " (" + jogadorAtual.getSimbolo() + ")");

            txtLinha.setText("");
            txtColuna.setText("");
            txtLinha.requestFocus();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite apenas números válidos!");
        }
    }

    private void trocarJogador() {
        if (jogadorAtual == jogador1) {
            jogadorAtual = jogador2;
        } else {
            jogadorAtual = jogador1;
        }
    }

    private boolean verificarFimDeJogo() {
        if (tabuleiro.verificarAlinhamento(jogadorAtual.getSimbolo())) {
            Jogador vencedor;
            if (jogadorAtual == jogador1) {
                vencedor = jogador2;
            } else {
                vencedor = jogador1;
            }
            JOptionPane.showMessageDialog(this, jogadorAtual.getNome() + " formou linha e perdeu!\n" + vencedor.getNome() + " venceu!");
            return true;
        }

        if (tabuleiro.estaCheio()) {
            JOptionPane.showMessageDialog(this, "Empate! Deu velha.");
            return true;
        }

        Jogador proximo;
        if (jogadorAtual == jogador1) {
            proximo = jogador2;
        } else {
            proximo = jogador1;
        }

        if (!tabuleiro.existeJogadaSegura(proximo.getSimbolo())) {
            JOptionPane.showMessageDialog(this, proximo.getNome() + " não tem nenhuma jogada segura!\n" + jogadorAtual.getNome() + " venceu!");
            return true;
        }

        return false;
    }
}