package visao;

import controle.ExploradorDeArquivos;
import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.Listas;
import modelo.Midias.Midia;
import modelo.Salvamento;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class HomePage extends javax.swing.JFrame {
    private Listas lista;
    private ExploradorDeArquivos explorador;
    private Salvamento salvamento;

    public HomePage() {
        initComponents();
        this.salvamento = new Salvamento();
        this.explorador = new ExploradorDeArquivos(this, salvamento);
        this.lista = new Listas();

        configurarTabela();
        configurarFiltros();
        adicionarListenersFiltros();

        setVisible(true);
    }
    // metodo para iniciar no construtor a minha filtragem no painel das listas
    private void adicionarListenersFiltros() {
        tipoCombo.addActionListener(e -> filtrarTabela());
        generoCombo.addActionListener(e -> filtrarTabela());
    }

    // metodo responsavel pela filtragem o qual e chamado pelo metodo acima no listener
    private void filtrarTabela() {
        String tipoSelecionado = (String) tipoCombo.getSelectedItem();
        String generoSelecionado = (String) generoCombo.getSelectedItem();

        DefaultTableModel model = (DefaultTableModel) tabelaMidias.getModel();//pega o modelo da minha tabela ja modificado com os campos que eu quero
        model.setRowCount(0); // limpar tabela

        for (Midia m : salvamento.getMidias()) {//aqui eu tenho o loop responsavel por percorrer a minha lista do salvamento
            boolean passaFiltroTipo = tipoSelecionado.equals("Todos") ||
                    m.getTipoArquivo().toString().equals(tipoSelecionado);//verifica se o tipo selecionado no filtro for todos ou se o tipo selecionado no combo for igual ao tipo da midia que ele esta percorrendo

            boolean passaFiltroGenero = generoSelecionado.equals("Todos") ||//verifica se o genero selecionado no filtro for todos ou se o tipo selecionado no combo for igual ao tipo da midia que ele esta percorrendo
                    (m.getGenero() != null &&
                            m.getGenero().getNome().equals(generoSelecionado));

            if (passaFiltroTipo && passaFiltroGenero) {
                model.addRow(new Object[]{
                        m.getNome() + "." + m.getTipoArquivo().name(),
                        m.getTamanho()
                });
            }
        }
    }


    private void configurarTabela() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Caminho", "Tamanho (MB)"}
        );
        tabelaMidias.setModel(modelo);
    }

    public void atualizarTabela() {
        filtrarTabela();
    }

    private void configurarFiltros() {
        // Carregar tipos de arquivo do enum ETipoArquivo
        carregarTiposArquivo();

        // Carregar gêneros dinamicamente
        carregarGeneros();
    }

    private void carregarTiposArquivo() {
        // Obter todos os valores do enum ETipoArquivo
        ETipoArquivo[] tipos = ETipoArquivo.values();
        String[] opcoesTipos = new String[tipos.length + 1];
        opcoesTipos[0] = "Todos";

        for (int i = 0; i < tipos.length; i++) {
            opcoesTipos[i + 1] = tipos[i].toString();
        }

        tipoCombo.setModel(new DefaultComboBoxModel<>(opcoesTipos));
    }

    private void carregarGeneros() {
        // Primeiro item "Todos"
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Todos");

        // Usar um Set para evitar gêneros duplicados
        java.util.Set<String> generosUnicos = new java.util.HashSet<>();

        // Adicionar todos os gêneros únicos
        for (Genero genero : lista.getListaGeneros()) {
            generosUnicos.add(genero.getNome());
        }

        // Adicionar ao modelo
        for (String genero : generosUnicos) {
            model.addElement(genero);
        }

        generoCombo.setModel(model);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        btnAdicionar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
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
        painelEsquerdo.setPreferredSize(new Dimension(380, 600)); // FIXA A LARGURA

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
        painelEsquerdo.add(btnAlterar);

        btnListar.setText("Listar");
        btnListar.setBounds(200, 70, 150, 30);
        painelEsquerdo.add(btnListar);

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

    private void abrirSelecaoMidia() {
        painelDireito.removeAll();
        painelDireito.setLayout(new BorderLayout());

        painelDireito.add(
                new SelecioneQualMidiaAdicionar(painelDireito, explorador),
                BorderLayout.CENTER
        );

        painelDireito.revalidate();
        painelDireito.repaint();
    }

    // Variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnListar;
    private javax.swing.JComboBox<String> generoCombo;
    private javax.swing.JComboBox<String> tipoCombo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaMidias;
    private javax.swing.JPanel painelDireito;
}
