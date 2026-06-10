package model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venda {

    //atributos
    private int id;
    private List<ItemVenda> itens;
    private double valorTotal;
    private boolean finalizada;
    private LocalDateTime dataHora;

    //lista estatica para armazenar todas as vendas (registro de vendas)
    private static List<Venda> listaVendas = new ArrayList<>();
    private static int contadorId = 1;

    //construtor
    public Venda() {
        this.id = contadorId++;
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
        this.finalizada = false;
        this.dataHora = null;
    }


    //adiciona um item a venda
    public boolean adicionarItem(Produto produto, int quantidade) {
        if (finalizada) {
            System.out.println("Não é possível adicionar itens a uma venda já finalizada.");
            return false;
        }

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return false;
        }

        if (quantidade > produto.getQuantidadeEstoque()) {
            System.out.println("Estoque insuficiente para o produto \"" + produto.getNome() + "\".");
            return false;
        }

        ItemVenda item = new ItemVenda(produto, quantidade);
        itens.add(item);
        calcularTotal();
        System.out.println("Item \"" + produto.getNome() + "\" (x" + quantidade + ") adicionado ao carrinho.");
        return true;
    }

    //remove um item do carrinho pelo índice
    public boolean removerItem(int index) {
        if (finalizada) {
            System.out.println("Não é possível remover itens de uma venda já finalizada.");
            return false;
        }

        if (index < 0 || index >= itens.size()) {
            System.out.println("Item não encontrado no carrinho.");
            return false;
        }

        ItemVenda removido = itens.remove(index);
        calcularTotal();
        System.out.println("Item \"" + removido.getProduto().getNome() + "\" removido do carrinho.");
        return true;
    }

    //recalcula o valor total somando os subtotais de cada item
    public double calcularTotal() {
        double total = 0.0;
        for (ItemVenda item : itens) {
            total += item.calcularSubtotal();
        }
        this.valorTotal = total;
        return this.valorTotal;
    }

    //finaliza a venda: da baixa no estoque, registra a venda e marca a data
    public boolean finalizarVenda() {
        if (finalizada) {
            System.out.println("Esta venda já foi finalizada.");
            return false;
        }

        if (itens.isEmpty()) {
            System.out.println("Não é possível finalizar uma venda sem itens.");
            return false;
        }

        //da baixa no estoque de cada produto
        for (ItemVenda item : itens) {
            Produto p = item.getProduto();
            int novoEstoque = p.getQuantidadeEstoque() - item.getQuantidade();
            p.setQuantidadeEstoque(novoEstoque);
        }

        this.finalizada = true;
        this.dataHora = LocalDateTime.now();
        calcularTotal();

        listaVendas.add(this);

        System.out.println("Venda #" + id + " finalizada com sucesso! Total: R$ " + String.format("%.2f", valorTotal));
        return true;
    }

    // Lista todas as vendas finalizadas (registro de vendas)
    public static void listarVendas() {
        if (listaVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        System.out.println("\n========== REGISTRO DE VENDAS ==========");
        for (Venda v : listaVendas) {
            System.out.println(v);
        }
        System.out.println("============================================\n");
    }


    public int getId() {
        return id;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public static List<Venda> getListaVendas() {
        return listaVendas;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String dataFormatada = (dataHora != null)
                ? dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                : "-";

        sb.append(String.format("Venda #%d | Status: %s | Data: %s | Total: R$ %.2f\n",
                id, finalizada ? "Finalizada" : "Em aberto", dataFormatada, valorTotal));

        for (ItemVenda item : itens) {
            sb.append("   - ").append(item).append("\n");
        }

        return sb.toString();
    }
}