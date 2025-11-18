package visao;

import controle.ExploradorDeArquivos;
import excecao.ArquivoNaoExisteExcecao;
import modelo.*;
import modelo.midias.Filme;
import modelo.midias.Musica;
import modelo.midias.Livro;
import modelo.midias.Midia;
import util.ComboUtil;
import util.JOptionPaneUtil;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HomePage extends javax.swing.JFrame {
    private javax.swing.JLabel labelFiltroGenero;
    private javax.swing.JLabel labelFiltroTipo;
    private javax.swing.JButton botaoAdicionarMidia;
    private javax.swing.JButton botaoAlterarMidia;
    private javax.swing.JButton botaoApagarMidia;
    private javax.swing.JButton botaoAtualizarTabela;
    private javax.swing.JButton botaoRenomear;
    private javax.swing.JButton botaoMover;
    private javax.swing.JButton botaoCarregarMidia;
    private javax.swing.JComboBox<String> generoCombo;
    private javax.swing.JComboBox<String> tipoCombo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaMidias;
    private javax.swing.JPanel painelDireito;

    private Listas lista;
    private Salvamento salvamento;
    private ExploradorDeArquivos explorador;

    public HomePage() {
        this.explorador = new ExploradorDeArquivos(this);

        initComponents();
        this.salvamento = explorador.getSalvamento();
        this.lista = new Listas();

        configurarTabela();

        removerListenersFiltros();

        configurarFiltros();

        adicionarListenersFiltros();

        filtrarTabela();

        setVisible(true);

        explorador.carregarMidiasCSV();

    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        painelDireito = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMidias = new javax.swing.JTable();

        setTitle("Sistema de Gerenciamento de Mídias");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        javax.swing.JPanel painelEsquerdo = new javax.swing.JPanel();
        painelEsquerdo.setBackground(new java.awt.Color(240, 240, 240));
        painelEsquerdo.setLayout(new BorderLayout(10, 10));
        painelEsquerdo.setPreferredSize(new Dimension(800, 600));
        painelEsquerdo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Novo layout: Usaremos um container principal para organizar
        // 1. O painel dos 6 botões de ação
        // 2. O painel do botão Carregar Mídia
        // 3. Os filtros

        // Criando um painel específico para os 6 botões (3 linhas x 2 colunas)
        JPanel painelBotoesAcao = new JPanel(new GridLayout(3, 2, 8, 8));
        painelBotoesAcao.setOpaque(false);

        botaoAdicionarMidia = new javax.swing.JButton("Adicionar Mídia +");
        botaoAdicionarMidia.addActionListener(evt -> abrirSelecaoMidia());
        painelBotoesAcao.add(botaoAdicionarMidia);

        botaoApagarMidia = new javax.swing.JButton("Apagar Mídia -");
        botaoApagarMidia.addActionListener(evt -> botaoDeletarMidiaAcao());
        painelBotoesAcao.add(botaoApagarMidia);

        botaoAlterarMidia = new javax.swing.JButton("Alterar Mídia");
        botaoAlterarMidia.addActionListener(evt -> botaoAlterarAcao());
        painelBotoesAcao.add(botaoAlterarMidia);

        botaoMover = new javax.swing.JButton("Mover Mídia");
        botaoMover.addActionListener(evt -> botaoMoverAcao());
        painelBotoesAcao.add(botaoMover);

        botaoRenomear = new javax.swing.JButton("Renomear Mídia");
        botaoRenomear.addActionListener(evt -> botaoRenomearAcao());
        painelBotoesAcao.add(botaoRenomear);

        botaoAtualizarTabela = new javax.swing.JButton("Atualizar Lista");
        botaoAtualizarTabela.addActionListener(evt -> botaoAtualizarTabelaAcao());
        painelBotoesAcao.add(botaoAtualizarTabela);


        botaoCarregarMidia = new javax.swing.JButton("Carregar Mídia");
        botaoCarregarMidia.addActionListener(evt -> botaoCarregarMidiaAcao());

        // Painel para centralizar o botão Carregar Mídia em uma linha
        JPanel painelCentralizado = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        painelCentralizado.setOpaque(false);
        painelCentralizado.add(botaoCarregarMidia);

        // Painel de Filtros (rótulos e combos)
        labelFiltroGenero = new javax.swing.JLabel("Filtro Gênero");
        labelFiltroTipo = new javax.swing.JLabel("Filtro Tipo Arquivo");

        labelFiltroGenero.setVerticalAlignment(SwingConstants.BOTTOM);
        labelFiltroTipo.setVerticalAlignment(SwingConstants.BOTTOM);

        generoCombo = new javax.swing.JComboBox<>();
        tipoCombo = new javax.swing.JComboBox<>();

        // Usaremos um GridBagLayout para organizar verticalmente
        JPanel painelAcoesFiltros = new JPanel(new GridBagLayout());
        painelAcoesFiltros.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0); // Espaçamento vertical

        // --- Adiciona Painel de 6 Botões ---
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        painelAcoesFiltros.add(painelBotoesAcao, gbc);

        // --- Adiciona Botão Centralizado ---
        gbc.gridy = 1;
        gbc.insets = new Insets(8, 0, 15, 0); // Mais espaço após o botão
        painelAcoesFiltros.add(painelCentralizado, gbc);

        // --- Adiciona Rótulos de Filtro ---
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE; // Não preencher horizontalmente

        JPanel pnlLabels = new JPanel(new GridLayout(1, 2, 8, 8));
        pnlLabels.setOpaque(false);
        pnlLabels.add(labelFiltroGenero);
        pnlLabels.add(labelFiltroTipo);

        painelAcoesFiltros.add(pnlLabels, gbc);

        // --- Adiciona ComboBoxes de Filtro ---
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Preencher horizontalmente

        JPanel pnlCombos = new JPanel(new GridLayout(1, 2, 8, 8));
        pnlCombos.setOpaque(false);
        pnlCombos.add(generoCombo);
        pnlCombos.add(tipoCombo);

        painelAcoesFiltros.add(pnlCombos, gbc);


        // Substituindo painelSuperior pelo novo painelAcoesFiltros
        painelEsquerdo.add(painelAcoesFiltros, BorderLayout.NORTH);

        jScrollPane1.setViewportView(tabelaMidias);
        painelEsquerdo.add(jScrollPane1, BorderLayout.CENTER);

        add(painelEsquerdo, BorderLayout.WEST);

        painelDireito.setBackground(new java.awt.Color(247, 247, 255));
        painelDireito.setLayout(new BorderLayout());
        add(painelDireito, BorderLayout.CENTER);
    }

    private void botaoCarregarMidiaAcao() {
        abrirNoPainelDireito(new CarregarArquivo(explorador));
    }

    private void botaoDeletarMidiaAcao() {
        try {
            Midia midiaSelecionada = getMidiaSelecionada();

            if (explorador.excluirMidia(midiaSelecionada)) {
                JOptionPaneUtil.mostrarMensagemSucesso("Mídia excluída com sucesso!");
            } else {
                JOptionPaneUtil.mostrarMensagemErro("Erro ao tentar excluir mídia.");
            }
        } catch (ArquivoNaoExisteExcecao excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Selecione um item da tabela para excluir!");
        }

    }

    public void botaoAlterarAcao() {
        try {
            abrirAlterarMidia();
        } catch (ArquivoNaoExisteExcecao excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Selecione um item da tabela para alterar!");
        }
    }

    private void botaoAtualizarTabelaAcao() {
        atualizarTabela();
    }

    private void botaoRenomearAcao() {
        try {
            Midia midiaSelecionada = getMidiaSelecionada();

            abrirNoPainelDireito(new RenomearMidia(explorador, midiaSelecionada));

        } catch (ArquivoNaoExisteExcecao excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Selecione um item da tabela para renomear!");
        }

    }

    private void botaoMoverAcao() {
        try {
            Midia midiaSelecionada = getMidiaSelecionada();

            abrirNoPainelDireito(new MoverMidia(explorador, midiaSelecionada));

        } catch (ArquivoNaoExisteExcecao excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Selecione um item da tabela para mover!");
        }
    }

    private void configurarTabela() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Caminho", "Tamanho (MB)"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaMidias.setModel(modelo);
    }

    private void removerListenersFiltros() {
        for (ActionListener al : tipoCombo.getActionListeners()) {
            tipoCombo.removeActionListener(al);
        }
        for (ActionListener al : generoCombo.getActionListeners()) {
            generoCombo.removeActionListener(al);
        }
    }

    private void configurarFiltros() {
        ComboUtil.carregarTiposArquivoParaFiltro(tipoCombo);
        ComboUtil.carregarGenerosParaFiltro(generoCombo, lista.getListaGeneros());
    }

    private void adicionarListenersFiltros() {
        tipoCombo.addActionListener(e -> filtrarTabela());
        generoCombo.addActionListener(e -> filtrarTabela());
    }

    public void atualizarTabela() {
        filtrarTabela();
    }

    private void filtrarTabela() {
        List<modelo.midias.Midia> listaFiltrada = explorador.filtrarMidias(
                generoCombo.getSelectedItem().toString(),
                tipoCombo.getSelectedItem().toString()
        );

        DefaultTableModel model = (DefaultTableModel) tabelaMidias.getModel();
        model.setRowCount(0);

        for (modelo.midias.Midia midia : listaFiltrada) {
            model.addRow(new Object[]{
                    midia.getNome() + "." + midia.getTipoArquivo().name().toLowerCase(),
                    midia.getTamanho()
            });
        }
    }

    private void abrirSelecaoMidia() {
        painelDireito.removeAll();
        painelDireito.setLayout(new BorderLayout());

        painelDireito.add(
                new SelecionarMidiaAdicionar(painelDireito, explorador),
                BorderLayout.CENTER
        );
        painelDireito.revalidate();
        painelDireito.repaint();
    }

    public void abrirAlterarMidia() throws ArquivoNaoExisteExcecao{
        Midia midiaSelecionada = getMidiaSelecionada();

        if (midiaSelecionada instanceof Filme) {
            abrirNoPainelDireito(new EditarMidiaFilme(midiaSelecionada, explorador));
        } else if (midiaSelecionada instanceof Musica) {
            abrirNoPainelDireito(new EditarMidiaMusica(midiaSelecionada, explorador));
        } else if (midiaSelecionada instanceof Livro) {
            abrirNoPainelDireito(new EditarMidiaLivro(midiaSelecionada, explorador));
        }
    }

    private Midia getMidiaSelecionada() {
        List<Midia> listaMidias = salvamento.getMidias();

        int viewIndex = tabelaMidias.getSelectedRow();
        if (viewIndex == -1) throw new ArquivoNaoExisteExcecao();

        int modelIndex = tabelaMidias.convertRowIndexToModel(viewIndex);

        Midia midiaSelecionada = listaMidias.get(modelIndex);
        return midiaSelecionada;
    }

    public void abrirNoPainelDireito(JPanel painel) {
        painelDireito.removeAll();
        painelDireito.setLayout(new BorderLayout());

        painelDireito.add(painel, BorderLayout.CENTER);

        painelDireito.revalidate();
        painelDireito.repaint();
    }

    public void limparPainelDireito() {
        painelDireito.removeAll();
        painelDireito.revalidate();
        painelDireito.repaint();
    }

}