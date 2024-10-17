/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaliacao;

import java.util.Scanner;

/**
 *
 * @author Bruno Klein
 */
public class Avaliacao {

    Scanner entrada = new Scanner(System.in);
    int escolhaMenu = 0;
    String produtos[] = new String[10];
    boolean consertado[] = new boolean[10];
    boolean entregue[] = new boolean[10];
    double precoConserto[] = new double[10];
    int tamanhoProdutos = 0;
    String produtoConsertado = "";
    int posicaoProdutoConsertadoOuEntregue = 0;
    String produtoEntregue = "";

    public static void main(String[] args) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.menuPrincipal();
    }

    public void menuPrincipal() {
        do {
            System.out.println("=== Menu de Opções ===\n"
                    + "1. Cadastrar Produto\n"
                    + "2. Fazer Conserto\n"
                    + "3. Entregar Produto ao Cliente\n"
                    + "4. Exibir Relatório Completo de Produtos\n"
                    + "5. Exibir Relatório de Produtos Ainda não Consertados\n"
                    + "6. Relatório de Produtos Consertados, mas não Entregues\n"
                    + "7. Exibir Faturamento Total da Loja\n"
                    + "8. Sair da Aplicação\n"
                    + "Escolha uma opção (1-8):");
            escolhaMenu = entrada.nextInt();
            switch (escolhaMenu) {

                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    fazerConserto();
                    break;
                case 3:
                    entregarProduto();
                    break;
                case 4:
                    exibirRelatorioCompletoDeProdutos();
                    break;
                case 5:
                    exibirRelatorioDeProdutosNaoConsertados();
                    break;
                case 6:
                    exibirRelatorioDeProdutosNaoEntregues();
                    break;
                case 7:
                    exibirFaturamentoTotal();
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (escolhaMenu != 8);
    }

    public void cadastrarProduto() {
        System.out.println("Digite o nome do produto que quer cadastrar: ");
        produtos[tamanhoProdutos] = entrada.next();
        consertado[tamanhoProdutos] = false;
        entregue[tamanhoProdutos] = false;
        tamanhoProdutos++;
    }

    public void fazerConserto() {
        System.out.println("Digite o nome do produto consertado: ");
        produtoConsertado = entrada.next();

        if (existeProdutoENaoConsertado()) {
            System.out.println("Digite o valor do conserto: ");
            precoConserto[posicaoProdutoConsertadoOuEntregue] = entrada.nextDouble();
            consertado[posicaoProdutoConsertadoOuEntregue] = true;
        } else {
            System.out.println("Esse produto não está cadastrado ou já está consertado");
        }
    }

    public boolean existeProdutoENaoConsertado() {
        for (int i = 0; i < tamanhoProdutos; i++) {
            if (produtos[i].equalsIgnoreCase(produtoConsertado) && !consertado[i]) {
                posicaoProdutoConsertadoOuEntregue = i;
                return true;
            }
        }
        return false;
    }

    public boolean existeProdutoENaoEntregue() {
        for (int i = 0; i < tamanhoProdutos; i++) {
            if (produtos[i].equalsIgnoreCase(produtoEntregue) && !entregue[i] && consertado[i]) {
                posicaoProdutoConsertadoOuEntregue = i;
                return true;
            }
        }
        return false;
    }

    public void entregarProduto() {
        System.out.println("Digite o nome do produto que foi entregue: ");
        produtoEntregue = entrada.next();
        if (existeProdutoENaoEntregue()) {
            entregue[posicaoProdutoConsertadoOuEntregue] = true;
        } else {
            System.out.println("Esse produto não está cadastrado ou já está entregue ou ainda não foi consertado");
        }
    }

    public void exibirRelatorioCompletoDeProdutos() {
        System.out.println(
                formatarSaida("Nome", 30)
                + formatarSaida("Preço Do conserto", 25)
                + formatarSaida("Está consertado", 25)
                + formatarSaida("Está entregue", 25));
        for (int i = 0; i < tamanhoProdutos; i++) {
            System.out.println(formatarSaida(produtos[i], 30)
                    + formatarSaida("R$ " + precoConserto[i], 25)
                    + formatarSaida("" + consertado[i], 25)
                    + formatarSaida("" + entregue[i], 25));
        }
    }

    public void exibirRelatorioDeProdutosNaoConsertados() {
        System.out.println("Produtos não consertados: ");
        for (int i = 0; i < tamanhoProdutos; i++) {
            if (!consertado[i]) {
                System.out.println(produtos[i]);
            }
        }
    }

    public void exibirRelatorioDeProdutosNaoEntregues() {
        System.out.println("Produtos consertados, mas ainda não entregues: ");
        for (int i = 0; i < tamanhoProdutos; i++) {
            if (consertado[i] && !entregue[i]) {
                System.out.println(produtos[i]);
            }
        }
    }

    public void exibirFaturamentoTotal() {
        double faturamento = 0;
        for (int i = 0; i < tamanhoProdutos; i++) {
            faturamento = faturamento + precoConserto[i];
        }
        System.out.println("O faturamento total foi de R$" + faturamento);
    }

    public String formatarSaida(String texto, int tamanho) {
        while (texto.length() < tamanho) {
            texto = " " + texto;
        }
        return texto;
    }
}
