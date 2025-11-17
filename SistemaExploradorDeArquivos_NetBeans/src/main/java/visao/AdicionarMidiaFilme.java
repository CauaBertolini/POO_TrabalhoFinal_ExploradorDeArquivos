package visao;

import controle.ExploradorDeArquivos;
import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.Idioma;
import modelo.Listas;

/**
 *
 * @author ISAC
 */
public class AdicionarMidiaFilme extends javax.swing.JPanel {

    private ExploradorDeArquivos explorador;
    private Listas lista;
    private String caminho;

    /**
     * Creates new form AdicionarMidiaFilme
     */
    public AdicionarMidiaFilme(ExploradorDeArquivos explorador) {
        initComponents();
        this.explorador = explorador;

        lista = new Listas();

        carregarGenero();
        carregarIdioma();
        carregarTipoArquivo();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        botaoCadastrar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        comboBoxGenero = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        duracaoFilme = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tamanhoArquivo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tituloFilme = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        caminhoArquivo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comboBoxIdioma = new javax.swing.JComboBox<>();
        comboBoxTipoArquivo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        procurarArquivo = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro de Filme");

        jLabel2.setText("Caminho do Arquivo");
        jLabel3.setText("Título");
        jLabel4.setText("Tamanho do Arquivo (MB)");
        jLabel5.setText("Duração do Filme (min)");
        jLabel7.setText("Gênero:");
        jLabel8.setText("Idioma:");
        jLabel9.setText("Tipo do Arquivo:");

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(evt -> botaoCadastrarActionPerformed(evt));

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(evt -> botaoCancelarActionPerformed(evt));

        comboBoxGenero.setModel(new javax.swing.DefaultComboBoxModel<>());
        comboBoxIdioma.setModel(new javax.swing.DefaultComboBoxModel<>());
        comboBoxTipoArquivo.setModel(new javax.swing.DefaultComboBoxModel<>());

        procurarArquivo.setText("Procurar");
        procurarArquivo.addActionListener(evt -> procurarArquivoActionPerformed(evt));

        // LAYOUT AUTOMÁTICO (igual ao NetBeans)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                                        .addComponent(jLabel2)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(caminhoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(procurarArquivo))

                                        .addComponent(jLabel3)
                                        .addComponent(tituloFilme)
                                        .addComponent(jLabel4)
                                        .addComponent(tamanhoArquivo)
                                        .addComponent(jLabel5)
                                        .addComponent(duracaoFilme)

                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(comboBoxTipoArquivo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(comboBoxGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(comboBoxIdioma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(botaoCancelar)
                                                .addGap(18, 18, 18)
                                                .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))

                                )
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()

                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)

                                .addGap(20, 20, 20)
                                .addComponent(jLabel2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(caminhoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(procurarArquivo))

                                .addGap(10, 10, 10)
                                .addComponent(jLabel3)
                                .addComponent(tituloFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)

                                .addGap(10, 10, 10)
                                .addComponent(jLabel4)
                                .addComponent(tamanhoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)

                                .addGap(10, 10, 10)
                                .addComponent(jLabel5)
                                .addComponent(duracaoFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)

                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(comboBoxTipoArquivo))

                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(comboBoxGenero))

                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(comboBoxIdioma))

                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))

                                .addGap(20, 20, 20))
        );
    }

    // -------------------------
    // BOTÃO CADASTRAR
    // -------------------------
    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {

        if (caminho == null || caminho.isEmpty()) {
            System.out.println("ERRO: Caminho não selecionado!");
            return;
        }

        String titulo = tituloFilme.getText();
        float tamanhoMB = Float.parseFloat(tamanhoArquivo.getText());
        double duracao = Double.parseDouble(duracaoFilme.getText());
        Genero genero = (Genero) comboBoxGenero.getSelectedItem();
        Idioma idioma = (Idioma) comboBoxIdioma.getSelectedItem();
        ETipoArquivo tipoArquivo = (ETipoArquivo) comboBoxTipoArquivo.getSelectedItem();

        explorador.criarNovaMidia(caminho, titulo, tamanhoMB, duracao, tipoArquivo, genero, idioma);
    }

    // -------------------------
    // BOTÃO CANCELAR
    // -------------------------
    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        caminhoArquivo.setText("");
        tituloFilme.setText("");
        tamanhoArquivo.setText("");
        duracaoFilme.setText("");
        comboBoxGenero.setSelectedItem(null);
    }

    // -------------------------
    // BOTÃO PROCURAR
    // -------------------------
    private void procurarArquivoActionPerformed(java.awt.event.ActionEvent evt) {

        String selecionado = explorador.abrirSeletorDeDiretorio();

        if (selecionado != null) {
            caminho = selecionado;
            caminhoArquivo.setText(selecionado);
        }
    }

    // -------------------------
    // CARREGAMENTO DE COMBOS
    // -------------------------
    public void carregarGenero() {
        comboBoxGenero.removeAllItems();

        for (Genero g : lista.getListaGeneros()) {
            if (g.getETipoGenero() == ETipoGenero.CINEMA) {
                comboBoxGenero.addItem(g);
            }
        }
    }

    public void carregarIdioma() {
        comboBoxIdioma.removeAllItems();

        for (Idioma idioma : lista.getListaIdiomas()) {
            comboBoxIdioma.addItem(idioma);
        }
    }

    public void carregarTipoArquivo() {
        comboBoxTipoArquivo.removeAllItems();
        comboBoxTipoArquivo.addItem(ETipoArquivo.MP4);
        comboBoxTipoArquivo.addItem(ETipoArquivo.MKV);
    }

    // Variables declaration
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JTextField caminhoArquivo;
    private javax.swing.JComboBox<Genero> comboBoxGenero;
    private javax.swing.JComboBox<Idioma> comboBoxIdioma;
    private javax.swing.JComboBox<ETipoArquivo> comboBoxTipoArquivo;
    private javax.swing.JTextField duracaoFilme;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JToggleButton procurarArquivo;
    private javax.swing.JTextField tamanhoArquivo;
    private javax.swing.JTextField tituloFilme;
}
