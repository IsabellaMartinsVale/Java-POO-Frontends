package com.br.pdvfrontend.view;

import com.br.pdvfrontend.model.Produto;
import com.br.pdvfrontend.service.ProdutoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProdutoForm extends JFrame {

    private JTextField nomeField;
    private JTextField referenciaField;
    private JTextField marcaField;
    private JTextField categoriaField;
    private JTextField fornecedorField;
    private JButton salvarButton;

    public ProdutoForm() {
        setTitle("Cadastro de Produto");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Referência:"));
        referenciaField = new JTextField();
        panel.add(referenciaField);

        panel.add(new JLabel("Marca:"));
        marcaField = new JTextField();
        panel.add(marcaField);

        panel.add(new JLabel("Categoria:"));
        categoriaField = new JTextField();
        panel.add(categoriaField);

        panel.add(new JLabel("Fornecedor:"));
        fornecedorField = new JTextField();
        panel.add(fornecedorField);

        salvarButton = new JButton("Salvar");
        panel.add(new JLabel()); // empty label for spacing
        panel.add(salvarButton);

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarProduto();
            }
        });

        add(panel);
    }

    private void salvarProduto() {
        String nome = nomeField.getText();
        String referencia = referenciaField.getText();
        String marca = marcaField.getText();
        String categoria = categoriaField.getText();
        String fornecedor = fornecedorField.getText();

        if (nome.isEmpty() || referencia.isEmpty() || marca.isEmpty() || categoria.isEmpty() || fornecedor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Produto novoProduto = new Produto(nome, referencia, marca, categoria, fornecedor);
        ProdutoService.getInstance().addProduto(novoProduto);

        JOptionPane.showMessageDialog(this, "Produto salvo com sucesso!");
        dispose(); // Close the form after saving
    }
}
