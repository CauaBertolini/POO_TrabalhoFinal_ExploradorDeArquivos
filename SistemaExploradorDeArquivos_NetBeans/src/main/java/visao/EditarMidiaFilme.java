package visao;

import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.Idioma;
import util.ComboUtil;

import javax.swing.*;

public class EditarMidiaFilme extends JPanel {

    private JLabel lblTitulo;
    private JLabel lblTamanho;
    private JLabel lblDuracao;
    private JLabel lblIdioma;
    private JLabel lblTipoArquivo;
    private JLabel lblGenero;

    private JTextField txtTamanho;
    private JTextField txtDuracao;


    private JComboBox<Genero> comboBoxGenero;
    private JComboBox<Idioma> comboBoxIdioma;
    private JComboBox<ETipoArquivo> comboBoxTipoArquivo;

    private JButton btnCancelar;
    private JButton btnConfirmar;

    private modelo.Midias.Filme filme;

    public EditarMidiaFilme(modelo.Midias.Midia midiaSelecionada) {
        initComponents();
        this.filme = (modelo.Midias.Filme) midiaSelecionada;

        ComboUtil.carregarTipoArquivoFilme(comboBoxTipoArquivo);
        ComboUtil.carregarGenerosComFiltro(comboBoxGenero, ETipoGenero.CINEMA);
        ComboUtil.carregarIdioma(comboBoxIdioma);
    }

    private void initComponents() {

        lblTitulo = new JLabel("Alterando Filme");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblTamanho = new JLabel("Tamanho do Arquivo (MB)");
        txtTamanho = new JTextField();

        lblDuracao = new JLabel("Duração do filme");
        txtDuracao = new JTextField();

        lblIdioma = new JLabel("Idioma");
        comboBoxIdioma = new JComboBox<>();

        lblTipoArquivo = new JLabel("Tipo de Arquivo");
        comboBoxTipoArquivo = new JComboBox<>();

        lblGenero = new JLabel("Gênero");
        comboBoxGenero = new JComboBox<>();

        btnCancelar = new JButton("Cancelar");
        btnConfirmar = new JButton("Confirmar");

        setBackground(new java.awt.Color(247, 247, 255));
        setBorder(BorderFactory.createLineBorder(new java.awt.Color(220, 220, 255)));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lblTitulo)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTamanho)
                                        .addComponent(txtTamanho, 200, 200, 200)
                                        .addComponent(lblDuracao)
                                        .addComponent(txtDuracao, 200, 200, 200)
                                        .addComponent(lblIdioma)
                                        .addComponent(comboBoxIdioma, 200, 200, 200)
                                        .addComponent(lblTipoArquivo)
                                        .addComponent(comboBoxTipoArquivo, 200, 200, 200)
                                        .addComponent(lblGenero)
                                        .addComponent(comboBoxGenero, 200, 200, 200)
                        )
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(btnCancelar, 110, 120, 150)
                                        .addComponent(btnConfirmar, 110, 120, 150)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(lblTitulo)
                        .addGap(25)
                        .addComponent(lblTamanho)
                        .addComponent(txtTamanho, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblDuracao)
                        .addComponent(txtDuracao, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblIdioma)
                        .addComponent(comboBoxIdioma, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblTipoArquivo)
                        .addComponent(comboBoxTipoArquivo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblGenero)
                        .addComponent(comboBoxGenero, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addGap(40)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnConfirmar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)
        );
    }

    private void carregarDadosMidia() {
        txtDuracao.setText(String.valueOf(filme.getDuracao()));
        txtTamanho.setText(String.valueOf(filme.getTamanho()));

        comboBoxTipoArquivo.setSelectedItem(filme.getTipoArquivo());
        comboBoxGenero.setSelectedItem(filme.getGenero());

        for (int i = 0; i < comboBoxGenero.getItemCount(); i++) {
            Genero g = comboBoxGenero.getItemAt(i);
            if (g.getNome().equals(filme.getGenero().getNome())) {
                comboBoxGenero.setSelectedIndex(i);
                break;
            }
        }
        for (int i = 0; i < comboBoxIdioma.getItemCount(); i++) {
            Idioma idi = comboBoxIdioma.getItemAt(i);
            if (idi.getNome().equalsIgnoreCase(filme.getIdioma().getNome())) {
                comboBoxIdioma.setSelectedIndex(i);
                break;
            }
        }


    }
}
