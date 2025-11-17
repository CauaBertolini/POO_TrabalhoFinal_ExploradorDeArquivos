package visao;

import java.awt.*;
import javax.swing.*;

public class MoverMidia extends JPanel {

    private JLabel lblTitulo;
    private JLabel lblInforme;
    private JTextField txtDestino;
    private JButton btnProcurar;
    private JButton btnCancelar;
    private JButton btnConfirmar;

    public MoverMidia() {
        initComponents();
    }

    private void initComponents() {

        // ---------- COMPONENTES ----------
        lblTitulo = new JLabel("Movendo Mídia");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblInforme = new JLabel("Informe para onde quer mover");

        txtDestino = new JTextField();

        btnProcurar = new JButton("Procurar...");
        btnCancelar = new JButton("Cancelar");
        btnConfirmar = new JButton("Confirmar");

        // ---------- LAYOUT ----------
        setBackground(new Color(245, 245, 245)); // leve cinza igual NetBeans
        setBorder(BorderFactory.createLineBorder(new Color(200, 200, 255), 2));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblInforme)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(txtDestino, 200, 200, 250)
                                .addComponent(btnProcurar)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancelar)
                                .addGap(40)
                                .addComponent(btnConfirmar)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(lblTitulo)
                        .addGap(30)
                        .addComponent(lblInforme)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDestino, 28, 28, 28)
                                .addComponent(btnProcurar)
                        )
                        .addGap(60)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCancelar)
                                .addComponent(btnConfirmar)
                        )
                        .addGap(20)
        );
    }

    // Para testar o painel individualmente
    public static void main(String[] args) {
        JFrame frame = new JFrame("Teste Painel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MoverMidia());
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
