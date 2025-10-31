package com.br.pdvfrontend.view;

import com.br.pdvfrontend.model.Pessoa;
import com.br.pdvfrontend.service.PessoaService;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private final JTextField txtEmail;
    private final JPasswordField txtSenha;
    private final PessoaService pessoaService = new PessoaService();

    public LoginView() {
        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Rótulo e campo de Email
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtEmail = new JTextField(20);
        add(txtEmail, gbc);

        // Rótulo e campo de Senha
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtSenha = new JPasswordField(20);
        add(txtSenha, gbc);

        // Painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnLogin = new JButton("Login");
        JButton btnRegistrar = new JButton("Registrar");
        panelBotoes.add(btnLogin);
        panelBotoes.add(btnRegistrar);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(panelBotoes, gbc);

        // Action Listeners
        btnLogin.addActionListener(e -> realizarLogin());

        btnRegistrar.addActionListener(e -> {
            // Abre a tela de registro
            new CadastroForm(LoginView.this).setVisible(true);
        });
    }

    private void realizarLogin() {
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());
        Pessoa pessoaLogada = pessoaService.login(email, senha);

        if (pessoaLogada != null) {
            // Se o login for bem-sucedido, abre a tela principal e fecha a de login.
            SwingUtilities.invokeLater(() -> {
                new PessoaList().setVisible(true);
                dispose();
            });
        } else {
            // Caso contrário, exibe uma mensagem de erro.
            JOptionPane.showMessageDialog(this, "Email ou senha inválidos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
