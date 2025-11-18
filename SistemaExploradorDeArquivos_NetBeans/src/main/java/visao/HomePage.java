package visao;

import controle.ExploradorDeArquivos;
import excecao.ArquivoNaoExisteExcecao;
import modelo.*;
import modelo.midias.Filme;
import modelo.midias.Musica;
import modelo.midias.Livro;
import modelo.midias.Midia;
import util.ComboUtil;
import util.JOptionPaneUtil;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HomePage extends javax.swing.JFrame {
    private javax.swing.JLabel labelFiltroGenero;
    private javax.swing.JLabel labelFiltroTipo;
    private javax.swing.JButton botaoAdicionarMidia;
    private javax.swing.JButton botaoAlterarMidia;
    private javax.swing.JButton botaoApagarMidia;
    private javax.swing.JButton botaoAtualizarTabela;
    private javax.swing.JButton botaoRenomear;
    private javax.swing.JButton botaoMover;
    private javax.swing.JComboBox<String> generoCombo;
    private javax.swing.JComboBox<String> tipoCombo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaMidias;
    private javax.swing.JPanel painelDireito;

    private Listas lista;
    private Salvamento salvamento;
    private ExploradorDeArquivos explorador;

    public HomePage() {
        initComponents();
        this.explorador = new ExploradorDeArquivos(this);
        this.salvamento = explorador.getSalvamento();
        this.lista = new Listas();

        configurarTabela();

        removerListenersFiltros();

        configurarFiltros();

        adicionarListenersFiltros();

        filtrarTabela();

        setVisible(true);

    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        painelDireito = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMidias = new javax.swing.JTable();

        setTitle("Sistema de Gerenciamento de Mídias");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        javax.swing.JPanel painelEsquerdo = new javax.swing.JPanel();
        painelEsquerdo.setBackground(new java.awt.Color(240, 240, 240));
        painelEsquerdo.setLayout(new BorderLayout(10, 10));
        painelEsquerdo.setPreferredSize(new Dimension(800, 600));
        painelEsquerdo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel painelSuperior = new JPanel(new GridLayout(5, 2, 8, 8));
        painelSuperior.setOpaque(false);

        botaoAdicionarMidia = new javax.swing.JButton("Adicionar Mídia +");
        botaoAdicionarMidia.addActionListener(evt -> abrirSelecaoMidia());
        painelSuperior.add(botaoAdicionarMidia);

        botaoApagarMidia = new javax.swing.JButton("Apagar Mídia -");
        botaoApagarMidia.addActionListener(evt -> botaoDeletarMidiaAcao());
        painelSuperior.add(botaoApagarMidia);

        botaoAlterarMidia = new javax.swing.JButton("Alterar Mídia");
        botaoAlterarMidia.addActionListener(evt -> botaoAlterarAcao());
        painelSuperior.add(botaoAlterarMidia);

        botaoMover = new javax.swing.JButton("Mover Mídia");
        botaoMover.addActionListener(evt -> botaoMoverAcao());
        painelSuperior.add(botaoMover);

        botaoRenomear = new javax.swing.JButton("Renomear Mídia");
        botaoRenomear.addActionListener(evt -> botaoRenomearAcao());
        painelSuperior.add(botaoRenomear);

        botaoAtualizarTabela = new javax.swing.JButton("Atualizar Lista");
        botaoAtualizarTabela.addActionListener(evt -> botaoAtualizarTabelaAcao());
        painelSuperior.add(botaoAtualizarTabela);

        labelFiltroGenero = new javax.swing.JLabel("Filtro Gênero");
        labelFiltroTipo = new javax.swing.JLabel("Filtro Tipo Arquivo");

        labelFiltroGenero.setVerticalAlignment(SwingConstants.BOTTOM);
        labelFiltroTipo.setVerticalAlignment(SwingConstants.BOTTOM);

        painelSuperior.add(labelFiltroGenero);
        painelSuperior.add(labelFiltroTipo);

        generoCombo = new javax.swing.JComboBox<>();
        tipoCombo = new javax.swing.JComboBox<>();
        painelSuperior.add(generoCombo);
        painelSuperior.add(tipoCombo);

        painelEsquerdo.add(painelSuperior, BorderLayout.NORTH);

        jScrollPane1.setViewportView(tabelaMidias);
        painelEsquerdo.add(jScrollPane1, BorderLayout.CENTER);

        add(painelEsquerdo, BorderLayout.WEST);

        painelDireito.setBackground(new java.awt.Color(247, 247, 255));
        painelDireito.setLayout(new BorderLayout());
        add(painelDireito, BorderLayout.CENTER);
    }

    /**
     * Realiza a exclusão da mídia selecionada na tabela.
     * Obtém a lista de mídias armazenadas e identifica, a partir da linha selecionada na interface,
     * qual objeto corresponde no modelo. Em seguida, solicita ao explorador que remova.
     * Caso a exclusão seja bem-sucedida, uma mensagem de confirmação é exibida.
     * Se nenhuma mídia estiver selecionado ou ocorrer um problema no processo,
     * uma mensagem de erro é mostrada.
     */
    private void botaoDeletarMidiaAcao() {
        try {
            List<Midia> listaMidias = salvamento.getMidias();

            int viewIndex = tabelaMidias.getSelectedRow();
            if (viewIndex == -1) throw new ArquivoNaoExisteExcecao();

            int modelIndex = tabelaMidias.convertRowIndexToModel(viewIndex);

            Midia midiaSelecionada = listaMidias.get(modelIndex);

            if (explorador.excluirMidia(midiaSelecionada)) {
                JOptionPaneUtil.mostrarMensagemSucesso("Mídia excluída com sucesso!");
            } else {
                JOptionPaneUtil.mostrarMensagemErro("Erro ao tentar excluir mídia.");
            }
        } catch (ArquivoNaoExisteExcecao excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Selecione um item da tabela para excluir!");
        }

    }

    /**
     * Chama o processo de alteração da mídia selecionada na tabela.
     * Tenta abrir a tela de edição correspondente e, caso nenhum item tenha sido selecionado,
     * captura a exceção e exibe uma mensagem informando o usuário da necessidade de escolher um arquivo.
     */
    public void botaoAlterarAcao() {
        try {
            abrirAlterarMidia();
        } catch (ArquivoNaoExisteExcecao excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Selecione um item da tabela para alterar!");
        }
    }

    /**
     * Atualiza o conteúdo exibido na tabela de mídias.
     * O método chama a operação para a função para recarregar os dados.
     */
    private void botaoAtualizarTabelaAcao() {
        atualizarTabela();
    }

    /**
     * Inicia o processo de renomear a mídia selecionada na tabela.
     * O método verifica se algum item foi escolhido pelo usuário e,
     * caso houver uma mídia selcionada, abre o painel para renomear.
     * Se nenhuma mídia estiver selecionada, exibe uma mensagem de erro.
     */
    private void botaoRenomearAcao() {
        try {
            List<Midia> listaMidias = salvamento.getMidias();

            int viewIndex = tabelaMidias.getSelectedRow();
            if (viewIndex == -1) throw new ArquivoNaoExisteExcecao();

            int modelIndex = tabelaMidias.convertRowIndexToModel(viewIndex);

            Midia midiaSelecionada = listaMidias.get(modelIndex);

            abrirNoPainelDireito(new RenomearMidia(explorador, midiaSelecionada));

        } catch (ArquivoNaoExisteExcecao excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Selecione um item da tabela para excluir!");
        }

    }

    /**
     * Inicia o processo de mover a mídia selecionada na tabela.
     * O método verifica se uma mídia foi escolhida pelo usuário e, caso houver um arquivo selecionado,
     * abre no painel direito a tela por mover a mídia.
     * Se nenhuma mídia estiver selecionada, uma mensagem de erro é exibida ao usuário.
     */
    private void botaoMoverAcao() {
        try {
            List<Midia> listaMidias = salvamento.getMidias();

            int viewIndex = tabelaMidias.getSelectedRow();
            if (viewIndex == -1) throw new ArquivoNaoExisteExcecao();

            int modelIndex = tabelaMidias.convertRowIndexToModel(viewIndex);

            Midia midiaSelecionada = listaMidias.get(modelIndex);

            abrirNoPainelDireito(new MoverMidia(explorador, midiaSelecionada));

        } catch (ArquivoNaoExisteExcecao excecao) {
            JOptionPaneUtil.mostrarMensagemErro("Selecione um item da tabela para excluir!");
        }
    }

    /**
     * Configura o modelo da tabela exibida na interface.
     * O método define as colunas que serão mostradas e cria um modelo onde nenhuma
     * célula pode ser editada. Depois, o modelo é adicionado à tabela.
     */
    private void configurarTabela() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Caminho", "Tamanho (MB)"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaMidias.setModel(modelo);
    }

    /**
     * Remove todos os listeners dos filtros de tipo e gênero.
     * O método percorre cada listener registrado nos combos
     * e os remove para evitar que eventos antigos interfiram
     * quando os filtros forem atualizados novamente.
     */
    private void removerListenersFiltros() {
        for (ActionListener al : tipoCombo.getActionListeners()) {
            tipoCombo.removeActionListener(al);
        }
        for (ActionListener al : generoCombo.getActionListeners()) {
            generoCombo.removeActionListener(al);
        }
    }

    /**
     * Configura os filtros da tela.
     * O método carrega no combo de tipos e no combo de gêneros
     * as opções disponíveis para que o usuário possa filtrar a lista de mídias.
     */
    private void configurarFiltros() {
        ComboUtil.carregarTiposArquivoParaFiltro(tipoCombo);
        ComboUtil.carregarGenerosParaFiltro(generoCombo, lista.getListaGeneros());
    }

    /**
     * Adiciona os listeners aos filtros da tela.
     * Sempre que o usuário alterar o tipo ou o gênero selecionado,
     * os listeners chamam o método de filtragem da tabela.
     */
    private void adicionarListenersFiltros() {
        tipoCombo.addActionListener(e -> filtrarTabela());
        generoCombo.addActionListener(e -> filtrarTabela());
    }

    /**
     * Atualiza o conteúdo exibido na tabela.
     * O método simplesmente chama a filtragem,
     * garantindo que os dados mostrados estejam sempre atualizados.
     */
    public void atualizarTabela() {
        filtrarTabela();
    }

    /**
     * Atualiza a tabela exibindo somente as mídias que
     * correspondem aos filtros selecionados. O método obtém
     * a lista filtrada pelo explorador, limpa a tabela atual
     * e adiciona novamente somente as mídias que atendem aos filtros.
     */
    private void filtrarTabela() {
        List<modelo.midias.Midia> listaFiltrada = explorador.filtrarMidias(
                generoCombo.getSelectedItem().toString(),
                tipoCombo.getSelectedItem().toString()
        );

        DefaultTableModel model = (DefaultTableModel) tabelaMidias.getModel();
        model.setRowCount(0);

        for (modelo.midias.Midia midia : listaFiltrada) {
            model.addRow(new Object[]{
                    midia.getNome() + "." + midia.getTipoArquivo().name().toLowerCase(),
                    midia.getTamanho()
            });
        }
    }

    /**
     * Abre no painel direito a interface para selecionar o tipo
     * de mídia que o usuário deseja adicionar. O método limpa o
     * painel atual, ajusta o layout e carrega o componente de seleção.
     */
    private void abrirSelecaoMidia() {
        painelDireito.removeAll();
        painelDireito.setLayout(new BorderLayout());

        painelDireito.add(
                new SelecionarMidiaAdicionar(painelDireito, explorador),
                BorderLayout.CENTER
        );
        painelDireito.revalidate();
        painelDireito.repaint();
    }

    /**
     * Abre no painel direito a tela de edição da mídia selecionada.
     * O método verifica se alguma linha da tabela está selecionada
     * e identifica qual tipo de mídia foi escolhido. Em seguida,
     * abre a tela de edição correspondente. Se nenhuma mídia estiver
     * selecionada, uma exceção é lançada.
     *
     * @throws ArquivoNaoExisteExcecao se nenhum item da tabela estiver selecionado.
     */
    public void abrirAlterarMidia() throws ArquivoNaoExisteExcecao{
        List<Midia> listaMidias = salvamento.getMidias();

        int viewIndex = tabelaMidias.getSelectedRow();
        if (viewIndex == -1) throw new ArquivoNaoExisteExcecao();

        int modelIndex = tabelaMidias.convertRowIndexToModel(viewIndex);

        Midia midiaSelecionada = listaMidias.get(modelIndex);

        if (midiaSelecionada instanceof Filme) {
            abrirNoPainelDireito(new EditarMidiaFilme(midiaSelecionada, explorador));
        } else if (midiaSelecionada instanceof Musica) {
            abrirNoPainelDireito(new EditarMidiaMusica(midiaSelecionada, explorador));
        } else if (midiaSelecionada instanceof Livro) {
            abrirNoPainelDireito(new EditarMidiaLivro(midiaSelecionada, explorador));
        }
    }

    /**
     * Substitui o conteúdo do painel direito pelo novo painel.
     * O método remove todos os componentes atuais, define o layout
     * como BorderLayout, adiciona o novo painel ao centro e atualiza a interface.
     *
     * @param painel o painel que será exibido no painel direito
     */
    public void abrirNoPainelDireito(JPanel painel) {
        painelDireito.removeAll();
        painelDireito.setLayout(new BorderLayout());

        painelDireito.add(painel, BorderLayout.CENTER);

        painelDireito.revalidate();
        painelDireito.repaint();
    }

    /**
     * Limpa todo o conteúdo do painel direito.
     * O método remove todos os componentes e atualiza a interface,
     * deixando o painel pronto para carregar novos componentes.
     */
    public void limparPainelDireito() {
        painelDireito.removeAll();
        painelDireito.revalidate();
        painelDireito.repaint();
    }

}