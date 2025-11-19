package visao;

import controle.ExploradorDeArquivos;
import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.Idioma;
import modelo.midias.*;
import util.ComboUtil;
import util.JOptionPaneUtil;

import javax.swing.*;
import java.awt.*;

public class EditarMidiaFilme extends JPanel {

    private JLabel lblTitulo;
    private JLabel lblTamanho;
    private JLabel lblDuracao;
    private JLabel lblIdioma;
    private JLabel lblTipoArquivo;
    private JLabel lblGenero;

    private JTextField campoTamanho;
    private JTextField campoDuracao;

    private JComboBox<Genero> comboBoxGenero;
    private JComboBox<Idioma> comboBoxIdioma;
    private JComboBox<ETipoArquivo> comboBoxTipoArquivo;

    private JButton botaoCancelar;
    private JButton botaoConfirmar;

    private ExploradorDeArquivos explorador;
    private Filme filme;

    public EditarMidiaFilme(Midia midiaSelecionada, ExploradorDeArquivos explorador) {
        this.explorador = explorador;
        initComponents();
        this.filme = (Filme) midiaSelecionada;

        ComboUtil.carregarTiposArquivosFilme(comboBoxTipoArquivo);
        ComboUtil.carregarGenerosComFiltro(comboBoxGenero, ETipoGenero.CINEMA);
        ComboUtil.carregarIdiomas(comboBoxIdioma);
        carregarDadosMidia();
    }

    private void initComponents() {

        // ---------- COMPONENTES (Sem alteração) ----------
        lblTitulo = new JLabel("Alterando Filme");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22)); // Usando a importação
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblTamanho = new JLabel("Tamanho do Arquivo (MB)");
        campoTamanho = new JTextField();

        lblDuracao = new JLabel("Duração do filme");
        campoDuracao = new JTextField();

        lblIdioma = new JLabel("Idioma");
        comboBoxIdioma = new JComboBox<>();

        lblTipoArquivo = new JLabel("Tipo de Arquivo");
        comboBoxTipoArquivo = new JComboBox<>();

        lblGenero = new JLabel("Gênero");
        comboBoxGenero = new JComboBox<>();

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(evt -> botaoCancelarAcao());

        botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.addActionListener(evt -> botaoConfirmarAcao());

        // ---------- LAYOUT (Baseado no seu exemplo) ----------

        // 1. Aplicar o fundo e a borda diretamente no 'this'
        setBackground(new java.awt.Color(247, 247, 255));
        setBorder(BorderFactory.createLineBorder(new java.awt.Color(220, 220, 255)));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // --- HORIZONTAL ---
        // Exatamente como no seu exemplo, usamos "molas" para centralizar
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
                                        .addComponent(lblIdioma)
                                        .addComponent(lblTipoArquivo)
                                        .addComponent(lblGenero)
                                )
                                .addGap(10) // Espaço entre colunas

                                // Coluna 2: Campos de Entrada (com tamanho fixo do seu exemplo)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false) // 'false' evita que cresçam com o rótulo
                                        .addComponent(campoTamanho, 200, 200, 270)
                                        .addComponent(campoDuracao, 200, 200, 270)
                                        .addComponent(comboBoxIdioma, 200, 200, 270)
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
        // Estrutura linha por linha, como no seu exemplo
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
                        // Linha 3: Idioma
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblIdioma)
                                .addComponent(comboBoxIdioma, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
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

    private void botaoConfirmarAcao() {
        alterarMidia();
    }

    private void botaoCancelarAcao() {
        explorador.exploradorLimparPainelDireito();
    }



    private void alterarMidia() {
        try {
            if (campoDuracao.getText().isEmpty() || campoDuracao.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tamanho e duração não podem estar vazios.");
                return;
            }

            float tamanho = Float.parseFloat(campoTamanho.getText());
            double duracao = Double.parseDouble(campoDuracao.getText());
            Genero genero = (Genero) comboBoxGenero.getSelectedItem();
            ETipoArquivo tipoArquivo = (ETipoArquivo) comboBoxTipoArquivo.getSelectedItem();
            Idioma idioma = (Idioma) comboBoxIdioma.getSelectedItem();

            if (explorador.alterarMidia(filme.getCaminho(), tamanho, duracao, genero, tipoArquivo, idioma)) {
                JOptionPaneUtil.mostrarMensagemSucesso("Filme alterado com sucesso!");
            }


        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tamanho e duração precisam ser números válidos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro inesperado ao alterar filme:\n" + e.getMessage());
        }
    }

    private void carregarDadosMidia() {
        campoDuracao.setText(String.valueOf(filme.getDuracao()));
        campoTamanho.setText(String.valueOf(filme.getTamanho()));

        comboBoxTipoArquivo.setSelectedItem(filme.getTipoArquivo());

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
