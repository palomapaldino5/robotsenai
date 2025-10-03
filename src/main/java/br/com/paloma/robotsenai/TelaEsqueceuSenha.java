package br.com.paloma.robotsenai;

import java.awt.*;
import javax.swing.*;

public class TelaEsqueceuSenha extends JFrame {

    public TelaEsqueceuSenha() {
        setTitle("Recuperar Senha");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // fecha só essa janela

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Texto explicativo
        JLabel lblInfo = new JLabel("<html>Para redefinir sua senha, preencha seu usuário ou e-mail.<br>"
                + "Se sua conta for encontrada, um e-mail será enviado<br>"
                + "com instruções para redefinir o acesso.</html>");
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblInfo, gbc);

        // Campo usuário
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Usuário:"), gbc);
        JTextField txtUsuario = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtUsuario, gbc);

        JButton btnBuscarUsuario = new JButton("Buscar");
        gbc.gridx = 1; gbc.gridy++;
        panel.add(btnBuscarUsuario, gbc);

        // Separador
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(new JSeparator(), gbc);

        // Campo e-mail
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("E-mail:"), gbc);
        JTextField txtEmail = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtEmail, gbc);

        JButton btnBuscarEmail = new JButton("Buscar");
        gbc.gridx = 1; gbc.gridy++;
        panel.add(btnBuscarEmail, gbc);

        add(panel);

        // Ações dos botões
        btnBuscarUsuario.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            if (usuario.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha o campo de usuário!");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Se o usuário '" + usuario + "' existir, um e-mail será enviado.",
                        "Recuperação de Senha",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnBuscarEmail.addActionListener(e -> {
            String email = txtEmail.getText();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha o campo de e-mail!");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Se o e-mail '" + email + "' existir, um e-mail será enviado.",
                        "Recuperação de Senha",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
