package br.com.paloma.robotsenai;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class TelaCadastroSwing extends JFrame {

    public TelaCadastroSwing() {
        setTitle("Cadastro de Usuário");
        setSize(412, 914);
        setMinimumSize(new Dimension(400, 900));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel principal
        JPanel painel = new JPanel();
        painel.setBackground(new Color(245, 245, 245));
        painel.setLayout(new GridBagLayout());
        add(painel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 25, 15, 25);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fonte
        Font fonteLabel = new Font("Segoe UI", Font.BOLD, 18);
        Font fonteCampo = new Font("Segoe UI", Font.PLAIN, 16);

        // Campos
        JTextField txtNome = criarCampo(fonteCampo);
        JTextField txtUsuario = criarCampo(fonteCampo);
        JPasswordField txtSenha = criarSenha(fonteCampo);
        JTextField txtEmail = criarCampo(fonteCampo);

        // Labels
        JLabel lblNome = new JLabel("Nome:"); lblNome.setFont(fonteLabel);
        JLabel lblUsuario = new JLabel("Usuário:"); lblUsuario.setFont(fonteLabel);
        JLabel lblSenha = new JLabel("Senha:"); lblSenha.setFont(fonteLabel);
        JLabel lblEmail = new JLabel("E-mail:"); lblEmail.setFont(fonteLabel);

        // Botão
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnCadastrar.setBackground(new Color(59, 89, 182));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setBorder(BorderFactory.createLineBorder(new Color(50, 70, 150), 2, true));
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover do botão
        btnCadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCadastrar.setBackground(new Color(40, 70, 180));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnCadastrar.setBackground(new Color(59, 89, 182));
            }
        });

        // Adicionando componentes
        gbc.gridx = 0; gbc.gridy = 0; painel.add(lblNome, gbc);
        gbc.gridy = 1; painel.add(txtNome, gbc);

        gbc.gridy = 2; painel.add(lblUsuario, gbc);
        gbc.gridy = 3; painel.add(txtUsuario, gbc);

        gbc.gridy = 4; painel.add(lblSenha, gbc);
        gbc.gridy = 5; painel.add(txtSenha, gbc);

        gbc.gridy = 6; painel.add(lblEmail, gbc);
        gbc.gridy = 7; painel.add(txtEmail, gbc);

        gbc.gridy = 8; painel.add(btnCadastrar, gbc);

        // Ação do botão
        btnCadastrar.addActionListener(e -> {
            if (txtNome.getText().isEmpty() || txtUsuario.getText().isEmpty() ||
                    txtSenha.getPassword().length == 0 || txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.");
                return;
            }

            char[] senha = txtSenha.getPassword();
            UsuarioDAO dao = new UsuarioDAO();
            boolean ok = dao.cadastrar(txtNome.getText(), txtUsuario.getText(), new String(senha), txtEmail.getText());
            Arrays.fill(senha, '0');

            if (ok) {
                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar.");
            }
        });

        setVisible(true);
    }

    // Método para criar JTextField com estilo
    private JTextField criarCampo(Font fonte) {
        JTextField campo = new JTextField();
        campo.setFont(fonte);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2, true),
                new EmptyBorder(8, 12, 8, 12)
        ));
        campo.setBackground(Color.WHITE);
        return campo;
    }

    // Método para criar JPasswordField com estilo
    private JPasswordField criarSenha(Font fonte) {
        JPasswordField campo = new JPasswordField();
        campo.setFont(fonte);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2, true),
                new EmptyBorder(8, 12, 8, 12)
        ));
        campo.setBackground(Color.WHITE);
        return campo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaCadastroSwing::new);
    }
}