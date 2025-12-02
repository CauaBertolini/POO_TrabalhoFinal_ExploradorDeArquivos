package visao;

import controle.ExploradorDeArquivos;
import excecao.ArquivoNaoExisteExcecao;
import modelo.midias.*;
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

public class PaginaPrincipal extends javax.swing.JFrame {
    // Componentes de Filtro e Ordenação
    private javax.swing.JLabel labelFiltroGenero;
    private javax.swing.JLabel labelFiltroTipo;
    private javax.swing.JLabel labelFiltroInstancia;
    private javax.swing.JLabel labelOrdenar;
    private javax.swing.JComboBox<String> generoCombo;
    private javax.swing.JComboBox<String> tipoCombo;
    private javax.swing.JComboBox<String> instanciaCombo;
    private javax.swing.JComboBox<String> ordenarCombo;

    // Componentes de Ação e UI
    private javax.swing.JButton botaoAdicionarMidia;
    private javax.swing.JButton botaoAlterarMidia;
    private javax.swing.JButton botaoApagarMidia;
    private javax.swing.JButton botaoAtualizarTabela;
    private javax.swing.JButton botaoRenomear;
    private javax.swing.JButton botaoMover;
    private javax.swing.JButton botaoCarregarMidia;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaMidias;
    private javax.swing.JPanel painelDireito;

    private modelo.Listas listas;
    private Salvamento salvamento;
    private ExploradorDeArquivos explorador;

    public PaginaPrincipal() {
        this.explorador = new ExploradorDeArquivos(this);

        initComponents();
        this.salvamento = explorador.getSalvamento();
        this.listas = new Listas();

        configurarTabela();

        removerListenersFiltros();

        configurarFiltros();

        explorador.carregarMidiasDoCSV();

        adicionarListenersFiltros();

        filtrarTabela();

        setVisible(true);
    }

    public PaginaPrincipal(boolean inicializar) {
        if (!inicializar) return; // construtor vazio para testes

        // construtor real
        this.explorador = new ExploradorDeArquivos(this);

        initComponents();
        this.salvamento = explorador.getSalvamento();
        this.listas = new Listas();

        configurarTabela();
        removerListenersFiltros();
        configurarFiltros();

        explorador.carregarMidiasDoCSV();

        adicionarListenersFiltros();
        filtrarTabela();

        setVisible(true);
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        // Inicialização de componentes novos e existentes
        painelDireito = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMidias = new javax.swing.JTable();
        generoCombo = new javax.swing.JComboBox<>();
        tipoCombo = new javax.swing.JComboBox<>();
        instanciaCombo = new javax.swing.JComboBox<>();
        ordenarCombo = new javax.swing.JComboBox<>();

        labelFiltroGenero = new javax.swing.JLabel("Filtro Gênero");
        labelFiltroTipo = new javax.swing.JLabel("Filtro Tipo Arquivo");
        labelFiltroInstancia = new javax.swing.JLabel("Filtro por Instância");
        labelOrdenar = new javax.swing.JLabel("Ordenar");


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

        // --- PAINEL DE BOTÕES DE AÇÃO (6) ---
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


        // --- BOTÃO CARREGAR MÍDIA ---
        botaoCarregarMidia = new javax.swing.JButton("Carregar Mídia");
        botaoCarregarMidia.addActionListener(evt -> botaoCarregarMidiaAcao());

        JPanel painelCentralizado = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        painelCentralizado.setOpaque(false);
        painelCentralizado.add(botaoCarregarMidia);

        // --- PAINEL DE AÇÕES E FILTROS (GridBagLayout para organização vertical) ---
        JPanel painelAcoesFiltros = new JPanel(new GridBagLayout());
        painelAcoesFiltros.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);

        // 1. Adiciona Painel de 6 Botões
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        painelAcoesFiltros.add(painelBotoesAcao, gbc);

        // 2. Adiciona Botão Centralizado (Carregar Mídia)
        gbc.gridy = 1;
        gbc.insets = new Insets(8, 0, 15, 0);
        painelAcoesFiltros.add(painelCentralizado, gbc);

