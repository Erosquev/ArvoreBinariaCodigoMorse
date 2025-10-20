package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Cria e inicializa a árvore binária de código Morse
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        arvore.inicializar();

        int opcao = -1; // variável de controle do menu

        // Loop principal do menu
        while (opcao != 0) {
            // Exibe as opções do menu
            System.out.println("\n========== MENU ==========");
            System.out.println("1 - Buscar caractere (de Letra/Número p/ Código Morse)");
            System.out.println("2 - Traduzir de código Morse p/ Texto");
            System.out.println("3 - Traduzir de texto p/ Código Morse");
            System.out.println("4 - Exibir estrutura da árvore");
            System.out.println("5 - Remover caractere da árvore");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            // Lê a entrada do usuário como string
            String entrada = sc.nextLine();

            // Verifica se o usuário não digitou nada
            if (entrada.length() == 0) {
                System.out.println("Nenhuma opção digitada.");
                continue; // volta ao início do loop
            }

            // Converte o primeiro caractere para número (supondo que seja 0-9)
            char primeiro = entrada.charAt(0);

            // Verifica se a entrada é um número válido
            if (primeiro < '0' || primeiro > '5') {
                System.out.println("Entrada inválida.");
                continue; // volta ao menu
            }

            // Converte o caractere numérico para inteiro
            opcao = primeiro - '0';


            // opcao 1 — Busca código Morse de um caracter
            if (opcao == 1) {
                System.out.print("Digite a letra ou número: ");
                String texto = sc.nextLine();

                // Verifica se o usuário não digitou nada
                if (texto.length() == 0) {
                    System.out.println("Nenhum caractere digitado.");
                    continue;
                }

                // Pega o primeiro caractere digitado e converte para maiúscula
                char caractere = Character.toUpperCase(texto.charAt(0));

                // Busca o código Morse na árvore
                String codigo = arvore.buscarCodigo(caractere);

                // Verifica se o caractere existe na árvore
                if (codigo == null) {
                    System.out.println("Caractere não encontrado na árvore.");
                } else {
                    System.out.println("Código Morse: " + codigo);
                }
            }


            // opcao 2 — Traduz código Morse para texto
            else if (opcao == 2) {
                System.out.println("\nDigite a mensagem em código Morse:");
                String morse = sc.nextLine();

                String traducao = "";   // Armazena o texto traduzido
                String codigoTemp = ""; // Armazena o código Morse de cada letra

                int i = 0;
                while (i < morse.length()) {
                    char c = morse.charAt(i);

                    // Espaço indica fim de um caractere Morse
                    if (c == ' ') {
                        if (codigoTemp.length() > 0) {
                            String letra = String.valueOf(arvore.buscar(codigoTemp));
                            if (letra == null) {
                                traducao = traducao + "?"; // Código inválido
                            } else {
                                traducao = traducao + letra;
                            }
                            codigoTemp = ""; // Limpa código temporário
                        } else {
                            // Espaço duplo entre palavras
                            traducao = traducao + " ";
                        }
                    } else {
                        // Acumula pontos e traços
                        codigoTemp = codigoTemp + c;
                    }
                    i++;
                }

                // Traduz o último caractere, se houver
                if (codigoTemp.length() > 0) {
                    String letra = String.valueOf(arvore.buscar(codigoTemp));
                    if (letra == null) {
                        traducao = traducao + "?";
                    } else {
                        traducao = traducao + letra;
                    }
                }

                System.out.println("Tradução: " + traducao);
            }


            // opcao 3 — Traduz texto para código Morse
            else if (opcao == 3) {
                System.out.println("\nDigite o texto para converter em código Morse:");
                String texto = sc.nextLine();

                String morse = "";

                int i = 0;
                while (i < texto.length()) {
                    char c = Character.toUpperCase(texto.charAt(i));

                    if (c == ' ') {
                        morse = morse + "  "; // Dois espaços entre palavras
                    } else {
                        String codigo = arvore.buscarCodigo(c);
                        if (codigo == null) {
                            morse = morse + "? "; // Caractere inválido
                        } else {
                            morse = morse + codigo + " ";
                        }
                    }
                    i++;
                }

                System.out.println("Código Morse: " + morse);
            }


            // opcao 4 — Exibe estrutura da árvore
            else if (opcao == 4) {
                System.out.println("\nEstrutura da Árvore Morse:");
                arvore.exibir();
            }

            // opcao 5 — Remove caractere da árvore
            else if (opcao == 5) {
                System.out.print("Digite o caractere para remover: ");
                String texto = sc.nextLine();

                // Verifica se o usuário não digitou nada
                if (texto.length() == 0) {
                    System.out.println("Nenhum caractere digitado.");
                    continue;
                }

                char remover = Character.toUpperCase(texto.charAt(0));

                // Busca o código Morse correspondente
                String codigoRemover = arvore.buscarCodigo(remover);

                // Avisa se o caractere não existe
                if (codigoRemover == null) {
                    System.out.println("Caractere não encontrado para remoção.");
                } else {
                    // Remove o caractere da árvore
                    arvore.remover(codigoRemover);
                    System.out.println("Caractere '" + remover + "' removido da árvore.");
                }
            }


            // opcao 0 — Encerra o programa
            else if (opcao == 0) {
                System.out.println("\nEncerrando o programa...");
            }


            // Opção inválida:
            else {
                System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }
}
