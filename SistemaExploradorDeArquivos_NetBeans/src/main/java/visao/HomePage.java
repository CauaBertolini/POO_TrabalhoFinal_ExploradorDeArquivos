package visao;

import controle.ExploradorDeArquivos;
import modelo.*;
import modelo.Midias.Filme;
import modelo.Midias.Musica;
import modelo.Midias.Livro;
import modelo.Midias.Midia;
import util.ComboUtil;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HomePage extends javax.swing.JFrame {
    private Listas lista;
    private Salvamento salvamento;
    private ExploradorDeArquivos explorador;

    public HomePage() {
        initComponents();
        this.explorador = new ExploradorDeArquivos(this);
        this.salvamento = explorador.getSalvamento();
        this.lista = new Listas();

        configurarTabela();

// 1. Remove listeners temporariamente
        removerListenersFiltros();

// 2. Carrega os combos
        configurarFiltros();

// 3. Recoloca listeners
        adicionarListenersFiltros();

// 4. Agora sim pode filtrar
        filtrarTabela();

        setVisible(true);

    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        btnAdicionar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        generoCombo = new javax.swing.JComboBox<>();
        tipoCombo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMidias = new javax.swing.JTable();
        painelDireito = new javax.swing.JPanel();

        setTitle("Sistema de Gerenciamento de Mídias");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // PAINEL ESQUERDO
        javax.swing.JPanel painelEsquerdo = new javax.swing.JPanel();
        painelEsquerdo.setBackground(new java.awt.Color(240, 240, 240));
        painelEsquerdo.setLayout(null);
        painelEsquerdo.setPreferredSize(new Dimension(500, 600)); // FIXA A LARGURA

        // Botões
        btnAdicionar.setText("Adicionar Mídia +");
        btnAdicionar.setBounds(30, 30, 150, 30);
        btnAdicionar.addActionListener(evt -> abrirSelecaoMidia());
        painelEsquerdo.add(btnAdicionar);

        btnDeletar.setText("Deletar Mídia -");
        btnDeletar.setBounds(200, 30, 150, 30);
        painelEsquerdo.add(btnDeletar);

        btnAlterar.setText("Alterar Mídia");
        btnAlterar.setBounds(30, 70, 150, 30);
        btnAlterar.addActionListener(evt -> abrirAlterarMidia());
        painelEsquerdo.add(btnAlterar);

        btnAtualizar.setText("Atualizar");
        btnAtualizar.setBounds(200, 70, 150, 30);
        btnAtualizar.addActionListener(atualizarTabelaListener());
        painelEsquerdo.add(btnAtualizar);

        // Filtros
        generoCombo.setBounds(30, 130, 150, 25);
        tipoCombo.setBounds(200, 130, 150, 25);
        painelEsquerdo.add(generoCombo);
        painelEsquerdo.add(tipoCombo);

        // Tabela
        jScrollPane1.setViewportView(tabelaMidias);
        jScrollPane1.setBounds(30, 170, 320, 330);
        painelEsquerdo.add(jScrollPane1);

        add(painelEsquerdo, BorderLayout.WEST);

        // PAINEL DIREITO (DESTINO DOS FORMULÁRIOS)
        painelDireito.setBackground(new java.awt.Color(200, 200, 200));
        painelDireito.setLayout(new BorderLayout());
        add(painelDireito, BorderLayout.CENTER);
    }

    private void configurarTabela() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Caminho", "Tamanho (MB)"}
        );
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

    private ActionListener atualizarTabelaListener() {
        return e -> filtrarTabela();
    }

    // metodo responsavel pela filtragem o qual e chamado pelo metodo acima no listener
    private void filtrarTabela() {
        // Filtra usando o explorador
        List<modelo.Midias.Midia> listaFiltrada = explorador.filtrarMidias(
                generoCombo.getSelectedItem().toString(),
                tipoCombo.getSelectedItem().toString()
        );

        // Modelo da JTable
        DefaultTableModel model = (DefaultTableModel) tabelaMidias.getModel();
        model.setRowCount(0); // limpa a tabela

        // Preenche a tabela
        for (modelo.Midias.Midia midia : listaFiltrada) {
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

    public void abrirAlterarMidia() {
        List<Midia> listaMidias = salvamento.getMidias();

        int viewIndex = tabelaMidias.getSelectedRow();
        if (viewIndex == -1) return;

        int modelIndex = tabelaMidias.convertRowIndexToModel(viewIndex);

        Midia midiaSelecionada = listaMidias.get(modelIndex);

        if (midiaSelecionada instanceof Filme) {
            abrirNoPainelDireito(new EditarMidiaFilme(midiaSelecionada));
        } else if (midiaSelecionada instanceof Musica) {

        } else if (midiaSelecionada instanceof Livro) {

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
        painelDireito.setLayout(new BorderLayout());
        painelDireito.revalidate();
        painelDireito.repaint();
    }

    // Variáveis
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JComboBox<String> generoCombo;
    private javax.swing.JComboBox<String> tipoCombo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaMidias;
    private javax.swing.JPanel painelDireito;
}
