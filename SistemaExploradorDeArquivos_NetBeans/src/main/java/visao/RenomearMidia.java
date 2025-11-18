package visao;

import controle.ExploradorDeArquivos;
import modelo.midias.Midia;
import util.JOptionPaneUtil;

import javax.swing.*;
import java.awt.*;

public class RenomearMidia extends JPanel {

    private JLabel lblTitulo;
    private JLabel lblNome;

    private JTextField campoNome;

    private JButton botaoCancelar;
    private JButton botaoRenomear;

    private ExploradorDeArquivos explorador;
    private Midia midiaRenomeando;

    public RenomearMidia(ExploradorDeArquivos explorador, Midia midia) {
        this.explorador = explorador;
        this.midiaRenomeando = midia;
        initComponents();
    }

    private void initComponents() {

        // Painel REAL do conteúdo
        JPanel conteudo = new JPanel();
        conteudo.setBackground(new Color(247, 247, 255));

        GroupLayout layout = new GroupLayout(conteudo);
        conteudo.setLayout(layout);

        lblTitulo = new JLabel("Renomeando");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblNome = new JLabel("Nome");
        lblNome.setHorizontalAlignment(SwingConstants.CENTER);

        campoNome = new JTextField();

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(evt -> botaoCancelarAcao());

        botaoRenomear = new JButton("Renomear");
        botaoRenomear.addActionListener(evt -> botaoRenomearAcao());

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lblTitulo, 250, 250, 250)
                        .addComponent(lblNome, 250, 250, 250)
                        .addComponent(campoNome, 250, 250, 250)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(botaoCancelar, 120, 120, 120)
                                        .addGap(10)
                                        .addComponent(botaoRenomear, 120, 120, 120)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(25)
                        .addComponent(lblNome)
                        .addComponent(campoNome, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(40)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(botaoCancelar, 32, 32, 32)
                                        .addComponent(botaoRenomear, 32, 32, 32)
                        )
        );

        // Agora o truque: centralizar usando BoxLayout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(247, 247, 255));

        add(Box.createVerticalGlue());  // empurra pra cima
        add(conteudo);                  // conteúdo centralizado
        add(Box.createVerticalGlue());  // empurra pra baixo

        conteudo.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void botaoRenomearAcao() {
        try {
            if (campoNome.getText().isBlank()) {
                JOptionPaneUtil.mostrarMensagemErro("Informe um nome para renomear");
            } else {
                String novoNome = campoNome.getText();
                explorador.renomearMidia(midiaRenomeando, novoNome);
                JOptionPaneUtil.mostrarMensagemSucesso("Mídia renomeada com sucesso!");
            }
        } catch (Exception excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Erro ao tentar renomear!");
        }
    }

    private void botaoCancelarAcao() {
        explorador.exploradorLimparPainelDireito();
    }
}
