package jdv;

public class Tabuleiro {
    private char[][] tabuleiro;
    private int tamanho;

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.tabuleiro = new char[tamanho][tamanho];
    }

    public boolean posicaoDisponivel(int linha, int coluna) {
        return linha >= 0 && linha < tamanho && coluna >= 0 && coluna < tamanho && tabuleiro[linha][coluna] == '\0';
    }

    public void colocarPeca(int linha, int coluna, char simbolo) {
        tabuleiro[linha][coluna] = simbolo;
    }

    public boolean verificarAlinhamento(char simbolo) {
        for (int i = 0; i < tamanho; i++) {
            boolean linhaOk = true;
            boolean colunaOk = true;

            for (int j = 0; j < tamanho; j++) {
                if (tabuleiro[i][j] != simbolo) {
                    linhaOk = false;
                }
                if (tabuleiro[j][i] != simbolo) {
                    colunaOk = false;
                }
            }

            if (linhaOk || colunaOk) {
                return true;
            }
        }

        boolean diag1 = true;
        boolean diag2 = true;

        for (int i = 0; i < tamanho; i++) {
            if (tabuleiro[i][i] != simbolo) {
                diag1 = false;
            }
            if (tabuleiro[i][tamanho - 1 - i] != simbolo) {
                diag2 = false;
            }
        }

        return diag1 || diag2;
    }

    public boolean estaCheio() {
        for (char[] linha : tabuleiro) {
            for (char celula : linha) {
                if (celula == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean existeJogadaSegura(char simbolo) {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (tabuleiro[i][j] == '\0') {
                    tabuleiro[i][j] = simbolo;
                    boolean alinhou = verificarAlinhamento(simbolo);
                    tabuleiro[i][j] = '\0';

                    if (!alinhou) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}