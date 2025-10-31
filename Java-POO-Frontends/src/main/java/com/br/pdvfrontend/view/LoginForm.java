package com.br.pdvfrontend.view;

import com.br.pdvfrontend.model.Pessoa;
import com.br.pdvfrontend.service.PessoaService;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private PessoaService pessoaService = new PessoaService();

    public LoginForm() {
        setTitle("PDV - Login");
        setSize(400, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel central com os campos
        JPanel painelCampos = new JPanel(new GridLayout(2, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        painelCampos.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        painelCampos.add(txtEmail);

        painelCampos.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        painelCampos.add(txtSenha);

        add(painelCampos, BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new GridLayout(2, 1, 5, 5));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        // Primeira linha de botões
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnEntrar = new JButton("Entrar");
        JButton btnCancelar = new JButton("Cancelar");
        linha1.add(btnEntrar);
        linha1.add(btnCancelar);

        // Segunda linha - botão cadastrar
        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnCadastrar = new JButton("Não tem conta? Cadastre-se");
        linha2.add(btnCadastrar);

        painelBotoes.add(linha1);
        painelBotoes.add(linha2);

        btnEntrar.addActionListener(e -> realizarLogin());
        btnCancelar.addActionListener(e -> System.exit(0));
        btnCadastrar.addActionListener(e -> new CadastroForm());

        // Enter no campo de senha também faz login
        txtSenha.addActionListener(e -> realizarLogin());

        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void realizarLogin() {
        String email = txtEmail.getText().trim();
        String senha = new String(txtSenha.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha email e senha.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Tenta fazer login
        Pessoa pessoa = pessoaService.login(email, senha);

        if (pessoa != null) {
            JOptionPane.showMessageDialog(this,
                    "Bem-vindo(a), " + pessoa.getNome() + "!",
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE);

            // Fecha a tela de login
            dispose();

            // Abre o MainApp
            SwingUtilities.invokeLater(() -> MainApp.createAndShowGUI());
        } else {
            JOptionPane.showMessageDialog(this,
                    "Email ou senha inválidos!",
                    "Erro de Login",
                    JOptionPane.ERROR_MESSAGE);
            txtSenha.setText("");
            txtEmail.requestFocus();
        }
    }
}