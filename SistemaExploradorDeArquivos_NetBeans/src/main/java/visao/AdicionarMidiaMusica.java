package visao;

import controle.ExploradorDeArquivos;
import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.Idioma;
import modelo.Listas;
import util.ComboUtil;
import util.JOptionPaneUtil;

import javax.swing.*;

public class AdicionarMidiaMusica extends JPanel {

    private ExploradorDeArquivos explorador;
    private Listas lista;
    private String caminho;

    private JLabel jLabelTitulo;
    private JLabel jLabelCaminho;
    private JLabel jLabelTituloMusica;
    private JLabel jLabelArtista;
    private JLabel jLabelTamanho;
    private JLabel jLabelDuracao;
    private JLabel jLabelGenero;
    private JLabel jLabelTipo;
    private JLabel jLabelIdioma;

    private JTextField campoCaminho;
    private JTextField campoTitulo;
    private JTextField campoArtista;
    private JTextField campoTamanho;
    private JTextField campoDuracao;

    private JComboBox<Genero> comboBoxGenero;
    private JComboBox<Idioma> comboBoxIdioma;
    private JComboBox<ETipoArquivo> comboBoxTipoArquivo;

    private JButton botaoProcurar;
    private JButton botaoCancelar;
    private JButton botaoCadastrar;

    public AdicionarMidiaMusica(ExploradorDeArquivos explorador) {
        initComponents();
        this.explorador = explorador;
        this.lista = new Listas();

        ComboUtil.carregarGenerosComFiltro(comboBoxGenero, ETipoGenero.MUSICAL);
        ComboUtil.carregarTipoArquivoMusica(comboBoxTipoArquivo);
        ComboUtil.carregarIdioma(comboBoxIdioma);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // --- Ajustes de Padrão ---
        setBackground(new java.awt.Color(247, 247, 255));
        setBorder(BorderFactory.createLineBorder(new java.awt.Color(220, 220, 255)));

        jLabelTitulo = new JLabel("Cadastro de Música");
        // Fonte do título padronizada
        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22));
        jLabelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // --- Nomes dos Rótulos (Labels) ---
        jLabelCaminho = new JLabel("Caminho do Arquivo:");
        jLabelTituloMusica = new JLabel("Título:");
        jLabelArtista = new JLabel("Artista:");
        jLabelTamanho = new JLabel("Tamanho (MB):");
        jLabelDuracao = new JLabel("Duração:");
        jLabelGenero = new JLabel("Gênero:");
        jLabelTipo = new JLabel("Tipo de Arquivo:");
        jLabelIdioma = new JLabel("Idioma:");

        campoCaminho = new JTextField();
        campoTitulo = new JTextField();
        campoArtista = new JTextField();
        campoTamanho = new JTextField();
        campoDuracao = new JTextField();

        comboBoxGenero = new JComboBox<>();
        comboBoxIdioma = new JComboBox<>();
        comboBoxTipoArquivo = new JComboBox<>();

        botaoProcurar = new JButton("Procurar");
        // Fonte do botão "Procurar" reduzida
        botaoProcurar.setFont(botaoProcurar.getFont().deriveFont(11f));
        botaoCancelar = new JButton("Cancelar");
        botaoCadastrar = new JButton("Cadastrar");

        botaoProcurar.addActionListener(evt -> botaoProcurarAcao());
        botaoCancelar.addActionListener(evt -> botaoCancelarAcao());
        botaoCadastrar.addActionListener(evt -> botaoCadastrarAcao());

        // -------------------- LAYOUT REFORMATADO --------------------
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // --- Layout Horizontal (Com centralização) ---
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        // Título centralizado
                        .addComponent(jLabelTitulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                        // Grupo do formulário (centralizado com "molas")
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola esquerda
                                // Coluna 1: Rótulos
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelCaminho)
                                        .addComponent(jLabelTituloMusica)
                                        .addComponent(jLabelArtista)
                                        .addComponent(jLabelTamanho)
                                        .addComponent(jLabelDuracao)
                                        .addComponent(jLabelGenero)
                                        .addComponent(jLabelTipo)
                                        .addComponent(jLabelIdioma)
                                )
                                .addGap(10) // Espaço entre colunas
                                // Coluna 2: Campos de Entrada
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        // Caso especial: Campo + Botão
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(campoCaminho, 180, 180, 180)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(botaoProcurar, 80, 80, 80)
                                        )
                                        .addComponent(campoTitulo, 200, 200, 270)
                                        .addComponent(campoArtista, 200, 200, 270)
                                        .addComponent(campoTamanho, 200, 200, 270)
                                        .addComponent(campoDuracao, 200, 200, 270)
                                        .addComponent(comboBoxGenero, 200, 200, 270)
                                        .addComponent(comboBoxTipoArquivo, 200, 200, 270)
                                        .addComponent(comboBoxIdioma, 200, 200, 270)
                                )
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola direita
                        )

                        // Grupo dos botões (centralizado com "molas")
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola esquerda
                                .addComponent(botaoCancelar, 110, 120, 150)
                                .addGap(8)
                                .addComponent(botaoCadastrar, 110, 120, 150)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola direita
                        )
        );

        // --- Layout Vertical (Linha por Linha) ---
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(jLabelTitulo)
                        .addGap(25)

                        // Linha 1: Caminho
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelCaminho)
                                .addComponent(campoCaminho, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoProcurar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(10)

                        // Linha 2: Título
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelTituloMusica)
                                .addComponent(campoTitulo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(10)

                        // Linha 3: Artista
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelArtista)
                                .addComponent(campoArtista, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(10)

                        // Linha 4: Tamanho
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelTamanho)
                                .addComponent(campoTamanho, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(10)

                        // Linha 5: Duração
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelDuracao)
                                .addComponent(campoDuracao, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(10)

                        // Linha 6: Gênero
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelGenero)
                                .addComponent(comboBoxGenero, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                        .addGap(10)

                        // Linha 7: Tipo de Arquivo
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelTipo)
                                .addComponent(comboBoxTipoArquivo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                        .addGap(10)

                        // Linha 8: Idioma
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelIdioma)
                                .addComponent(comboBoxIdioma, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                        .addGap(40) // Espaço antes dos botões

                        // Linha 9: Botões
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(botaoCancelar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoCadastrar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                        .addGap(20) // Espaço no final
        );
    }

    // -------------------------
    // MÉTODOS DE AÇÃO
    // -------------------------
    private void botaoProcurarAcao() {
        String selecionado = explorador.abrirSeletorDeDiretorio();
        if (selecionado != null) {
            caminho = selecionado;
            campoCaminho.setText(selecionado);
        }
    }

    private void botaoCadastrarAcao() {
        try {
            String titulo = campoTitulo.getText();
            String artista = campoArtista.getText();
            float tamanho = Float.parseFloat(campoTamanho.getText());
            double duracao = Double.parseDouble(campoDuracao.getText());
            Genero genero = (Genero) comboBoxGenero.getSelectedItem();
            ETipoArquivo tipo = (ETipoArquivo) comboBoxTipoArquivo.getSelectedItem();
            Idioma idioma = (Idioma) comboBoxIdioma.getSelectedItem();

            explorador.criarNovaMidia(caminho, titulo, tamanho, duracao, tipo, genero, artista, false);

            JOptionPaneUtil.mostrarMensagemSucesso("Música cadastrada com sucesso!");
            explorador.exploradorLimparPainelDireito();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar música:\n" + e.getMessage());
        }
    }

    private void botaoCancelarAcao() {
        explorador.exploradorLimparPainelDireito();
    }


}
