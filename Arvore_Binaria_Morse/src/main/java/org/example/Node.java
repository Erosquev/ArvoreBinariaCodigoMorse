package org.example;

// Implementação da Árvore Binária de Código Morse
// Desenvolvido conforme especificações do trabalho

class Node {
    private char letra;
    private Node esquerda; // representa '.'
    private Node direita;  // representa '-'

    public Node(char letra) {
        this.letra = letra;
        this.esquerda = null;
        this.direita = null;
    }

    // Getters e Setters
    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public Node getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Node esquerda) {
        this.esquerda = esquerda;
    }

    public Node getDireita() {
        return direita;
    }

    public void setDireita(Node direita) {
        this.direita = direita;
    }
}



