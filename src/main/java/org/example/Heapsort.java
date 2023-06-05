package org.example;

import java.util.Collections;
import java.util.List;

public class Heapsort {
    public  void heapsort(List<Produto> produtos) {
        int n = produtos.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(produtos, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            Produto temp = produtos.get(0);
            produtos.set(0, produtos.get(i));
            produtos.set(i, temp);
            heapify(produtos, i, 0);
        }
    }

    public void heapify(List<Produto> produtos, int heapSize, int r) {
        int largo = r;
        int esquerda = 2 * r + 1;
        int direita = 2 * r + 2;

        if (esquerda < heapSize && produtos.get(esquerda).getPreco() > produtos.get(largo).getPreco()) {
            largo = esquerda;
        }

        if (direita < heapSize && produtos.get(direita).getPreco() > produtos.get(largo).getPreco()) {
            largo = direita;
        }

        if (largo != r) {
            Collections.swap(produtos, r, largo);
            heapify(produtos, heapSize, largo);
        }
    }
}