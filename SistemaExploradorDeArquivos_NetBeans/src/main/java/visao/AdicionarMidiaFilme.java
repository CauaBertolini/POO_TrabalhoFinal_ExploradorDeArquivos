package visao;

import controle.ExploradorDeArquivos;
import enumerador.ETipoArquivo;
import enumerador.ETipoGenero;
import excecao.CampoMenorOuIgualAZeroExcecao;
import excecao.CampoVazioOuNuloExcecao;
import modelo.Genero;
import modelo.Idioma;
import modelo.Listas;
import util.ComboUtil;
import util.JOptionPaneUtil;

import javax.swing.*;

public class AdicionarMidiaFilme extends JPanel {

    private ExploradorDeArquivos explorador;
    private Listas lista;
    private String caminho;

    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JTextField campoCaminho;
    private javax.swing.JComboBox<Genero> comboBoxGenero;
    private javax.swing.JComboBox<Idioma> comboBoxIdioma;
    private javax.swing.JComboBox<ETipoArquivo> comboBoxTipoArquivo;
    private javax.swing.JTextField campoDuracao;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JToggleButton botaoProcurar;
    private javax.swing.JTextField campoTamanho;
    private javax.swing.JTextField campoTituloFilme;

    public AdicionarMidiaFilme(ExploradorDeArquivos explorador) {
        initComponents();
        this.explorador = explorador;

        lista = new Listas();

        ComboUtil.carregarGenerosComFiltro(comboBoxGenero, ETipoGenero.CINEMA);
        ComboUtil.carregarTipoArquivoFilme(comboBoxTipoArquivo);
        ComboUtil.carregarIdioma(comboBoxIdioma);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jFileChooser1 = new JFileChooser();
        botaoCadastrar = new JButton();
        botaoCancelar = new JButton();
        comboBoxGenero = new JComboBox<>();
        jLabel7 = new JLabel();
        campoDuracao = new JTextField();
        jLabel5 = new JLabel();
        campoTamanho = new JTextField();
        jLabel4 = new JLabel();
        campoTituloFilme = new JTextField();
        jLabel3 = new JLabel();
        campoCaminho = new JTextField();
        jLabel2 = new JLabel();
        jLabel1 = new JLabel();
        jLabel8 = new JLabel();
        comboBoxIdioma = new JComboBox<>();
        comboBoxTipoArquivo = new JComboBox<>();
        jLabel9 = new JLabel();
        botaoProcurar = new JToggleButton();

        // --- Ajustes de Padrão ---
        setBackground(new java.awt.Color(247, 247, 255));
        setBorder(BorderFactory.createLineBorder(new java.awt.Color(220, 220, 255)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Cadastro de Filme");

        // --- Nomes dos Rótulos (Labels) ---
        jLabel2.setText("Caminho do Arquivo");
        jLabel3.setText("Título");
        jLabel4.setText("Tamanho do Arquivo (MB)");
        jLabel5.setText("Duração do Filme (min)");
        jLabel7.setText("Gênero:");
        jLabel8.setText("Idioma:");
        jLabel9.setText("Tipo do Arquivo:");

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(evt -> botaoCadastrarAcao(evt));

        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(evt -> botaoCancelarAcao(evt));

        comboBoxGenero.setModel(new DefaultComboBoxModel<>());
        comboBoxIdioma.setModel(new DefaultComboBoxModel<>());
        comboBoxTipoArquivo.setModel(new DefaultComboBoxModel<>());

        botaoProcurar.setText("Procurar");

        // --- ALTERAÇÃO 1: Reduzir a fonte do botão "Procurar" ---
        // Pega a fonte atual do botão e apenas diminui o tamanho para 11
        botaoProcurar.setFont(botaoProcurar.getFont().deriveFont(11f));

        botaoProcurar.addActionListener(evt -> botaoProcurarAcao(evt));

        // --- INÍCIO DA REFORMATAÇÃO DO LAYOUT ---
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // --- Layout Horizontal (Com centralização) ---
        layout.setHorizontalGroup(
                // O grupo principal agora está alinhado à ESQUERDA,
                // mas vamos controlar a centralização de cada grupo individualmente.
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        // --- ALTERAÇÃO 2: Forçar o título a se expandir e centralizar ---
                        // Isso faz o JLabel preencher todo o espaço horizontal disponível,
                        // e como o texto dele está alinhado (SwingConstants.CENTER), ele centraliza.
                        .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                        // Grupo para o formulário (centralizado com "molas")
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola flexível à esquerda
                                // Coluna 1: Rótulos
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)
                                )
                                .addGap(10) // Espaço entre colunas
                                // Coluna 2: Campos de Entrada
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(campoCaminho, 180, 180, 180)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(botaoProcurar, 80, 80, 80)
                                        )
                                        .addComponent(campoTituloFilme, 200, 200, 270)
                                        .addComponent(campoTamanho, 200, 200, 270)
                                        .addComponent(campoDuracao, 200, 200, 270)
                                        .addComponent(comboBoxTipoArquivo, 200, 200, 270)
                                        .addComponent(comboBoxGenero, 200, 200, 270)
                                        .addComponent(comboBoxIdioma, 200, 200, 270)
                                )
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola flexível à direita
                        )

                        // --- ALTERAÇÃO 3: Centralizar os botões ---
                        // Grupo para os botões (também centralizado com "molas")
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola flexível à esquerda
                                .addComponent(botaoCancelar, 110, 120, 150)
                                .addGap(8)
                                .addComponent(botaoCadastrar, 110, 120, 150)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Mola flexível à direita
                        )
        );

        // --- Layout Vertical (Permanece o mesmo) ---
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(jLabel1) // Título
                        .addGap(25)

                        // Linha 1: Caminho
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(campoCaminho, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                // Ajusta a altura do botão "Procurar" para 30, igual ao campo de texto
                                .addComponent(botaoProcurar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(10)
                        // Linha 2: Título
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(campoTituloFilme, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(10)
                        // Linha 3: Tamanho
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(campoTamanho, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(10)
                        // Linha 4: Duração
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(campoDuracao, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(10)
                        // Linha 5: Tipo de Arquivo
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(comboBoxTipoArquivo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(10)
                        // Linha 6: Gênero
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(comboBoxGenero, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(10)
                        // Linha 7: Idioma
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(comboBoxIdioma, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(40) // Espaço maior antes dos botões

                        // Linha 8: Botões
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(botaoCancelar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoCadastrar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)
        );
        // --- FIM DA REFORMATAÇÃO DO LAYOUT ---
    }

    // -------------------------
    // MÉTODOS DE AÇÃO
    // -------------------------
    private void botaoCadastrarAcao(java.awt.event.ActionEvent evt) {

        if (caminho == null || caminho.isEmpty()) {
            JOptionPaneUtil.mostrarMensagemErro("Caminho não selecionado!");
            return;
        }

        try {
            String titulo = campoTituloFilme.getText();
            float tamanhoMB = Float.parseFloat(campoTamanho.getText());
            double duracao = Double.parseDouble(campoDuracao.getText());
            Genero genero = (Genero) comboBoxGenero.getSelectedItem();
            Idioma idioma = (Idioma) comboBoxIdioma.getSelectedItem();
            ETipoArquivo tipoArquivo = (ETipoArquivo) comboBoxTipoArquivo.getSelectedItem();

            explorador.criarNovaMidia(
                    caminho,
                    titulo,
                    tamanhoMB,
                    duracao,
                    tipoArquivo,
                    genero,
                    idioma
            );

            JOptionPaneUtil.mostrarMensagemSucesso("Filme cadastrado com sucesso!");
            explorador.exploradorLimparPainelDireito();

        } catch (CampoVazioOuNuloExcecao | CampoMenorOuIgualAZeroExcecao ex) {

            JOptionPaneUtil.mostrarMensagemErro("Erro ao cadastrar mídia:\n" + ex.getMessage());

        } catch (NumberFormatException ex) {

            JOptionPaneUtil.mostrarMensagemErro("Valores numéricos inválidos: tamanho e duração precisam ser números.");
        }
    }

    private void botaoCancelarAcao(java.awt.event.ActionEvent evt) {
        explorador.exploradorLimparPainelDireito();
    }

    private void botaoProcurarAcao(java.awt.event.ActionEvent evt) {
        String selecionado = explorador.abrirSeletorDeDiretorio();

        if (selecionado != null) {
            caminho = selecionado;
            campoCaminho.setText(selecionado);
        }
    }

}
