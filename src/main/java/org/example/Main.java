package org.example;

import database.ClientesDAO;
import database.ClientesEntity;
import utils.AVLTree;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static ClientesDAO clientesDAO = new ClientesDAO();
    private static AVLTree arvoreAVL = new AVLTree();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        carregarClientesNaAVL();

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Sistema de Cadastro de Clientes ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Buscar Cliente por CPF");
            System.out.println("3. Exibir CPFs em Ordem Crescente");
            System.out.println("4. Exibir Clientes em Ordem Alfabética");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    buscarCliente();
                    break;
                case 3:
                    exibirCpfEmOrdem();
                    break;
                case 4:
                    exibirEmOrdemAlfabetica();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        clientesDAO.getClass();
        System.exit(0);
    }

    private static void carregarClientesNaAVL() {
        List<ClientesEntity> clientes = clientesDAO.findAll();
        for (ClientesEntity cliente : clientes) {
            arvoreAVL.insercao(cliente);
        }
        System.out.println("Clientes carregados na Árvore AVL.");
    }

    private static void cadastrarCliente() {
        System.out.println("\n--- Cadastrar Novo Cliente ---");
        System.out.print("Nome Completo: ");
        String nome = scanner.nextLine();

        System.out.print("Data de Nascimento (dd/mm/aaaa): ");
        String dataNascimento = scanner.nextLine();

        System.out.print("Contato Telefônico: ");
        String telefone = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Cpf: ");
        String cpf = scanner.nextLine();

        ClientesEntity existente = clientesDAO.findByCPF(cpf);
        if (existente != null) {
            System.out.println("Cpf ja existente");
            return;
        }

        ClientesEntity novoCliente = new ClientesEntity(nome, dataNascimento, telefone, email, endereco, cpf);
        clientesDAO.add(novoCliente);
        arvoreAVL.insercao(novoCliente);
        System.out.println("Cliente Cadastrado");
    }

    private static void buscarCliente() {
        System.out.println("\n--- Buscar Cliente por CPF ---");
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();

        ClientesEntity cliente = arvoreAVL.busca(cpf);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void exibirCpfEmOrdem() {
        System.out.println("\n--- CPFs em Ordem Crescente ---");
        arvoreAVL.emOrdem();
    }

    private static void exibirEmOrdemAlfabetica() {
        System.out.println("\n--- Clientes em Ordem Alfabetica ---");
        List<ClientesEntity> clientes = clientesDAO.OrderByName();
        for (ClientesEntity cliente : clientes) {
            System.out.println(cliente);
        }
    }
}