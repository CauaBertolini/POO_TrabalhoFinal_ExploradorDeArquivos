package visao;

import controle.ExploradorDeArquivos;
import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.Midias.Livro;
import modelo.Midias.Midia;
import modelo.Midias.Musica;
import util.ComboUtil;
import util.JOptionPaneUtil;

import javax.swing.*;

public class EditarMidiaMusica extends JPanel {

    private JLabel lblTitulo;
    private JLabel lblTamanho;
    private JLabel lblDuracao;
    private JLabel lblArtista;
    private JLabel lblTipoArquivo;
    private JLabel lblGenero;

    private JTextField campoTamanho;
    private JTextField campoDuracao;
    private JTextField campoArtista;

    private JComboBox<ETipoArquivo> comboBoxTipoArquivo;
    private JComboBox<Genero> comboBoxGenero;

    private JButton botaoCancelar;
    private JButton botaoConfirmar;

    private ExploradorDeArquivos explorador;
    private Musica musica;

    public EditarMidiaMusica(Midia midiaSelecionada, ExploradorDeArquivos explorador) {
        this.explorador = explorador;
        initComponents();
        this.musica = (Musica) midiaSelecionada;

        ComboUtil.carregarTipoArquivoMusica(comboBoxTipoArquivo);
        ComboUtil.carregarGenerosComFiltro(comboBoxGenero, ETipoGenero.MUSICAL);
        carregarDadosMidia();
    }

    private void initComponents() {

        lblTitulo = new JLabel("Alterando Música");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblTamanho = new JLabel("Tamanho do Arquivo");
        campoTamanho = new JTextField();

        lblDuracao = new JLabel("Duração da música");
        campoDuracao = new JTextField();

        lblArtista = new JLabel("Artista");
        campoArtista = new JTextField();

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
        setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lblTitulo)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTamanho)
                                        .addComponent(campoTamanho, 200, 200, 200)
                                        .addComponent(lblDuracao)
                                        .addComponent(campoDuracao, 200, 200, 200)
                                        .addComponent(lblArtista)
                                        .addComponent(campoArtista, 200, 200, 200)
                                        .addComponent(lblTipoArquivo)
                                        .addComponent(comboBoxTipoArquivo, 200, 200, 200)
                                        .addComponent(lblGenero)
                                        .addComponent(comboBoxGenero, 200, 200, 200)
                        )
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(botaoCancelar, 110, 120, 150)
                                        .addComponent(botaoConfirmar, 110, 120, 150)
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
                        .addComponent(lblDuracao)
                        .addComponent(campoDuracao, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblArtista)
                        .addComponent(campoArtista, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblTipoArquivo)
                        .addComponent(comboBoxTipoArquivo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblGenero)
                        .addComponent(comboBoxGenero, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(40)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(botaoCancelar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botaoConfirmar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)
        );
    }

    private void alterarMidia() {

        float tamanho = 0;
        double duracao = 0;
        Genero genero = null;
        ETipoArquivo tipoArquivo = null;
        String autor = "";

        try {
            tamanho = Float.parseFloat(campoTamanho.getText());
            duracao = Double.parseDouble(campoDuracao.getText());
            genero = (Genero) comboBoxGenero.getSelectedItem();
            tipoArquivo = (ETipoArquivo) comboBoxTipoArquivo.getSelectedItem();
            autor = campoArtista.getText();

            explorador.alterarMidia(musica.getCaminho(), tamanho, duracao, genero, tipoArquivo, autor, false);

            JOptionPaneUtil.mostrarMensagemSucesso("Música alterada com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void botaoConfirmarAcao() {
        alterarMidia();
    }

    private void botaoCancelarAcao() {
        explorador.exploradorLimparPainelDireito();
    }

    private void carregarDadosMidia() {
        campoDuracao.setText(String.valueOf(musica.getDuracao()));
        campoTamanho.setText(String.valueOf(musica.getTamanho()));
        campoArtista.setText(String.valueOf(musica.getArtista()));

        comboBoxTipoArquivo.setSelectedItem(musica.getTipoArquivo());

        for (int i = 0; i < comboBoxGenero.getItemCount(); i++) {
            Genero g = comboBoxGenero.getItemAt(i);
            if (g.getNome().equals(musica.getGenero().getNome())) {
                comboBoxGenero.setSelectedIndex(i);
                break;
            }
        }
    }
}
