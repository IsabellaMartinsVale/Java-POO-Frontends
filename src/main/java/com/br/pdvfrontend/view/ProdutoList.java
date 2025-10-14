package com.br.pdvfrontend.view;

import com.br.pdvfrontend.model.Produto;
import com.br.pdvfrontend.service.ProdutoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProdutoList extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton novoButton;
    private JButton atualizarButton;

    public ProdutoList() {
        setTitle("Lista de Produtos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table
        String[] columnNames = {"Nome", "Referência", "Marca", "Categoria", "Fornecedor"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Buttons
        JPanel buttonPanel = new JPanel();
        novoButton = new JButton("Novo Produto");
        atualizarButton = new JButton("Atualizar");
        buttonPanel.add(novoButton);
        buttonPanel.add(atualizarButton);

        // Layout
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Actions
        novoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProdutoForm produtoForm = new ProdutoForm();
                produtoForm.setVisible(true);
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTabela();
            }
        });

        // Initial data load
        atualizarTabela();
    }

    private void atualizarTabela() {
        // Clear existing data
        tableModel.setRowCount(0);

        // Get data from service
        List<Produto> produtos = ProdutoService.getInstance().getProdutos();

        // Populate table
        for (Produto produto : produtos) {
            Object[] rowData = {
                    produto.getNome(),
                    produto.getReferencia(),
                    produto.getMarca(),
                    produto.getCategoria(),
                    produto.getFornecedor()
            };
            tableModel.addRow(rowData);
        }
    }
}