        // --- 3. RÓTULOS (Ordenar e Instância) ---
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);

        // Painel para Rótulos (alinhar à esquerda)
        JPanel pnlLabelsOrdemInstancia = new JPanel(new GridLayout(1, 2, 8, 8));
        pnlLabelsOrdemInstancia.setOpaque(false);

        // Alinhamento à esquerda para cada rótulo
        JPanel pnlLabelOrdenar = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlLabelOrdenar.setOpaque(false);
        pnlLabelOrdenar.add(labelOrdenar);

        JPanel pnlLabelInstancia = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlLabelInstancia.setOpaque(false);
        pnlLabelInstancia.add(labelFiltroInstancia);

        pnlLabelsOrdemInstancia.add(pnlLabelOrdenar);
        pnlLabelsOrdemInstancia.add(pnlLabelInstancia);

        painelAcoesFiltros.add(pnlLabelsOrdemInstancia, gbc);

        // 4. COMBOS (Ordenar e Instância)
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel pnlCombosOrdemInstancia = new JPanel(new GridLayout(1, 2, 8, 8));
        pnlCombosOrdemInstancia.setOpaque(false);
        pnlCombosOrdemInstancia.add(ordenarCombo);
        pnlCombosOrdemInstancia.add(instanciaCombo);

        painelAcoesFiltros.add(pnlCombosOrdemInstancia, gbc);

        // --- 5. RÓTULOS (Gênero e Tipo Arquivo) ---
        gbc.gridy = 4;

        // Painel para Rótulos (alinhar à esquerda)
        JPanel pnlLabelsTipoGenero = new JPanel(new GridLayout(1, 2, 8, 8));
        pnlLabelsTipoGenero.setOpaque(false);

        // Alinhamento à esquerda para cada rótulo
        JPanel pnlLabelGenero = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlLabelGenero.setOpaque(false);
        pnlLabelGenero.add(labelFiltroGenero);

        JPanel pnlLabelTipo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlLabelTipo.setOpaque(false);
        pnlLabelTipo.add(labelFiltroTipo);

        pnlLabelsTipoGenero.add(pnlLabelGenero);
        pnlLabelsTipoGenero.add(pnlLabelTipo);

        painelAcoesFiltros.add(pnlLabelsTipoGenero, gbc);

        // 6. COMBOS (Gênero e Tipo Arquivo)
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel pnlCombosTipoGenero = new JPanel(new GridLayout(1, 2, 8, 8));
        pnlCombosTipoGenero.setOpaque(false);
        pnlCombosTipoGenero.add(generoCombo);
        pnlCombosTipoGenero.add(tipoCombo);

        painelAcoesFiltros.add(pnlCombosTipoGenero, gbc);


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

    private void botaoAlterarAcao() {
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
                new String[]{"Nome", "Tamanho (MB)"}
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
        if (instanciaCombo.getActionListeners().length > 0) {
            for (ActionListener al : instanciaCombo.getActionListeners()) {
                instanciaCombo.removeActionListener(al);
            }
        }
        if (ordenarCombo.getActionListeners().length > 0) {
            for (ActionListener al : ordenarCombo.getActionListeners()) {
                ordenarCombo.removeActionListener(al);
            }
        }
    }

    private void configurarFiltros() {
        ComboUtil.carregarTiposArquivoParaFiltro(tipoCombo);
        ComboUtil.carregarGenerosParaFiltro(generoCombo, listas.getListaGeneros());
        ComboUtil.carregarInstanciasParaFiltro(instanciaCombo);
        ComboUtil.carregarOrdenacoesParaFiltro(ordenarCombo);
    }

    private void adicionarListenersFiltros() {
        tipoCombo.addActionListener(e -> filtrarTabela());
        generoCombo.addActionListener(e -> filtrarTabela());
        instanciaCombo.addActionListener(e -> filtrarTabela());
        ordenarCombo.addActionListener(e -> filtrarTabela());
    }

    public void atualizarTabela() {
        filtrarTabela();
    }

    private void filtrarTabela() {
        List<Midia> listaFiltrada = explorador.filtrarMidias(
                generoCombo.getSelectedItem().toString(),
                tipoCombo.getSelectedItem().toString(),
                instanciaCombo.getSelectedItem().toString()
        );

        explorador.ordenarMidias(listaFiltrada, ordenarCombo.getSelectedItem().toString());

        DefaultTableModel model = (DefaultTableModel) tabelaMidias.getModel();
        model.setRowCount(0);

        for (Midia midia : listaFiltrada) {
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

    private Midia getMidiaSelecionada() throws ArquivoNaoExisteExcecao {
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