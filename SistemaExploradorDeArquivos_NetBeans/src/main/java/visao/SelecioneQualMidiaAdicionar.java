package visao;

import controle.ExploradorDeArquivos;
import modelo.Salvamento;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class SelecioneQualMidiaAdicionar extends javax.swing.JPanel {

    private ExploradorDeArquivos explorador;
    private JPanel painelPrincipal;
    private Salvamento salvamento;

    public SelecioneQualMidiaAdicionar(JPanel painelPrincipal, ExploradorDeArquivos explorador, Salvamento salvamento) {
        initComponents();
        this.painelPrincipal = painelPrincipal;
        this.explorador = explorador;  // CORRIGIDO!
        this.salvamento = salvamento;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        painelFundo = new javax.swing.JPanel();
        btnFilme = new javax.swing.JToggleButton();
        btnMusica = new javax.swing.JToggleButton();
        btnLivro = new javax.swing.JToggleButton();
        titulo = new javax.swing.JLabel();

        setLayout(new BorderLayout());

        painelFundo.setBackground(new java.awt.Color(230, 230, 230));

        btnFilme.setText("Filme");
        btnFilme.addActionListener(evt -> abrirPainelFilme());

        btnMusica.setText("Música");
        btnMusica.addActionListener(evt -> abrirPainelMusica());

        btnLivro.setText("Livro");
        btnLivro.addActionListener(evt -> abrirPainelLivro());

        titulo.setFont(new java.awt.Font("Segoe UI", 0, 18));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Selecione qual mídia deseja adicionar");

        javax.swing.GroupLayout painelFundoLayout = new javax.swing.GroupLayout(painelFundo);
        painelFundo.setLayout(painelFundoLayout);
        painelFundoLayout.setHorizontalGroup(
                painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addGroup(painelFundoLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnFilme, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(btnLivro, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(btnMusica, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        painelFundoLayout.setVerticalGroup(
                painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelFundoLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(titulo)
                                .addGap(30, 30, 30)
                                .addComponent(btnFilme, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(250, Short.MAX_VALUE))
        );

        add(painelFundo, BorderLayout.CENTER);
    }

    // ==== MÉTODOS PARA ABRIR OS PAINÉIS CORRETAMENTE ====

    private void abrirPainelFilme() {
        trocarPainel(new AdicionarMidiaFilme(explorador));
    }

    private void abrirPainelMusica() {
        trocarPainel(new AdicionarMidiaMusica(explorador));
    }

    private void abrirPainelLivro() {
        trocarPainel(new AdicionarMidiaLivro(explorador, salvamento));
    }

    // ==== Método padrão de troca de painel ====

    private void trocarPainel(JPanel novoPainel) {
        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.add(novoPainel, BorderLayout.CENTER);
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    // Variables declaration
    private javax.swing.JPanel painelFundo;
    private javax.swing.JToggleButton btnFilme;
    private javax.swing.JToggleButton btnMusica;
    private javax.swing.JToggleButton btnLivro;
    private javax.swing.JLabel titulo;
}
