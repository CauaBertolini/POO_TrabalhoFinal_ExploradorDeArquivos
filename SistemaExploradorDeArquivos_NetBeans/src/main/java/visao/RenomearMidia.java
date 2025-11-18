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

        // ---------- COMPONENTES (Inicialização) ----------
        lblTitulo = new JLabel("Renomeando");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblNome = new JLabel("Nome");

        campoNome = new JTextField();

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(evt -> botaoCancelarAcao());

        botaoRenomear = new JButton("Renomear");
        botaoRenomear.addActionListener(evt -> botaoRenomearAcao());

        // ---------- LAYOUT (Aplicando MOLAS diretamente em 'this') ----------

        // Aplicamos a cor e a borda no painel principal (THIS)
        this.setBackground(new Color(247, 247, 255));
        this.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 255)));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true); // Adiciona padding interno

        // --- HORIZONTAL (Com "MOLAS" para centralizar o conteúdo) ---
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        // 1. Título (Ocupa a largura total e se auto-centraliza)
                        .addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)

                        // 2. Grupo do Formulário (centralizado com molas)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola esquerda

                                // Conteúdo do formulário
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblNome)
                                                // Usa um espaço flexível para empurrar o campo para a direita
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 20, 30)
                                                .addComponent(campoNome, 200, 200, 250)
                                        )
                                )
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola direita
                        )

                        // 3. Grupo dos Botões (centralizado com molas)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola esquerda
                                .addComponent(botaoCancelar, 120, 120, 120)
                                .addGap(10)
                                .addComponent(botaoRenomear, 120, 120, 120)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola direita
                        )
        );

        // --- VERTICAL (Linha por Linha) ---
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(25)

                        // Linha 1: Nome (rótulo e campo lado a lado)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNome)
                                .addComponent(campoNome, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        )

                        .addGap(40)

                        // Linha 2: Botões
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(botaoCancelar, 32, 32, 32)
                                .addComponent(botaoRenomear, 32, 32, 32)
                        )
        );
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
