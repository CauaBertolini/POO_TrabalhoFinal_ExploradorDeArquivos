package util;

import javax.swing.*;

/**
 * Classe utilitária para exibição padronizada de mensagens via {@link JOptionPane}.
 * <p>
 * Fornece métodos estáticos para apresentar mensagens de erro, sucesso e confirmação,
 * garantindo consistência visual e centralização das chamadas de diálogo na aplicação.
 */
public class JOptionPaneUtil {

    /**
     * Exibe uma mensagem de erro utilizando um diálogo do tipo ERROR_MESSAGE.
     *
     * @param mensagem texto da mensagem a ser exibida.
     */
    public static void mostrarMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Exibe uma mensagem de sucesso utilizando um diálogo do tipo INFORMATION_MESSAGE.
     *
     * @param mensagem texto da mensagem a ser exibida.
     */
    public static void mostrarMensagemSucesso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
    }
}
