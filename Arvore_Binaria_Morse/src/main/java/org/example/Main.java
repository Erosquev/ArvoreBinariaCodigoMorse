package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Cria e inicializa a árvore binária de código Morse
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        arvore.inicializar();

        int opcao = -1; // variável de controle do menu principal

        // Loop principal do menu faz continuar até o usuário escolher "0"
        while (opcao != 0) {
            // Exibe o menu de opções
            System.out.println("\n========== MENU ==========");
            System.out.println("1 - Buscar caractere (de Letra/Número para Código Morse)");
            System.out.println("2 - Traduzir código Morse para Texto");
            System.out.println("3 - Traduzir texto para Código Morse ");
            System.out.println("4 - Exibir estrutura da árvore");
            System.out.println("5 - Remover caractere da árvore");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                // Tenta ler o número digitado pelo usuário e converter para inteiro
                opcao = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                // Caso o usuário digite algo que não seja número, trata o erro
                System.out.println("Entrada inváida.");
                continue; // volta ao início do loop
            }

            try {
                // opcao 1 — busca codigo morse de um caractere
                if (opcao == 1) {
                    System.out.print("Digite a letra ou número: ");
                    // Lê o primeiro caractere digitado e converte para maiúscula
                    char caractere = Character.toUpperCase(sc.nextLine().charAt(0));

                    try {
                        // Busca o código Morse correspondente ao caractere na árvore
                        String codigo = arvore.buscarCodigo(caractere);
                        System.out.println("Código Morse: " + codigo);
                    } catch (Exception e) {
                        // Caso o caractere não exista na árvore, printa:
                        System.out.println("Caractere não encontrado na árvore.");
                    }
                }


                // opcao 2 — traduz de código morse para texto
                else if (opcao == 2) {
                    System.out.println("\nDigite a mensagem em código Morse:");
                    String morse = sc.nextLine(); // lê a mensagem completa
                    String traducao = "";          // armazena o texto traduzido
                    String codigoTemp = "";        // armazena cada letra em Morse

                    try {
                        // Percorre cada caractere digitado
                        for (int i = 0; i < morse.length(); i++) {
                            char c = morse.charAt(i);

                            // Quando encontra um espaço, tenta decodificar o código acumulado
                            if (c == ' ') {
                                if (codigoTemp != "") { // há código acumulado
                                    try {
                                        traducao = traducao + arvore.buscar(codigoTemp);
                                    } catch (Exception e) {
                                        // Caso o código não exista na árvore
                                        traducao = traducao + "?";
                                    }
                                    codigoTemp = ""; // limpa o código temporário
                                } else {
                                    // Espaço duplo entre palavras
                                    traducao = traducao + " ";
                                }
                            } else {
                                // Adiciona o símbolo atual (. ou -) ao código temporário
                                codigoTemp = codigoTemp + c;
                            }
                        }

                        // Tradução da última letra (após o fim da string)
                        if (codigoTemp != "") {
                            try {
                                traducao = traducao + arvore.buscar(codigoTemp);
                            } catch (Exception e) {
                                traducao = traducao + "?";
                            }
                        }

                        System.out.println("Tradução: " + traducao);
                    } catch (Exception e) {
                        // Caso ocorra erro geral durante a tradução
                        System.out.println("Erro na tradução: " + e.getMessage());
                    }
                }


                // opcao 3 — traduz de texto p/ codigo morse
                else if (opcao == 3) {
                    System.out.println("\nDigite o texto para converter em código Morse:");
                    String texto = sc.nextLine();
                    String morse = "";

                    try {
                        // Percorre cada caractere do texto digitado
                        for (int i = 0; i < texto.length(); i++) {
                            char c = Character.toUpperCase(texto.charAt(i));

                            // Quando há espaço no texto, adiciona espaçamento duplo no Morse
                            if (c == ' ') {
                                morse = morse + "  ";
                            } else {
                                try {
                                    // Busca o código Morse da letra e adiciona um espaço ao final
                                    morse = morse + arvore.buscarCodigo(c) + " ";
                                } catch (Exception e) {
                                    // Caso a letra não exista na árvore
                                    morse = morse + "? ";
                                }
                            }
                        }
                        System.out.println("Código Morse: " + morse);
                    } catch (Exception e) {
                        System.out.println("Erro ao converter texto.");
                    }
                }


                // opcao 4 — exibe toda a árvore
                else if (opcao == 4) {
                    System.out.println("\nEstrutura da Árvore Morse:");
                    // Exibe de forma hierárquica a estrutura da árvore binária
                    arvore.exibir();
                }


                // opcao 5 — remove um caracter da arvore

                else if (opcao == 5) {
                    System.out.print("Digite o caractere para remover: ");
                    char remover = Character.toUpperCase(sc.nextLine().charAt(0));

                    try {
                        // Busca o caminho do caractere a remover
                        String codigoRemover = arvore.buscarCodigo(remover);

                        // Remove o nó correspondente, mas mantém o restante da estrutura
                        arvore.remover(codigoRemover);

                        System.out.println("Caractere '" + remover + "' removido da árvore.");
                    } catch (Exception e) {
                        System.out.println("Caractere não encontrado para remoção.");
                    }
                }


                // opcao 0 — finaliza o programa
                else if (opcao == 0) {
                    System.out.println("\nEncerrando o programa... ");
                }


                // qualquer outra entrada é opção invalida:

                else {
                    System.out.println("Opção inválida.");
                }

            } catch (StringIndexOutOfBoundsException e) {
                // Caso o usuário apenas pressione "Enter" sem digitar nada
                System.out.println("Nenhum caractere foi digitado.");
            } catch (Exception e) {
                // Qualquer outro erro inesperado
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }

        sc.close();
    }
}
