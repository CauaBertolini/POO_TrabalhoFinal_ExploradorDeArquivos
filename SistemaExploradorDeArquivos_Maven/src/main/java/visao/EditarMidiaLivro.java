package visao;

import controle.ExploradorDeArquivos;
import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import modelo.Genero;
import modelo.midias.Livro;
import util.ComboUtil;
import util.JOptionPaneUtil;

import javax.swing.*;
import java.awt.*;

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

    public EditarMidiaLivro(modelo.midias.Midia midiaSelecionada, ExploradorDeArquivos explorador) {
        this.explorador = explorador;
        initComponents();
        this.livro = (Livro) midiaSelecionada;

        ComboUtil.carregarTipoArquivoLivro(comboBoxTipoArquivo);
        ComboUtil.carregarGenerosComFiltro(comboBoxGenero, ETipoGenero.LITERARIO);
        carregarDadosMidia();
    }

    private void initComponents() {

        // ---------- COMPONENTES (Sem alteração) ----------
        lblTitulo = new JLabel("Alterando Livro");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22)); // Usando a importação
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
                                        .addComponent(lblPaginas)
                                        .addComponent(lblAutor)
                                        .addComponent(lblTipoArquivo)
                                        .addComponent(lblGenero)
                                )
                                .addGap(10) // Espaço entre colunas

                                // Coluna 2: Campos de Entrada (com tamanho fixo)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false) // 'false' evita que cresçam
                                        .addComponent(campoTamanho, 200, 200, 270)
                                        .addComponent(campoPaginas, 200, 200, 270)
                                        .addComponent(campoAutor, 200, 200, 270)
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
                        // Linha 2: Páginas
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPaginas)
                                .addComponent(campoPaginas, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(10)
                        // Linha 3: Autor
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblAutor)
                                .addComponent(campoAutor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(comboBoxGenero, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE) // Ajustado para 32
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
