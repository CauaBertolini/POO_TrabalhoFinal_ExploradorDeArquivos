package visao;

import controle.ExploradorDeArquivos;
import modelo.midias.Midia;
import util.JOptionPaneUtil;

import java.awt.*;
import javax.swing.*;

public class MoverMidia extends JPanel {

    private JLabel lblTitulo;
    private JLabel lblInforme;
    private JTextField campoCaminho;
    private JButton botaoProcurar;
    private JButton botaoCancelar;
    private JButton botaoConfirmar;

    private ExploradorDeArquivos explorador;
    private Midia midiaMovendo;

    public MoverMidia(ExploradorDeArquivos explorador,  Midia midiaMovendo) {
        this.explorador = explorador;
        this.midiaMovendo = midiaMovendo;

        initComponents();

    }

    private void initComponents() {

        // ---------- COMPONENTES (Inicialização) ----------
        lblTitulo = new JLabel("Movendo Mídia");
        // Ajuste: Tamanho da fonte para 22 (igual ao Renomear)
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblInforme = new JLabel("Informe para onde quer mover");

        campoCaminho = new JTextField();

        botaoProcurar = new JButton("Procurar...");
        botaoProcurar.addActionListener(evt -> botaoProcurarAcao());

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(evt -> botaoCancelarAcao());

        botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.addActionListener(evt -> botaoConfirmarAcao());


        // ---------- LAYOUT (Aplicando as cores e bordas diretamente em 'this') ----------

        // Ajuste: Cor de fundo igual ao Renomear (247, 247, 255)
        this.setBackground(new Color(247, 247, 255));
        // Ajuste: Borda igual ao Renomear (cor 220, 220, 255 e espessura 1)
        this.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 255)));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // --- HORIZONTAL (Com "MOLAS" para centralizar) ---
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        // 1. Título (Ocupa a largura total do card, se expande com as molas)
                        // A largura mínima e preferencial é 300 (igual ao Renomear)
                        .addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)

                        // 2. Grupo do Formulário (centralizado com molas)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola esquerda

                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblInforme)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(campoCaminho, 250, 250, 350) // Tamanho do campo
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(botaoProcurar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        )
                                )
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola direita
                        )

                        // 3. Grupo dos Botões (centralizado com molas)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola esquerda
                                .addComponent(botaoCancelar, 90, 90, 90)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoConfirmar, 90, 90, 90)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola direita
                        )
        );

        // --- VERTICAL (Linha por linha) ---
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        // Ajuste: Espaçamento de 25 (igual ao Renomear)
                        .addGap(25)
                        .addComponent(lblInforme)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(campoCaminho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoProcurar)
                        )
                        // Ajuste: Espaçamento de 40 (igual ao Renomear)
                        .addGap(40)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(botaoConfirmar)
                                .addComponent(botaoCancelar)
                        )
        );
    }

    private void botaoConfirmarAcao() {
        try {
            if (campoCaminho.getText().isBlank()) {
                JOptionPaneUtil.mostrarMensagemErro("Informe um nome para renomear");
            } else {
                String campoCaminhoNovo = campoCaminho.getText();
                explorador.moverMidia(midiaMovendo, campoCaminho.getText());
                JOptionPaneUtil.mostrarMensagemSucesso("Mídia movida com sucesso!");
            }
        } catch (Exception excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Erro ao tentar renomear!");
        }


    }

    private void botaoCancelarAcao() {
        explorador.exploradorLimparPainelDireito();
    }

    private void botaoProcurarAcao() {
        String caminhoSelecionado = explorador.abrirSeletorDeDiretorio();
        if (caminhoSelecionado != null) {
            campoCaminho.setText(caminhoSelecionado);
        }
    }

}
