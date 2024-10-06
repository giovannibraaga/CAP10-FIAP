package utils;

import database.ClientesEntity;

public class AVLNode {
    String cpf;
    ClientesEntity clientes;
    int height;
    AVLNode left;
    AVLNode right;

    public AVLNode(ClientesEntity clientes) {
        this.cpf = clientes.getCpf();
        this.clientes = clientes;
        this.height = 1;
    }
}
