package visao;

import controle.ExploradorDeArquivos;
import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.Idioma;
import modelo.Listas;

public class AdicionarMidiaMusica extends javax.swing.JPanel {

    private ExploradorDeArquivos explorador;
    private Listas lista;
    private String caminho;

    public AdicionarMidiaMusica(ExploradorDeArquivos explorador) {
        initComponents();
        this.explorador = explorador ;
        lista = new Listas();
        carregarGenero();
        carregarIdioma();
        carregarTipoArquivo();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        setBackground(new java.awt.Color(204, 204, 204));

        jLabelTitulo = new javax.swing.JLabel("Cadastro de Musica");
        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelCaminho = new javax.swing.JLabel("Caminho do Arquivo");
        jLabelTituloMusica = new javax.swing.JLabel("Título");
        jLabelArtista = new javax.swing.JLabel("Artista");
        jLabelTamanho = new javax.swing.JLabel("Tamanho arquivo (MB)");
        jLabelDuracao = new javax.swing.JLabel("Duração da Música");
        jLabelGenero = new javax.swing.JLabel("Gênero");
        jLabelTipo = new javax.swing.JLabel("Tipo Arquivo");
        jLabelIdioma = new javax.swing.JLabel("Idioma");

        campoCaminho = new javax.swing.JTextField();
        campoTitulo = new javax.swing.JTextField();
        campoArtista = new javax.swing.JTextField();
        campoTamanho = new javax.swing.JTextField();
        campoDuracao = new javax.swing.JTextField();

        comboGenero = new javax.swing.JComboBox<>();
        comboIdioma = new javax.swing.JComboBox<>();
        comboTipo = new javax.swing.JComboBox<>();

        botaoProcurar = new javax.swing.JButton("Procurar");
        botaoCancelar = new javax.swing.JButton("Cancelar");
        botaoCadastrar = new javax.swing.JButton("Cadastrar");


        botaoProcurar.addActionListener((evt) -> {
            String selecionado = explorador.abrirSeletorDeDiretorio();

            if (selecionado != null) {
                caminho = selecionado;
                campoCaminho.setText(selecionado);
            }
        });


        botaoCancelar.addActionListener((evt) -> {
            campoCaminho.setText("");
            campoTitulo.setText("");
            campoArtista.setText("");
            campoTamanho.setText("");
            campoDuracao.setText("");
        });

        botaoCadastrar.addActionListener((evt) -> {

            String titulo = campoTitulo.getText();
            String artista = campoArtista.getText();

            float tamanho = Float.parseFloat(campoTamanho.getText());
            double duracao = Double.parseDouble(campoDuracao.getText());
            Genero genero = (Genero) comboGenero.getSelectedItem();
            Idioma idioma = (Idioma) comboIdioma.getSelectedItem();
            ETipoArquivo tipo = (ETipoArquivo) comboTipo.getSelectedItem();

            explorador.criarNovaMidia(caminho, titulo, tamanho, duracao,tipo, genero, artista, false);
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelCaminho)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(campoCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(botaoProcurar))
                                        .addComponent(jLabelTituloMusica)
                                        .addComponent(campoTitulo)
                                        .addComponent(jLabelArtista)
                                        .addComponent(campoArtista)
                                        .addComponent(jLabelTamanho)
                                        .addComponent(campoTamanho)
                                        .addComponent(jLabelDuracao)
                                        .addComponent(campoDuracao)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelGenero)
                                                .addGap(18, 18, 18)
                                                .addComponent(comboGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelTipo)
                                                .addGap(18, 18, 18)
                                                .addComponent(comboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelIdioma)
                                                .addGap(18, 18, 18)
                                                .addComponent(comboIdioma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(botaoCancelar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15)
                                .addComponent(jLabelTitulo)
                                .addGap(20)
                                .addComponent(jLabelCaminho)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(campoCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botaoProcurar))
                                .addGap(10)
                                .addComponent(jLabelTituloMusica)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoTitulo)
                                .addGap(10)
                                .addComponent(jLabelArtista)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoArtista)
                                .addGap(10)
                                .addComponent(jLabelTamanho)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoTamanho)
                                .addGap(10)
                                .addComponent(jLabelDuracao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoDuracao)
                                .addGap(10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelGenero)
                                        .addComponent(comboGenero))
                                .addGap(10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelTipo)
                                        .addComponent(comboTipo))
                                .addGap(10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelIdioma)
                                        .addComponent(comboIdioma))
                                .addGap(20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
    }

    public void carregarGenero() {
        comboGenero.removeAllItems();
        for (Genero g : lista.getListaGeneros()) {
            if (g.getETipoGenero() == ETipoGenero.MUSICAL) {
                comboGenero.addItem(g);
            }
        }
    }

    public void carregarIdioma() {
        comboIdioma.removeAllItems();
        for (Idioma i : lista.getListaIdiomas()) {
            comboIdioma.addItem(i);
        }
    }

    public void carregarTipoArquivo() {
        comboTipo.removeAllItems();
        comboTipo.addItem(ETipoArquivo.MP3);
    }

    // Variables
    private javax.swing.JLabel jLabelTitulo, jLabelCaminho, jLabelTituloMusica, jLabelArtista, jLabelTamanho, jLabelDuracao, jLabelGenero, jLabelTipo, jLabelIdioma;
    private javax.swing.JTextField campoCaminho, campoTitulo, campoArtista, campoTamanho, campoDuracao;
    private javax.swing.JComboBox<Genero> comboGenero;
    private javax.swing.JComboBox<Idioma> comboIdioma;
    private javax.swing.JComboBox<ETipoArquivo> comboTipo;
    private javax.swing.JButton botaoProcurar, botaoCancelar, botaoCadastrar;
}
