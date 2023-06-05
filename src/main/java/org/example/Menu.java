package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.InputMismatchException;
import java.io.IOException;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    public static GerenciadorProdutos gerenciadorProdutos = new GerenciadorProdutos();

    public static void main(String[] args) {
        boolean sair = false;
        while (!sair) {
            mostrarMenu();
            try {
                int opcoes = scanner.nextInt();
                scanner.nextLine();
                switch (opcoes) {
                    case 1:
                        adicionarProduto();
                        break;
                    case 2:
                        removerProduto();
                        break;
                    case 3:
                        atualizarProduto();
                        break;
                    case 4:
                        mostrarProduto();
                        break;
                    case 5:
                        ordenarProdutoHeapSort();
                        break;
                    case 6:
                        ordenarProdutoBuscaBinaria();
                        break;
                    case 7:
                        carregarDados();
                        break;
                    case 8:
                        sair = true;
                        break;
                    default:
                        System.out.println("Digite apenas números de 1 a 8");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números de 1 a 8");
                scanner.nextLine();
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println("\n##--Gerenciador de Produtos--##");
        System.out.println("|-------------------------------|");
        System.out.println("| 1 - Adicionar Produto         |");
        System.out.println("| 2 - Remover Produto           |");
        System.out.println("| 3 - Atualizar Produto         |");
        System.out.println("| 4 - Mostrar Produto           |");
        System.out.println("| 5 - Ordenação HeapSort        |");
        System.out.println("| 6 - Ordenação Busca Binária   |");
        System.out.println("| 7 - Ler Arquivo               |");
        System.out.println("| 8 - Sair                      |");
        System.out.println("|------------------------------ |");
        System.out.println("Digite uma opção: ");
    }

    public static void adicionarProduto() {
        System.out.println("Nome do produto a ser adicionado: ");
        String nome = scanner.nextLine();
        System.out.println("Descrição do produto a ser adicionado: ");
        String descricao = scanner.nextLine();
        System.out.println("Preço do produto a ser adicionado: ");
        double preco = scanner.nextDouble();
        System.out.println("Quantidade do produto a ser adicionado: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        Produto produto = new Produto(nome, descricao, preco, quantidade);
        gerenciadorProdutos.adicionarProduto(produto);
    }

    public static void removerProduto() {
        System.out.println("Nome do produto a ser removido: ");
        String nome = scanner.nextLine();
        Produto produto = gerenciadorProdutos.ordenarProdutoBuscaBinaria(nome);
        if (produto != null) {
            gerenciadorProdutos.removerProduto(produto);
        }
    }

    public static void atualizarProduto() {
        System.out.println("Nome do produto a ser atualizado: ");
        String nome = scanner.nextLine();
        Produto produto = gerenciadorProdutos.ordenarProdutoBuscaBinaria(nome);
        if (produto != null) {
            System.out.println("Novo nome do produto: ");
            String novoNome = scanner.nextLine();
            System.out.println("Nova descrição do produto: ");
            String novaDescricao = scanner.nextLine();
            System.out.println("Novo preço do produto: ");
            double novoPreco = scanner.nextDouble();
            System.out.println("Nova quantidade do produto: ");
            int novaQuantidade = scanner.nextInt();
            scanner.nextLine();

            gerenciadorProdutos.atualizarProduto(produto, novoNome, novaDescricao, novoPreco, novaQuantidade);
        }
    }

    public static void mostrarProduto() {
        List<Produto> produtos = gerenciadorProdutos.mostrarProduto();
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    public static void ordenarProdutoHeapSort() {
        List<Produto> produtos = gerenciadorProdutos.mostrarProduto();
        System.out.println("Ordenando com HeapSort: ");
        gerenciadorProdutos.ordenarProdutoHeapSort();
        for (Produto pheap : produtos) {
            System.out.println(pheap);
        }
    }

    public static void ordenarProdutoBuscaBinaria() {
        List<Produto> produtos = gerenciadorProdutos.mostrarProduto();
        System.out.println("Ordenando com Busca Binária: ");
        Collections.sort(produtos, (p1, p2) -> p1.getNome().compareToIgnoreCase(p2.getNome()));
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    public static void carregarDados() {
        System.out.println("Digite o caminho do arquivo: ");
        String caminhoArquivo = scanner.nextLine();
        gerenciadorProdutos.carregarProdutosArquivo(caminhoArquivo);
    }
}