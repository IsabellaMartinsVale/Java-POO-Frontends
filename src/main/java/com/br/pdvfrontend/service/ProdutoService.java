package com.br.pdvfrontend.service;

import com.br.pdvfrontend.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    private static ProdutoService instance;
    private final List<Produto> produtos;

    private ProdutoService() {
        produtos = new ArrayList<>();
    }

    public static synchronized ProdutoService getInstance() {
        if (instance == null) {
            instance = new ProdutoService();
        }
        return instance;
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Produto getProduto(int index) {
        if (index >= 0 && index < produtos.size()) {
            return produtos.get(index);
        }
        return null;
    }
}
