package visao;

import controle.ExploradorDeArquivos;
import modelo.Salvamento;
import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import javax.swing.*;
import modelo.Genero;
import modelo.Listas;
import modelo.Midias.Livro;
import util.ComboUtil;

public class AdicionarMidiaLivro extends JPanel {

    private ExploradorDeArquivos explorador;
    private Listas listas;

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

        setBackground(new java.awt.Color(230, 230, 230));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20));
        jLabelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelTitulo.setText("Cadastro de Livro");

        jLabelCaminho.setText("Caminho do Arquivo:");
        jLabelNome.setText("Título do Livro:");
        jLabelTamanho.setText("Tamanho (MB):");
        jLabelPaginas.setText("Número de Páginas:");
        jLabelAutor.setText("Autor:");
        jLabelGenero.setText("Gênero Literário:");
        jLabelTipoArquivo.setText("Tipo de Arquivo:");

        botaoProcurar.setText("Procurar");
        //botao procurar que serve para preencher o campo do caminho
        botaoProcurar.addActionListener(evt -> {
            String caminho = explorador.abrirSeletorDeDiretorio();
            if (caminho != null) {campoCaminho.setText(caminho);}
        });

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(evt -> cadastrarLivro());

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(evt -> limparCampos());


        // -------------------- GROUPLAYOUT COMPLETO --------------------
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelTitulo, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)

                                        .addComponent(jLabelCaminho)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(campoCaminho)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(botaoProcurar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        )

                                        .addComponent(jLabelNome)
                                        .addComponent(campoNome)

                                        .addComponent(jLabelAutor)
                                        .addComponent(campoAutor)

                                        .addComponent(jLabelTamanho)
                                        .addComponent(campoTamanho)

                                        .addComponent(jLabelPaginas)
                                        .addComponent(campoPaginas)

                                        .addComponent(jLabelGenero)
                                        .addComponent(comboBoxGenero)

                                        .addComponent(jLabelTipoArquivo)
                                        .addComponent(comboBoxTipoArquivo)

                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(botaoCancelar, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(botaoCadastrar, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                        )
                                )
                                .addGap(30, 30, 30)
                        )
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabelTitulo)
                                .addGap(20, 20, 20)

                                .addComponent(jLabelCaminho)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(campoCaminho, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botaoProcurar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGap(15, 15, 15)

                                .addComponent(jLabelNome)
                                .addComponent(campoNome, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)

                                .addComponent(jLabelAutor)
                                .addComponent(campoAutor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)

                                .addComponent(jLabelTamanho)
                                .addComponent(campoTamanho, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)

                                .addComponent(jLabelPaginas)
                                .addComponent(campoPaginas, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)

                                .addComponent(jLabelGenero)
                                .addComponent(comboBoxGenero, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)

                                .addComponent(jLabelTipoArquivo)
                                .addComponent(comboBoxTipoArquivo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)

                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(botaoCancelar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botaoCadastrar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                )

                                .addContainerGap(40, Short.MAX_VALUE)
                        )
        );
    }


    private void cadastrarLivro() {
        try {
            String caminho = campoCaminho.getText();
            String nome = campoNome.getText();
            String autor = campoAutor.getText();
            float tamanho = Float.parseFloat(campoTamanho.getText());
            int paginas = Integer.parseInt(campoPaginas.getText());
            Genero genero = (Genero) comboBoxGenero.getSelectedItem();
            ETipoArquivo tipo = (ETipoArquivo) comboBoxTipoArquivo.getSelectedItem();

            explorador.criarNovaMidia(caminho, nome, tamanho, paginas,tipo, genero, autor, true);


            JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");

            limparCampos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar livro:\n" + e.getMessage());
        }
    }

    private void limparCampos() {
        campoCaminho.setText("");
        campoNome.setText("");
        campoAutor.setText("");
        campoTamanho.setText("");
        campoPaginas.setText("");
    }

    // Componentes (iguais ao anterior)
    private JButton botaoCadastrar;
    private JButton botaoCancelar;
    private JButton botaoProcurar;

    private JTextField campoCaminho;
    private JTextField campoNome;
    private JTextField campoAutor;
    private JTextField campoTamanho;
    private JTextField campoPaginas;

    private JComboBox<Genero> comboBoxGenero;
    private JComboBox<ETipoArquivo> comboBoxTipoArquivo;

    private JLabel jLabelTitulo;
    private JLabel jLabelCaminho;
    private JLabel jLabelNome;
    private JLabel jLabelTamanho;
    private JLabel jLabelPaginas;
    private JLabel jLabelAutor;
    private JLabel jLabelGenero;
    private JLabel jLabelTipoArquivo;
}
