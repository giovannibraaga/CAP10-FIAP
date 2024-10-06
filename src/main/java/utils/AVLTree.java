package utils;

import database.ClientesEntity;

import static java.lang.Math.max;

public class AVLTree {

    private AVLNode root;
    private int comparacoes;

    public AVLTree() {
        this.root = null;
    }

    private int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getFB(AVLNode node) {
        if (node == null)
            return 0;

        return height(node.left) - height(node.right);
    }

    private AVLNode rotacaoDireita(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode rotacaoEsquerda(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insercao(ClientesEntity clientes) {
        root = insercaoRescursiva(root, clientes);
    }

    private AVLNode insercaoRescursiva(AVLNode node, ClientesEntity cliente) {
        if (node == null)
            return new AVLNode(cliente);

        if (cliente.getCpf().compareTo(node.cpf) < 0) {
            node.left = insercaoRescursiva(node.left, cliente);
        } else if (cliente.getCpf().compareTo(node.cpf) > 0) {
            node.right = insercaoRescursiva(node.right, cliente);
        } else {
            return node;
        }

        node.height = 1 + max(height(node.left), height(node.right));
        int fb = getFB(node);

//        Esquerda-Esquerda
        if (fb > 1 && cliente.getCpf().compareTo(node.left.cpf) < 0) {
            return rotacaoDireita(node);
        }

//        Direita-Direita
        if (fb < -1 && cliente.getCpf().compareTo(node.right.cpf) > 0) {
            return rotacaoEsquerda(node);
        }

//        Esquerda-Direita
        if (fb > 1 && cliente.getCpf().compareTo(node.left.cpf) > 0) {
            node.left = rotacaoEsquerda(node.left);
            return rotacaoDireita(node);
        }

//        Direita-Esquerda
        if (fb < -1 && cliente.getCpf().compareTo(node.right.cpf) < 0) {
            node.right = rotacaoDireita(node.right);
            return rotacaoEsquerda(node);
        }

        return node;
    }

    public ClientesEntity busca(String cpf) {
        comparacoes = 0;
        AVLNode resultado = buscaRecursiva(root, cpf);
        System.out.println("Número de comparações durante a busca: " + comparacoes);
        return resultado != null ? resultado.clientes : null;
    }

    private AVLNode buscaRecursiva(AVLNode node, String cpf) {
        if (node == null)
            return null;

        comparacoes++;

        if (cpf.equals(node.cpf))
            return node;

        if (cpf.compareTo(node.cpf) < 0) {
            return buscaRecursiva(node.left, cpf);
        } else {
            return buscaRecursiva(node.right, cpf);
        }
    }

    public void emOrdem() {
        emOrdemRecursiva(root);
    }

    private void emOrdemRecursiva(AVLNode node) {
        if (node != null) {
            emOrdemRecursiva(node.left);
            System.out.println(node.clientes);
            emOrdemRecursiva(node.right);
        }
    }

}
