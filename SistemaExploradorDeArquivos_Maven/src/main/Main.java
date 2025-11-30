package controle;

import visao.PaginaPrincipal;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new PaginaPrincipal().setVisible(true);
        });
    }
}