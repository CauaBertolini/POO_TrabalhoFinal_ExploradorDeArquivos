package visao;

import controle.ExploradorDeArquivos;
import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.Listas;
import util.ComboUtil;

import javax.swing.*;

public class AdicionarMidiaLivro extends JPanel {

    private ExploradorDeArquivos explorador;
    private Listas listas;

    private JLabel jLabelTitulo;
    private JLabel jLabelCaminho;
    private JLabel jLabelNome;
    private JLabel jLabelTamanho;
    private JLabel jLabelPaginas;
    private JLabel jLabelAutor;
    private JLabel jLabelGenero;
    private JLabel jLabelTipoArquivo;

    private JTextField campoCaminho;
    private JTextField campoNome;
    private JTextField campoAutor;
    private JTextField campoTamanho;
    private JTextField campoPaginas;

    private JComboBox<Genero> comboBoxGenero;
    private JComboBox<ETipoArquivo> comboBoxTipoArquivo;

    private JButton botaoCadastrar;
    private JButton botaoCancelar;
    private JButton botaoProcurar;

    public AdicionarMidiaLivro(ExploradorDeArquivos explorador) {
        initComponents();
        this.explorador = explorador;
        this.listas = new Listas();

        ComboUtil.carregarGenerosComFiltro(comboBoxGenero, ETipoGenero.LITERARIO);
        ComboUtil.carregarTipoArquivoLivro(comboBoxTipoArquivo);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelTitulo = new JLabel();
        jLabelCaminho = new JLabel();
        jLabelNome = new JLabel();
        jLabelTamanho = new JLabel();
        jLabelPaginas = new JLabel();
        jLabelAutor = new JLabel();
        jLabelGenero = new JLabel();
        jLabelTipoArquivo = new JLabel();

        campoCaminho = new JTextField();
        campoNome = new JTextField();
        campoAutor = new JTextField();
        campoTamanho = new JTextField();
        campoPaginas = new JTextField();

        comboBoxGenero = new JComboBox<>();
        comboBoxTipoArquivo = new JComboBox<>();

        botaoCadastrar = new JButton();
        botaoCancelar = new JButton();
        botaoProcurar = new JButton();

        // --- Ajustes de Padrão ---
        setBackground(new java.awt.Color(247, 247, 255));
        setBorder(BorderFactory.createLineBorder(new java.awt.Color(220, 220, 255)));

        // Fonte do título padronizada
        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22));
        jLabelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelTitulo.setText("Cadastro de Livro");

        // --- Nomes dos Rótulos (Labels) ---
        jLabelCaminho.setText("Caminho do Arquivo:");
        jLabelNome.setText("Título do Livro:");
        jLabelTamanho.setText("Tamanho (MB):");
        jLabelPaginas.setText("Número de Páginas:");
        jLabelAutor.setText("Autor:");
        jLabelGenero.setText("Gênero Literário:");
        jLabelTipoArquivo.setText("Tipo de Arquivo:");

        botaoProcurar.setText("Procurar");
        // Fonte do botão "Procurar" reduzida
        botaoProcurar.setFont(botaoProcurar.getFont().deriveFont(11f));
        botaoProcurar.addActionListener(evt -> botaoProcurarAcao());

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(evt -> botaoCadastrarAcao());

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(evt -> botaoCancelarAcao());

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
                                        .addComponent(jLabelNome)
                                        .addComponent(jLabelAutor)
                                        .addComponent(jLabelTamanho)
                                        .addComponent(jLabelPaginas)
                                        .addComponent(jLabelGenero)
                                        .addComponent(jLabelTipoArquivo)
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
                                        .addComponent(campoNome, 200, 200, 270)
                                        .addComponent(campoAutor, 200, 200, 270)
                                        .addComponent(campoTamanho, 200, 200, 270)
                                        .addComponent(campoPaginas, 200, 200, 270)
                                        .addComponent(comboBoxGenero, 200, 200, 270)
                                        .addComponent(comboBoxTipoArquivo, 200, 200, 270)
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

                        // Linha 2: Nome (Título do Livro)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelNome)
                                .addComponent(campoNome, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(10)

                        // Linha 3: Autor
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelAutor)
                                .addComponent(campoAutor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(10)

                        // Linha 4: Tamanho
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelTamanho)
                                .addComponent(campoTamanho, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(10)

                        // Linha 5: Páginas
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelPaginas)
                                .addComponent(campoPaginas, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(10)

                        // Linha 6: Gênero
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelGenero)
                                .addComponent(comboBoxGenero, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                        .addGap(10)

                        // Linha 7: Tipo de Arquivo
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelTipoArquivo)
                                .addComponent(comboBoxTipoArquivo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                        .addGap(40) // Espaço antes dos botões

                        // Linha 8: Botões
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(botaoCancelar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoCadastrar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                        .addGap(20) // Espaço no final
        );
    }

    /**
     * Abre um seletor de diretório e, caso o usuário escolha uma pasta,
     * preenche o campo de caminho com o diretório selecionado.
     */
    private void botaoProcurarAcao() {
        String caminhoSelecionado = explorador.abrirSeletorDeDiretorio();
        if (caminhoSelecionado != null) {
            campoCaminho.setText(caminhoSelecionado);
        }
    }

    /**
     * Realiza a ação de cadastro de um livro.
     * <p>
     * Captura os valores preenchidos nos campos da interface, converte
     * os dados necessários e solicita à classe {@code ExploradorDeArquivos}
     * a criação de uma nova mídia do tipo Livro. Exibe mensagens de sucesso
     * ou erro conforme o resultado.
     * </p>
     */
    private void botaoCadastrarAcao() {
        try {
            String caminho = campoCaminho.getText();
            String nome = campoNome.getText();
            String autor = campoAutor.getText();
            float tamanho = Float.parseFloat(campoTamanho.getText());
            int paginas = Integer.parseInt(campoPaginas.getText());
            Genero genero = (Genero) comboBoxGenero.getSelectedItem();
            ETipoArquivo tipo = (ETipoArquivo) comboBoxTipoArquivo.getSelectedItem();

            explorador.criarNovaMidia(caminho, nome, tamanho, paginas, tipo, genero, autor, true);

            JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
            explorador.exploradorLimparPainelDireito();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar livro:\n" + e.getMessage());
        }
    }


    /**
     * Cancela a operação atual e limpa o painel direito da interface.
     */
    private void botaoCancelarAcao() {
        explorador.exploradorLimparPainelDireito();
    }

}