package org.example;

public class ArvoreBinariaMorse {
    private Node raiz;

    // Getter e Setter da raiz
    public Node getRaiz() {
        return raiz;
    }

    public void setRaiz(Node raiz) {
        this.raiz = raiz;
    }

    // Inicializa a árvore
    public void inicializar() {
        setRaiz(new Node(' '));
        construirArvorePadrao();
    }

    // Cria a árvore e insere as letras em suas devidas posições
    private void construirArvorePadrao() {

    // Fiz em listas para facilitar a visualização
        String[] letras = {
                "A .-", "B -...", "C -.-.", "D -..", "E .", "F ..-.", "G --.",
                "H ....", "I ..", "J .---", "K -.-", "L .-..", "M --", "N -.",
                "O ---", "P .--.", "Q --.-", "R .-.", "S ...", "T -",
                "U ..-", "V ...-", "W .--", "X -..-", "Y -.--", "Z --.."
        };
        String[] numeros = {
                "0 -----", "1 .----", "2 ..---", "3 ...--", "4 ....-",
                "5 .....", "6 -....", "7 --...", "8 ---..", "9 ----."
        };



        for (int i = 0; i < letras.length; i++) {
            String[] p = separar(letras[i]);
            inserir(p[1], p[0].charAt(0));
        }
        for (int i = 0; i < numeros.length; i++) {
            String[] p = separar(numeros[i]);
            inserir(p[1], p[0].charAt(0));
        }
    }

    // Separa a letra do seu devido código para inserir ela na árvore na posição correta
    private String[] separar(String s) {
        //inicializo as variaveis como vazias
        String carac = "";
        String codigo = "";
        //ja determino onde o espaço que irá ser identificado para separação se encontra na posição da string
        int espaco = 1;

        carac += s.charAt(0); //estabeleco onde se encontra a letra/numero

        //Percorro o restante da string (a partit do espaço) para armazenar os caracteres do código em sua variável
        for (int i = espaco + 1; i < comprimento(s); i++) codigo += s.charAt(i);
        //gero como resultado um alista como se fosse uma tupla que contém o caracter e seu código separados
        String[] resultado = {carac, codigo };
        return resultado;
    }

//    Calcula o comprimento da string (substitui  o length())
    private int comprimento(String s) {
        int comp = 0;
        try {
            while (true) {
                s.charAt(comp);
                comp++;
            }
        } catch (Exception e) {}
        return comp;
    }

    // Aqui está a lógica de inserção de caractere seguindo o código Morse
    public void inserir(String codigo, char letra) {
        Node atual = getRaiz();
        for (int i = 0; i < comprimento(codigo); i++) {
            // é conferido dígito por dígito: se - p/ direita, se . p/ esquerda
            char simbolo = codigo.charAt(i);
            if (simbolo == '.') {
                if (atual.getEsquerda() == null)
                    atual.setEsquerda(new Node(' '));
                atual = atual.getEsquerda();
            } else if (simbolo == '-') {
                if (atual.getDireita() == null)
                    atual.setDireita(new Node(' '));
                atual = atual.getDireita();
            }
        }
        //Onde parar é inserida a letra
        atual.setLetra(letra);
    }

    // Busca caractere pelo código: ao ler o códito dígito por dígito ele vai seguindo o caminho determinado  pela função inserir
    public char buscar(String codigo) {
        Node atual = getRaiz();
        for (int i = 0; i < comprimento(codigo); i++) {
            char simbolo = codigo.charAt(i);
            if (simbolo == '.') atual = atual.getEsquerda();
            else if (simbolo == '-') atual = atual.getDireita();
            if (atual == null) return '?';
        }
        return atual.getLetra();
    }

    // Busca código dado um caractere: aplica a função recursiva de buscar pelo código a letra inserida
    public String buscarCodigo(char caractere) {
        return buscarCodigoRec(getRaiz(), caractere, "");
    }

    private String buscarCodigoRec(Node atual, char caractere, String caminho) {
        if (atual == null) return "";
        if (atual.getLetra() == caractere) return caminho;

    // aqui faz basicamente a operação inversa do buscar por código, conforme vai procurando a letra requisitda, vai adicionando os passos do caminho (. ou -)
        String esq = buscarCodigoRec(atual.getEsquerda(), caractere, caminho + ".");
        if (comprimento(esq) > 0) return esq;

        String dir = buscarCodigoRec(atual.getDireita(), caractere, caminho + "-");
        return dir;
    }


    // Exibe árvore hierarquicamente pela chaada da função recursiva
    public void exibir() {
        exibirRec(getRaiz(), 0);
    }

    private void exibirRec(Node atual, int nivel) {

        if (atual != null) { // confere se o node não está vazio ja
         // percorre a arvora printando de forma pre ordem
            for (int i = 0; i < nivel; i++) System.out.print("   ");
            System.out.println(atual.getLetra());
            exibirRec(atual.getEsquerda(), nivel + 1);
            exibirRec(atual.getDireita(), nivel + 1);
        }
    }

    // Remove caractere chamando função recursiva
    public void remover(String codigo) {
        removerRec(getRaiz(), codigo, 0);
    }


    private void removerRec(Node atual, String codigo, int indice) {

        // Esse é o caso base
        if (atual == null || indice >= comprimento(codigo)) return;
        /* * Se o nó atual for nulo (caminho inexistente) OU se o índice for maior ou igual ao comprimento do código (todo o caminho foi percorrido),
a recursão é interrompida.*/


        // Obtém o caractere . ou - na posição atual
        char simbolo = codigo.charAt(indice);


        // essa é a condição de remoção
        if (indice == comprimento(codigo) - 1) {
            /*
             * Verifica se o índice aponta para o último caractere.
             * A remoção será aplicada ao filho do nó atual
             */

            if (simbolo == '.' && atual.getEsquerda() != null)
                atual.getEsquerda().setLetra(' ');
                /*
                 * Se o último símbolo for '.' (ponto) e o nó da esquerda existir,
                 * a letra do nó da esquerda é limpa, definida como ' '
                 */

            else if (simbolo == '-' && atual.getDireita() != null)
                atual.getDireita().setLetra(' ');
            /*
             * Se o último símbolo for '-' (ponto) e o nó da esquerda existir,
             * a letra do nó da esquerda é limpa, definida como ' '
             */

            return;}

        // essa condição avança recursivamente para a esquerda
        if (simbolo == '.')
            removerRec(atual.getEsquerda(), codigo, indice + 1);

        // essa condição avança recursivamente para a direita
        else if (simbolo == '-')
            removerRec(atual.getDireita(), codigo, indice + 1);

    }

    // Traduz uma mensagem em código Morse
    public String traduzirMensagem(String mensagem) {
        String resultado = "";
        String codigo = "";
        for (int i = 0; i < comprimento(mensagem); i++) {
            char c = mensagem.charAt(i);
            if (c != ' ') codigo += c;
            else {
                resultado += buscar(codigo);
                codigo = "";
            }
        }
        resultado += buscar(codigo);
        return resultado;
    }
}