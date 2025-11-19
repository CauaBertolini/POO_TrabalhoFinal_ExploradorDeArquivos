package visao;

import controle.ExploradorDeArquivos;
import modelo.Salvamento;

import java.awt.BorderLayout;
import javax.swing.*;

public class SelecionarMidiaAdicionar extends JPanel {
    private javax.swing.JPanel painelFundo;
    private javax.swing.JToggleButton btnFilme;
    private javax.swing.JToggleButton btnMusica;
    private javax.swing.JToggleButton btnLivro;
    private javax.swing.JLabel titulo;

    private ExploradorDeArquivos explorador;
    private JPanel painelPrincipal;
    private Salvamento salvamento;

    public SelecionarMidiaAdicionar(JPanel painelPrincipal, ExploradorDeArquivos explorador) {
        initComponents();
        this.painelPrincipal = painelPrincipal;
        this.explorador = explorador;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        painelFundo = new JPanel();
        btnFilme = new JToggleButton();
        btnMusica = new JToggleButton();
        btnLivro = new JToggleButton();
        titulo = new JLabel();

        setLayout(new BorderLayout());

        painelFundo.setBackground(new java.awt.Color(247, 247, 255));

        btnFilme.setText("Filme");
        btnFilme.addActionListener(evt -> abrirPainelFilme());

        btnMusica.setText("Música");
        btnMusica.addActionListener(evt -> abrirPainelMusica());

        btnLivro.setText("Livro");
        btnLivro.addActionListener(evt -> abrirPainelLivro());

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 22));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Selecione qual mídia deseja adicionar");

        javax.swing.GroupLayout painelFundoLayout = new javax.swing.GroupLayout(painelFundo);
        painelFundo.setLayout(painelFundoLayout);
        painelFundoLayout.setHorizontalGroup(
                painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addGroup(painelFundoLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnFilme, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(btnLivro, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(btnMusica, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelFundoLayout.setVerticalGroup(
                painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelFundoLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(titulo)
                                .addGap(30, 30, 30)
                                .addComponent(btnFilme, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        trocarPainel(new AdicionarMidiaLivro(explorador));
    }

    private void trocarPainel(JPanel novoPainel) {
        painelPrincipal.removeAll();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.add(novoPainel, BorderLayout.CENTER);
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

}