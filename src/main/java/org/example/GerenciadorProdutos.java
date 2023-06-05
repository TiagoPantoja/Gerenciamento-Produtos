package org.example;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;

public class GerenciadorProdutos {

    public List<Produto> produtos;

    public List<Produto> getProdutos() {
        return produtos;
    }
    public GerenciadorProdutos() {
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
        System.out.println("Produto adicionado com sucesso!");
    }

    public void removerProduto(Produto produto) {
        this.produtos.remove(produto);
        System.out.println("Produto removido com sucesso!");
    }

    public void atualizarProduto(Produto produto, String nome, String descricao, double preco, int quantidade) {
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setQuantidade(quantidade);
        System.out.println("Produto atualizado com sucesso!");
    }

    public List<Produto> mostrarProduto() {
        System.out.println("Mostrando todos os produtos: ");
        return this.produtos;
    }

    public void ordenarProdutoHeapSort() {
        Heapsort heap = new Heapsort();
        heap.heapsort(getProdutos());
        System.out.println("Produtos ordenados com sucesso!");
    }

    public Produto ordenarProdutoBuscaBinaria(String nome) {
        int index = buscaBinariaNome(nome, 0, produtos.size() - 1);
        if (index == -1) {
            return produtos.get(index);
        }
        return null;
    }

    public int buscaBinariaNome(String nome, int comeco, int fim) {
        if (comeco <= fim) {
            int meio = (comeco + fim) / 2;
            String nomeMeio = produtos.get(meio).getNome();
            int comparacao = nome.compareTo(nomeMeio);
            if (comparacao == 0) {
                return meio;
            } else if (comparacao < 0) {
                return buscaBinariaNome(nome, comeco, meio - 1);
            } else {
                return buscaBinariaNome(nome, meio + 1, fim);
            }
        }
        return -1;
    }

    public void carregarProdutosArquivo(String caminhoArquivo) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                String nome = dados[0];
                String descricao = dados[1];
                double preco = Double.parseDouble(dados[2]);
                int quantidade = Integer.parseInt(dados[3]);
                Produto produto = new Produto(nome, descricao, preco, quantidade);
                produtos.add(produto);
            }
            reader.close();
            System.out.println("Produtos carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar produtos" + e.getMessage());
        }
    }
}
