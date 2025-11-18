package util;

import javax.swing.*;

public class JOptionPaneUtil {
    public static void mostrarMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarMensagemSucesso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarMensagemConfirmacao(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Ok", JOptionPane.QUESTION_MESSAGE);
    }
}
