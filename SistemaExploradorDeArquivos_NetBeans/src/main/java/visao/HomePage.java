package visao;

import controle.ExploradorDeArquivos;
import excecao.ArquivoNaoExisteExcecao;
import modelo.*;
import modelo.midias.Filme;
import modelo.midias.Musica;
import modelo.midias.Livro;
import modelo.midias.*;
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
    private javax.swing.JLabel labelFiltroInstancia;
    private javax.swing.JLabel labelOrdenar;
    private javax.swing.JButton botaoAdicionarMidia;
    private javax.swing.JButton botaoAlterarMidia;
    private javax.swing.JButton botaoApagarMidia;
    private javax.swing.JButton botaoAtualizarTabela;
    private javax.swing.JButton botaoRenomear;
    private javax.swing.JButton botaoMover;
    private javax.swing.JComboBox<String> generoCombo;
    private javax.swing.JComboBox<String> tipoCombo;
    private javax.swing.JComboBox<String> instanciaCombo;
    private javax.swing.JComboBox<String> ordenarCombo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaMidias;
    private javax.swing.JPanel painelDireito;

    private Listas lista;
    private Salvamento salvamento;
    private ExploradorDeArquivos explorador;

    public HomePage() {
        initComponents();
        this.explorador = new ExploradorDeArquivos(this);
        this.salvamento = explorador.getSalvamento();
        this.lista = new Listas();

        configurarTabela();

        removerListenersFiltros();

        configurarFiltros();

        adicionarListenersFiltros();

        filtrarTabela();

        setVisible(true);

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

        JPanel painelSuperior = new JPanel(new GridLayout(5, 2, 8, 8));
        painelSuperior.setOpaque(false);

        botaoAdicionarMidia = new javax.swing.JButton("Adicionar Mídia +");
        botaoAdicionarMidia.addActionListener(evt -> abrirSelecaoMidia());
        painelSuperior.add(botaoAdicionarMidia);

        botaoApagarMidia = new javax.swing.JButton("Apagar Mídia -");
        botaoApagarMidia.addActionListener(evt -> botaoDeletarMidiaAcao());
        painelSuperior.add(botaoApagarMidia);

        botaoAlterarMidia = new javax.swing.JButton("Alterar Mídia");
        botaoAlterarMidia.addActionListener(evt -> botaoAlterarAcao());
        painelSuperior.add(botaoAlterarMidia);

        botaoMover = new javax.swing.JButton("Mover Mídia");
        botaoMover.addActionListener(evt -> botaoMoverAcao());
        painelSuperior.add(botaoMover);

        botaoRenomear = new javax.swing.JButton("Renomear Mídia");
        botaoRenomear.addActionListener(evt -> botaoRenomearAcao());
        painelSuperior.add(botaoRenomear);

        botaoAtualizarTabela = new javax.swing.JButton("Atualizar Lista");
        botaoAtualizarTabela.addActionListener(evt -> botaoAtualizarTabelaAcao());
        painelSuperior.add(botaoAtualizarTabela);

        labelFiltroGenero = new javax.swing.JLabel("Filtro Gênero");
        labelFiltroTipo = new javax.swing.JLabel("Filtro Tipo Arquivo");
        labelFiltroInstancia = new javax.swing.JLabel("Filtro por Instância");
        labelOrdenar = new javax.swing.JLabel("Ordenar");

        labelFiltroGenero.setVerticalAlignment(SwingConstants.BOTTOM);
        labelFiltroTipo.setVerticalAlignment(SwingConstants.BOTTOM);
        labelFiltroInstancia.setVerticalAlignment(SwingConstants.BOTTOM);
        labelOrdenar.setVerticalAlignment(SwingConstants.BOTTOM);

        painelSuperior.add(labelOrdenar);
        painelSuperior.add(labelFiltroInstancia);
        painelSuperior.add(labelFiltroGenero);
        painelSuperior.add(labelFiltroTipo);


        instanciaCombo = new javax.swing.JComboBox<>();
        generoCombo = new javax.swing.JComboBox<>();
        tipoCombo = new javax.swing.JComboBox<>();
        ordenarCombo = new javax.swing.JComboBox<>();

        painelSuperior.add(generoCombo);
        painelSuperior.add(tipoCombo);
        painelSuperior.add(instanciaCombo);
        painelSuperior.add(ordenarCombo);

        painelEsquerdo.add(painelSuperior, BorderLayout.NORTH);

        jScrollPane1.setViewportView(tabelaMidias);
        painelEsquerdo.add(jScrollPane1, BorderLayout.CENTER);

        add(painelEsquerdo, BorderLayout.WEST);

        painelDireito.setBackground(new java.awt.Color(247, 247, 255));
        painelDireito.setLayout(new BorderLayout());
        add(painelDireito, BorderLayout.CENTER);
    }

    private void botaoDeletarMidiaAcao() {
        try {
            List<Midia> listaMidias = salvamento.getMidias();

            int viewIndex = tabelaMidias.getSelectedRow();
            if (viewIndex == -1) throw new ArquivoNaoExisteExcecao();

            int modelIndex = tabelaMidias.convertRowIndexToModel(viewIndex);

            Midia midiaSelecionada = listaMidias.get(modelIndex);

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
            List<Midia> listaMidias = salvamento.getMidias();

            int viewIndex = tabelaMidias.getSelectedRow();
            if (viewIndex == -1) throw new ArquivoNaoExisteExcecao();

            int modelIndex = tabelaMidias.convertRowIndexToModel(viewIndex);

            Midia midiaSelecionada = listaMidias.get(modelIndex);

            abrirNoPainelDireito(new RenomearMidia(explorador, midiaSelecionada));

        } catch (ArquivoNaoExisteExcecao excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Selecione um item da tabela para excluir!");
        }

    }

    private void botaoMoverAcao() {
        try {
            List<Midia> listaMidias = salvamento.getMidias();

            int viewIndex = tabelaMidias.getSelectedRow();
            if (viewIndex == -1) throw new ArquivoNaoExisteExcecao();

            int modelIndex = tabelaMidias.convertRowIndexToModel(viewIndex);

            Midia midiaSelecionada = listaMidias.get(modelIndex);

            abrirNoPainelDireito(new MoverMidia(explorador, midiaSelecionada));

        } catch (ArquivoNaoExisteExcecao excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Selecione um item da tabela para excluir!");
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
        ComboUtil.carregarInstanciasParaFiltro(instanciaCombo);
        ComboUtil.carregarOrdenacoes(ordenarCombo);
    }

    private void adicionarListenersFiltros() {
        tipoCombo.addActionListener(e -> filtrarTabela());
        generoCombo.addActionListener(e -> filtrarTabela());
        instanciaCombo.addActionListener(e -> filtrarTabela());
        ordenarCombo.addActionListener(evt-> filtrarTabela());// NOVO!
    }

    public void atualizarTabela() {
        filtrarTabela();
    }

    private void filtrarTabela() {
        List<Midia> listaFiltrada = explorador.filtrarMidias(
                generoCombo.getSelectedItem().toString(),
                tipoCombo.getSelectedItem().toString(),
                instanciaCombo.getSelectedItem().toString() // NOVO!
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
        List<Midia> listaMidias = salvamento.getMidias();

        int viewIndex = tabelaMidias.getSelectedRow();
        if (viewIndex == -1) throw new ArquivoNaoExisteExcecao();

        int modelIndex = tabelaMidias.convertRowIndexToModel(viewIndex);

        Midia midiaSelecionada = listaMidias.get(modelIndex);

        if (midiaSelecionada instanceof Filme) {
            abrirNoPainelDireito(new EditarMidiaFilme(midiaSelecionada, explorador));
        } else if (midiaSelecionada instanceof Musica) {
            abrirNoPainelDireito(new EditarMidiaMusica(midiaSelecionada, explorador));
        } else if (midiaSelecionada instanceof Livro) {
            abrirNoPainelDireito(new EditarMidiaLivro(midiaSelecionada, explorador));
        }
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