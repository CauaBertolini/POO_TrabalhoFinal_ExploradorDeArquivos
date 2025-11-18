package visao;

import controle.ExploradorDeArquivos;
import util.JOptionPaneUtil;

import java.awt.*;
import javax.swing.*;

public class CarregarArquivo extends JPanel {

    private JLabel lblTitulo;
    private JTextField campoCaminho;
    private JButton botaoProcurar;
    private JButton botaoCancelar;
    private JButton botaoCarregar;

    private ExploradorDeArquivos explorador;

    // Construtor que recebe o explorador de arquivos
    public CarregarArquivo(ExploradorDeArquivos explorador) {
        this.explorador = explorador;
        initComponents();
    }

    private void initComponents() {

        // ---------- COMPONENTES (Inicialização) ----------
        lblTitulo = new JLabel("Carregar Arquivo");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22)); // Padrão dos outros painéis
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        campoCaminho = new JTextField();

        botaoProcurar = new JButton("Procurar...");
        botaoProcurar.addActionListener(evt -> botaoProcurarAcao());

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(evt -> botaoCancelarAcao());

        botaoCarregar = new JButton("Carregar");
        botaoCarregar.addActionListener(evt -> botaoCarregarAcao());


        // ---------- LAYOUT (Padrão: Molas para centralização) ----------

        // Aplicamos a cor e a borda no painel principal (THIS)
        this.setBackground(new Color(247, 247, 255));
        this.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 255)));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // --- HORIZONTAL (Com "MOLAS" para centralizar o conteúdo) ---
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        // 1. Título (Ocupa a largura total e se auto-centraliza)
                        .addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)

                        // 2. Grupo do Campo/Botão Procurar (centralizado com molas)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola esquerda

                                .addGroup(layout.createSequentialGroup()
                                        // Campo de caminho com tamanho definido
                                        .addComponent(campoCaminho, 250, 250, 350)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botaoProcurar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )

                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola direita
                        )

                        // 3. Grupo dos Botões Ação (centralizado com molas)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola esquerda
                                .addComponent(botaoCancelar, 120, 120, 120)
                                .addGap(10)
                                .addComponent(botaoCarregar, 120, 120, 120)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola direita
                        )
        );

        // --- VERTICAL (Linha por Linha) ---
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(40) // Espaço superior
                        .addComponent(lblTitulo)
                        .addGap(40) // Espaço após o título

                        // Linha 1: Campo e Botão Procurar
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(campoCaminho, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoProcurar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        )

                        .addGap(50) // Espaço antes dos botões

                        // Linha 2: Botões Cancelar e Carregar
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(botaoCancelar, 32, 32, 32)
                                .addComponent(botaoCarregar, 32, 32, 32)
                        )
                        .addGap(40) // Espaço inferior
        );
    }

    // ---------- AÇÕES DOS BOTÕES ----------

    private void botaoProcurarAcao() {
        // Usa o seletor de arquivo (não de diretório) para carregar um arquivo de mídia
        String caminhoSelecionado = explorador.abrirSeletorDeArquivoTpoo();
        if (caminhoSelecionado != null) {
            campoCaminho.setText(caminhoSelecionado);
        }
    }

    private void botaoCarregarAcao() {
        String caminho = campoCaminho.getText();
        if (caminho.isBlank()) {
            JOptionPaneUtil.mostrarMensagemErro("Selecione um arquivo para carregar.");
            return;
        }

        try {
            // A lógica de carregar o arquivo e determinar seu tipo deve estar no ExploradorDeArquivos
            explorador.carregarArquivo(caminho);
            JOptionPaneUtil.mostrarMensagemSucesso("Arquivo carregado e mídia adicionada com sucesso!");
            explorador.exploradorLimparPainelDireito();
        } catch (Exception excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Erro ao carregar o arquivo: " + excecao.getMessage());
        }
    }

    private void botaoCancelarAcao() {
        // Limpa o painel direito, retornando à Home Page
        explorador.exploradorLimparPainelDireito();
    }
}