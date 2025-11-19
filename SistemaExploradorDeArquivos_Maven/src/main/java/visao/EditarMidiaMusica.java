package visao;

import controle.ExploradorDeArquivos;
import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.midias.Midia;
import modelo.midias.Musica;
import util.ComboUtil;
import util.JOptionPaneUtil;

import javax.swing.*;
import java.awt.*;

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

        ComboUtil.carregarTiposArquivosMusica(comboBoxTipoArquivo);
        ComboUtil.carregarGenerosComFiltro(comboBoxGenero, ETipoGenero.MUSICAL);
        carregarDadosMidia();
    }

    private void initComponents() {

        // ---------- COMPONENTES (Sem alteração) ----------
        lblTitulo = new JLabel("Alterando Música");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22)); // Usando a importação
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

        // ---------- LAYOUT (Baseado no padrão centralizado) ----------

        // 1. Aplicar o fundo e a borda diretamente no 'this'
        setBackground(new java.awt.Color(247, 247, 255));
        setBorder(BorderFactory.createLineBorder(new java.awt.Color(220, 220, 255)));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // --- HORIZONTAL ---
        // Usamos "molas" (addContainerGap) para centralizar o formulário e os botões
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        // 1. Título ocupa toda a largura e se auto-centraliza
                        .addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                        // 2. Grupo do Formulário (centralizado com molas)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola esquerda

                                // Coluna 1: Rótulos
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTamanho)
                                        .addComponent(lblDuracao)
                                        .addComponent(lblArtista)
                                        .addComponent(lblTipoArquivo)
                                        .addComponent(lblGenero)
                                )
                                .addGap(10) // Espaço entre colunas

                                // Coluna 2: Campos de Entrada (com tamanho fixo)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false) // 'false' evita que cresçam com o rótulo
                                        .addComponent(campoTamanho, 200, 200, 270)
                                        .addComponent(campoDuracao, 200, 200, 270)
                                        .addComponent(campoArtista, 200, 200, 270)
                                        .addComponent(comboBoxTipoArquivo, 200, 200, 270)
                                        .addComponent(comboBoxGenero, 200, 200, 270)
                                )
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola direita
                        )

                        // 3. Grupo dos Botões (centralizado com molas)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola esquerda
                                .addComponent(botaoCancelar, 110, 120, 150)
                                .addGap(8)
                                .addComponent(botaoConfirmar, 110, 120, 150)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola direita
                        )
        );

        // --- VERTICAL ---
        // Estrutura linha por linha, garantindo alinhamento de base e espaçamento
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(lblTitulo) // Título
                        .addGap(25)

                        // Linha 1: Tamanho
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTamanho)
                                .addComponent(campoTamanho, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(10)
                        // Linha 2: Duração
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDuracao)
                                .addComponent(campoDuracao, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(10)
                        // Linha 3: Artista
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblArtista)
                                .addComponent(campoArtista, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(10)
                        // Linha 4: Tipo de Arquivo
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTipoArquivo)
                                .addComponent(comboBoxTipoArquivo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(10)
                        // Linha 5: Gênero
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblGenero)
                                .addComponent(comboBoxGenero, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(40) // Espaço maior antes dos botões

                        // Linha 6: Botões
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
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
