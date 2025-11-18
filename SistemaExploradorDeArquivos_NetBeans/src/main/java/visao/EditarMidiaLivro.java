package visao;

import javax.swing.*;

public class EditarMidiaLivro extends JPanel {

    private JLabel lblTitulo;
    private JLabel lblTamanho;
    private JLabel lblPaginas;
    private JLabel lblAutor;
    private JLabel lblTipoArquivo;

    private JTextField txtTamanho;
    private JTextField txtPaginas;
    private JTextField txtAutor;

    private JComboBox<String> comboTipoArquivo;

    private JButton btnCancelar;
    private JButton btnConfirmar;

    public EditarMidiaLivro() {
        initComponents();
    }

    private void initComponents() {

        lblTitulo = new JLabel("Alterando Livro");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblTamanho = new JLabel("Tamanho do Arquivo (MB)");
        txtTamanho = new JTextField();

        lblPaginas = new JLabel("Quantidade de Páginas");
        txtPaginas = new JTextField();

        lblAutor = new JLabel("Autor");
        txtAutor = new JTextField();

        lblTipoArquivo = new JLabel("Tipo de Arquivo");
        comboTipoArquivo = new JComboBox<>(new String[] { "Item 1", "Item 2", "Item 3" });

        btnCancelar = new JButton("Cancelar");
        btnConfirmar = new JButton("Confirmar");

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
                                        .addComponent(txtTamanho, 200, 200, 200)
                                        .addComponent(lblPaginas)
                                        .addComponent(txtPaginas, 200, 200, 200)
                                        .addComponent(lblAutor)
                                        .addComponent(txtAutor, 200, 200, 200)
                                        .addComponent(lblTipoArquivo)
                                        .addComponent(comboTipoArquivo, 200, 200, 200)
                                )
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancelar, 100, 120, 150)
                                .addComponent(btnConfirmar, 100, 120, 150)
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
                        .addComponent(lblPaginas)
                        .addComponent(txtPaginas, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblAutor)
                        .addComponent(txtAutor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(lblTipoArquivo)
                        .addComponent(comboTipoArquivo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addGap(40)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnConfirmar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)
        );
    }
}
