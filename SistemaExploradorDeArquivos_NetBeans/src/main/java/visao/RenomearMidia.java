package visao;

import javax.swing.*;

public class RenomearMidia extends JPanel {

    private JLabel lblTitulo;
    private JLabel lblNome;

    private JTextField txtNome;

    private JButton btnCancelar;
    private JButton btnRenomear;

    public RenomearMidia() {
        initComponents();
    }

    private void initComponents() {

        lblTitulo = new JLabel("Renomeando");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblNome = new JLabel("Nome");
        txtNome = new JTextField();

        btnCancelar = new JButton("Cancelar");
        btnRenomear = new JButton("Renomear");

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
                                        .addComponent(lblNome)
                                        .addComponent(txtNome, 250, 250, 250)
                        )
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(btnCancelar, 110, 120, 150)
                                        .addComponent(btnRenomear, 110, 120, 150)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(lblTitulo)
                        .addGap(25)
                        .addComponent(lblNome)
                        .addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(40)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnRenomear, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(20)
        );
    }
}

