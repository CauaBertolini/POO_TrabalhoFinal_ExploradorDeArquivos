package visao;

import controle.ExploradorDeArquivos;
import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.Idioma;
import modelo.Midias.Livro;
import util.ComboUtil;
import util.JOptionPaneUtil;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EditarMidiaLivro extends JPanel {

    private JLabel lblTitulo;
    private JLabel lblTamanho;
    private JLabel lblPaginas;
    private JLabel lblAutor;
    private JLabel lblTipoArquivo;
    private JLabel lblGenero;

    private JTextField campoTamanho;
    private JTextField campoPaginas;
    private JTextField campoAutor;

    private JComboBox<ETipoArquivo> comboBoxTipoArquivo;
    private JComboBox<Genero> comboBoxGenero;

    private JButton botaoCancelar;
    private JButton botaoConfirmar;

    private ExploradorDeArquivos explorador;
    private Livro livro;

    public EditarMidiaLivro(modelo.Midias.Midia midiaSelecionada, ExploradorDeArquivos explorador) {
        this.explorador = explorador;
        initComponents();
        this.livro = (modelo.Midias.Livro) midiaSelecionada;

        ComboUtil.carregarTipoArquivoLivro(comboBoxTipoArquivo);
        ComboUtil.carregarGenerosComFiltro(comboBoxGenero, ETipoGenero.LITERARIO);
        carregarDadosMidia();
    }

    private void initComponents() {

        lblTitulo = new JLabel("Alterando Livro");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblTamanho = new JLabel("Tamanho do Arquivo (MB)");
        campoTamanho = new JTextField();

        lblPaginas = new JLabel("Quantidade de Páginas");
        campoPaginas = new JTextField();

        lblAutor = new JLabel("Autor");
        campoAutor = new JTextField();

        lblTipoArquivo = new JLabel("Tipo de Arquivo");
        comboBoxTipoArquivo = new JComboBox<>();

        lblGenero = new JLabel("Gênero");
        comboBoxGenero = new JComboBox<>();

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(evt -> botaoCancelarAcao());

        botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.addActionListener(evt -> botaoConfirmarAcao());

        setBackground(new java.awt.Color(247, 247, 255));
        setBorder(BorderFactory.createLineBorder(new java.awt.Color(220, 220, 255)));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lblTitulo)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTamanho)
                                        .addComponent(campoTamanho, 200, 200, 200)
                                        .addComponent(lblPaginas)
                                        .addComponent(campoPaginas, 200, 200, 200)
                                        .addComponent(lblAutor)
                                        .addComponent(campoAutor, 200, 200, 200)
                                        .addComponent(lblTipoArquivo)
                                        .addComponent(comboBoxTipoArquivo, 200, 200, 200)
                                        .addComponent(lblGenero)
                                        .addComponent(comboBoxGenero, 200, 200, 200)
                                )
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(botaoCancelar, 100, 120, 150)
                                .addComponent(botaoConfirmar, 100, 120, 150)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(lblTitulo)
                        .addGap(25)
                        .addComponent(lblTamanho)
                        .addComponent(campoTamanho, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblPaginas)
                        .addComponent(campoPaginas, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblAutor)
                        .addComponent(campoAutor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblTipoArquivo)
                        .addComponent(comboBoxTipoArquivo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblGenero)
                        .addComponent(comboBoxGenero, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(40)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(botaoCancelar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoConfirmar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)
        );
    }

    private void botaoConfirmarAcao() {
        alterarMidia();
    }

    private void botaoCancelarAcao() {
        explorador.exploradorLimparPainelDireito();
    }

    private void alterarMidia() {

        float tamanho = 0;
        double duracao = 0;
        Genero genero = null;
        ETipoArquivo tipoArquivo = null;
        String autor = "";

        try {
            tamanho = Float.parseFloat(campoTamanho.getText());
            duracao = Double.parseDouble(campoPaginas.getText());
            genero = (Genero) comboBoxGenero.getSelectedItem();
            tipoArquivo = (ETipoArquivo) comboBoxTipoArquivo.getSelectedItem();
            autor = campoAutor.getText();

            explorador.alterarMidia(livro.getCaminho(), tamanho, duracao, genero, tipoArquivo, autor, true);

            JOptionPaneUtil.mostrarMensagemSucesso("Livro alterado com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void carregarDadosMidia() {
        campoPaginas.setText(String.valueOf(livro.getDuracao()));
        campoTamanho.setText(String.valueOf(livro.getTamanho()));
        campoAutor.setText(String.valueOf(livro.getAutor()));

        comboBoxTipoArquivo.setSelectedItem(livro.getTipoArquivo());

        for (int i = 0; i < comboBoxGenero.getItemCount(); i++) {
            Genero g = comboBoxGenero.getItemAt(i);
            if (g.getNome().equals(livro.getGenero().getNome())) {
                comboBoxGenero.setSelectedIndex(i);
                break;
            }
        }
    }
}
