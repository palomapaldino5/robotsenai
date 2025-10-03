package br.com.paloma.robotsenai;

import java.awt.*;
import javax.swing.*;

public class TelaLoginSwing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaLoginSwing::new);
    }

    public TelaLoginSwing() {
        JFrame frame = new JFrame("Tela de Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(412, 914);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        // === LOGO/IMAGEM NO TOPO ===
        JLabel lblImagem = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/static/imagem.png"));
            Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            lblImagem.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblImagem.setText("LOGO");
            lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
        }
        lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(lblImagem, BorderLayout.NORTH);

        // JLabel lblImagem = new JLabel(); // Removido para evitar duplicidade
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/static/imagem.png"));
            Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            lblImagem.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblImagem.setText("LOGO"); // fallback se não encontrar
        }
        lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(lblImagem, BorderLayout.NORTH);


        // === PAINEL CENTRAL COM CAMPOS ===
        JPanel panelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUsuario = new JLabel("Usuário:");
        JTextField txtUsuario = new JTextField(15);
        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(15);

        JButton btnEntrar = new JButton("Entrar");
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnEsqueceu = new JButton("Esqueceu a senha?");

        // Alinhando em 2 colunas
        gbc.gridx = 0; gbc.gridy = 0;
        panelCampos.add(lblUsuario, gbc);
        gbc.gridx = 1;
        panelCampos.add(txtUsuario, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panelCampos.add(lblSenha, gbc);
        gbc.gridx = 1;
        panelCampos.add(txtSenha, gbc);

        // Botão Entrar centralizado
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelCampos.add(btnEntrar, gbc);

        // Botão Cadastrar
        btnCadastrar.addActionListener(e -> {
            new TelaCadastroSwing(); // abre a tela de cadastro
        });
        
        gbc.gridy++;
        panelCampos.add(btnCadastrar, gbc);

        // Botão Esqueceu a Senha
        btnEsqueceu.addActionListener(e -> {
            new TelaEsqueceuSenha().setVisible(true);
        });        
        gbc.gridy++;
        panelCampos.add(btnEsqueceu, gbc);

        frame.add(panelCampos, BorderLayout.CENTER);

        // === AÇÕES ===
        btnEntrar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());

            if (usuario.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Preencha usuário e senha!");
                return;
            }

            if (LoginService.autenticar(usuario, senha)) {
                JOptionPane.showMessageDialog(frame, "Bem-vindo, " + usuario + "!");
                // Aqui você pode abrir outra tela, ex: TelaPrincipal()
            } else {
                JOptionPane.showMessageDialog(frame, "Usuário ou senha inválidos!");
            }
        });

        btnCadastrar.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Cadastro de novo usuário ainda não implementado.");
        });

        btnEsqueceu.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Função 'Esqueceu a senha?' ainda não implementada.");
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

// === SIMULAÇÃO DE UM SERVIÇO DE LOGIN ===
class LoginService {
    public static boolean autenticar(String usuario, String senha) {
        // Aqui poderia consultar um banco de dados
        return usuario.equals("admin") && senha.equals("123");
    }
}
