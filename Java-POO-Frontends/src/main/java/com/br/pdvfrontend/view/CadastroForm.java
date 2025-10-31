package com.br.pdvfrontend.view;

import com.br.pdvfrontend.model.Pessoa;
import com.br.pdvfrontend.service.PessoaService;

import javax.swing.*;
import java.awt.*;

public class CadastroForm extends JDialog {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private final PessoaService pessoaService = new PessoaService();

    // Construtor para ser usado quando o formulário é chamado a partir de outra janela (ex: LoginView)
    public CadastroForm(Frame owner) {
        super(owner, "Registrar Nova Conta", true);
        initComponents();
    }

    // Construtor para permitir a execução autônoma do formulário
    public CadastroForm() {
        super((Frame) null, "Registrar Nova Conta", true);
        initComponents();
    }

    private void initComponents() {
        setSize(400, 300);
        setLocationRelativeTo(getOwner());
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        txtNome = new JTextField(20);
        add(txtNome, gbc);

        // CPF
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("CPF:"), gbc);
        gbc.gridx = 1;
        txtCpf = new JTextField(20);
        add(txtCpf, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        add(txtEmail, gbc);

        // Senha
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Senha:"), gbc);
        gbc.gridx = 1;
        txtSenha = new JPasswordField(20);
        add(txtSenha, gbc);

        // Botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelBotoes, gbc);

        // Action Listeners
        btnSalvar.addActionListener(e -> realizarCadastro());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void realizarCadastro() {
        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome(txtNome.getText());
        novaPessoa.setCpf(txtCpf.getText());
        novaPessoa.setEmail(txtEmail.getText());
        novaPessoa.setSenha(new String(txtSenha.getPassword()));

        Pessoa pessoaSalva = pessoaService.salvar(novaPessoa);

        if (pessoaSalva != null) {
            JOptionPane.showMessageDialog(this, "Registro salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar o registro. Verifique os dados e a conexão com o backend.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method para permitir a execução autônoma
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastroForm dialog = new CadastroForm();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        });
    }
}
