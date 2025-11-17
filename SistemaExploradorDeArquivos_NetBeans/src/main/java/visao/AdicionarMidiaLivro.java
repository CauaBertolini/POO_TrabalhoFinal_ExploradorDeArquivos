package visao;

import controle.ExploradorDeArquivos;
import modelo.Salvamento;
import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import javax.swing.*;
import modelo.Genero;
import modelo.Listas;
import modelo.Midias.Livro;

public class AdicionarMidiaLivro extends JPanel {

    private ExploradorDeArquivos explorador;
    private Salvamento salvamento;
    private Listas listas;

    public AdicionarMidiaLivro(ExploradorDeArquivos explorador, Salvamento salvamento) {
        this.explorador = explorador;
        this.salvamento = salvamento;
        this.listas = new Listas();
        initComponents();
        carregarComboBoxes();
    }

    private void carregarComboBoxes() {

        // Tipos de arquivo
        comboBoxTipoArquivo.removeAllItems();
        comboBoxTipoArquivo.addItem(ETipoArquivo.PDF);
        comboBoxTipoArquivo.addItem(ETipoArquivo.EPUB);

        // Gêneros literários
        comboBoxGenero.removeAllItems();
        for (Genero g : listas.getListaGeneros()) {
            if (g.getETipoGenero() == ETipoGenero.LITERARIO) {
                comboBoxGenero.addItem(g);
            }
        }
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

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18));
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
        botaoProcurar.addActionListener(evt -> {
            String caminho = explorador.abrirSeletorDeDiretorio();
            if (caminho != null) campoCaminho.setText(caminho);
        });

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(evt -> cadastrarLivro());

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(evt -> limparCampos());

        // ------ Layout cortado para manter o foco (você já tem ele funcionando) ------
        // (Se quiser, envio novamente o layout completo)

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        setLayout(layout);

        // ... (mantém exatamente o layout do painel anterior sem alterar nada)
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

            modelo.Midias.Midia livro = new Livro(
                    caminho,
                    nome,
                    tamanho,
                    paginas,
                    tipo,
                    genero,
                    autor
            );

            // ⭐ Adiciona AGORA no lugar certo ⭐
            salvamento.incluirMidia(livro);

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
