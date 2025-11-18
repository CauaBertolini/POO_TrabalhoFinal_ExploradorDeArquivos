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

        // ---------- COMPONENTES (Sem alteração) ----------
        lblTitulo = new JLabel("Movendo Mídia");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblInforme = new JLabel("Informe para onde quer mover");

        campoCaminho = new JTextField();

        botaoProcurar = new JButton("Procurar...");
        botaoProcurar.addActionListener(evt -> botaoProcurarAcao());

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(evt -> botaoCancelarAcao());

        botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.addActionListener(evt -> botaoConfirmarAcao());


        // ---------- 1. PAINEL DE CONTEÚDO (O "CARD" INTERNO) ----------
        // Este é o novo painel que vai segurar os componentes
        JPanel pnlConteudo = new JPanel();
        pnlConteudo.setBackground(new Color(245, 245, 245));
        pnlConteudo.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 255), 2));

        // ---------- 2. LAYOUT DO PAINEL DE CONTEÚDO (GroupLayout) ----------
        // Aplicamos o GroupLayout a 'pnlConteudo', não a 'this'
        GroupLayout layoutConteudo = new GroupLayout(pnlConteudo);
        pnlConteudo.setLayout(layoutConteudo);

        layoutConteudo.setAutoCreateGaps(true);
        layoutConteudo.setAutoCreateContainerGaps(true); // Adiciona padding interno

        // --- HORIZONTAL (PAINEL INTERNO) ---
        layoutConteudo.setHorizontalGroup(
                layoutConteudo.createParallelGroup(GroupLayout.Alignment.LEADING)
                        // Definimos um tamanho preferido para o "card" não esticar
                        .addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblInforme)
                        .addGroup(layoutConteudo.createSequentialGroup()
                                .addComponent(campoCaminho, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Deixa o campo crescer
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoProcurar)
                        )
                        .addGroup(GroupLayout.Alignment.TRAILING, layoutConteudo.createSequentialGroup()
                                .addComponent(botaoCancelar, 90, 90, 90) // Tamanho fixo para botões
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoConfirmar, 90, 90, 90) // Tamanho fixo para botões
                        )
        );

        // --- VERTICAL (PAINEL INTERNO) ---
        layoutConteudo.setVerticalGroup(
                layoutConteudo.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(18)
                        .addComponent(lblInforme)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layoutConteudo.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(campoCaminho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoProcurar)
                        )
                        .addGap(30)
                        .addGroup(layoutConteudo.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(botaoConfirmar)
                                .addComponent(botaoCancelar)
                        )
        );

        // ---------- 3. LAYOUT DO PAINEL PRINCIPAL (this) ----------
        // Este painel (MoverMidia) usará GridBagLayout apenas para centralizar o pnlConteudo
        // O fazemos transparente para que a cor de fundo do seu explorador apareça
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());

        // Configurações do GridBagLayout para centralizar
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza o componente
        gbc.fill = GridBagConstraints.NONE;   // Não estica o pnlConteudo
        gbc.insets = new Insets(20, 20, 20, 20); // Margem externa

        // Adiciona o painel de conteúdo (com seu próprio GroupLayout) ao painel principal
        this.add(pnlConteudo, gbc);
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
