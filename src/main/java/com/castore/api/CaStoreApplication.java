package com.castore.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class CaStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CaStoreApplication.class, args);
    }
}

class Produto {
    private int id;
    private String nome;
    private String categoria;
    private double precoUnit;
    private double precoPromo;
    private int qtdPromo;
    private String chavePix;

    public Produto(int id, String nome, String categoria, double precoUnit, double precoPromo, int qtdPromo, String chavePix) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.precoUnit = precoUnit;
        this.precoPromo = precoPromo;
        this.qtdPromo = qtdPromo;
        this.chavePix = chavePix;
    }

    public double calcularSubtotal(int qty) {
        if (qty <= 0) return 0;
        if (qtdPromo == 0 || precoPromo == 0) return precoUnit * qty;
        int grupos = qty / qtdPromo;
        int resto = qty % qtdPromo;
        return (grupos * precoPromo) + (resto * precoUnit);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }
    public double getPrecoUnit() { return precoUnit; }
    public double getPrecoPromo() { return precoPromo; }
    public int getQtdPromo() { return qtdPromo; }
    public String getChavePix() { return chavePix; }
}

class ItemPedido {
    private Produto produto;
    private int quantidade;
    private double subtotal;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = produto.calcularSubtotal(quantidade);
    }

    public void adicionarQuantidade(int qty) {
        this.quantidade += qty;
        this.subtotal = produto.calcularSubtotal(this.quantidade);
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getSubtotal() { return subtotal; }
}

class Pedido {
    private String cliente;
    private List<ItemPedido> itens;
    private double total;
    private boolean pago;
    private LocalDateTime dataHora;

    public Pedido() {
        this.itens = new ArrayList<>();
        this.total = 0;
        this.pago = false;
        this.dataHora = LocalDateTime.now();
    }

    public void adicionarItem(Produto produto, int qty) {
        for (ItemPedido item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                total -= item.getSubtotal();
                item.adicionarQuantidade(qty);
                total += item.getSubtotal();
                return;
            }
        }
        ItemPedido novoItem = new ItemPedido(produto, qty);
        itens.add(novoItem);
        total += novoItem.getSubtotal();
    }

    public String getDataHoraFormatada() {
        return this.dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public String getCliente() { return cliente; }
    public List<ItemPedido> getItens() { return itens; }
    public double getTotal() { return total; }
    public boolean isPago() { return pago; }
    public LocalDateTime getDataHora() { return dataHora; }

    public void setCliente(String cliente) { this.cliente = cliente; }
    public void setPago(boolean pago) { this.pago = pago; }
}

class ItemRequest {
    private int produtoId;
    private int quantidade;

    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}

class PedidoRequest {
    private String cliente;
    private List<ItemRequest> itens;

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public List<ItemRequest> getItens() { return itens; }
    public void setItens(List<ItemRequest> itens) { this.itens = itens; }
}

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
class CaStoreController {

    private final List<Produto> catalogo = Arrays.asList(
        new Produto(1,  "Bolo de Chocolate",      "Bolo",    8.00, 14.00, 2, "70719744113"),
        new Produto(2,  "Bolo Ninho c/ Morango",  "Bolo",    8.00, 14.00, 2, "70719744113"),
        new Produto(3,  "Brownie de Chocolate",   "Brownie", 6.00, 10.00, 2, "+5562981813742"),
        new Produto(4,  "Brownie de Ninho",       "Brownie", 6.00, 10.00, 2, "+5562981813742"),
        new Produto(5,  "Coca-Cola Original",     "Bebida",  4.50,  0.00, 0, "eikfilho27@gmail.com"),
        new Produto(6,  "Coca-Cola Zero",         "Bebida",  4.50,  0.00, 0, "eikfilho27@gmail.com"),
        new Produto(7,  "Monster Pipeline Punch", "Bebida", 12.00,  0.00, 0, "eikfilho27@gmail.com"),
        new Produto(8,  "Monster Pacific Punch",  "Bebida", 12.00,  0.00, 0, "eikfilho27@gmail.com"),
        new Produto(9,  "Monster Ultra",          "Bebida", 12.00,  0.00, 0, "eikfilho27@gmail.com"),
        new Produto(10, "Monster Mango Loko",     "Bebida", 12.00,  0.00, 0, "eikfilho27@gmail.com")
    );

    @GetMapping("/produtos")
    public List<Produto> getProdutos() {
        return catalogo;
    }

    @PostMapping("/pedidos")
    public Pedido criarPedido(@RequestBody PedidoRequest request) {
        Pedido pedido = new Pedido();
        pedido.setCliente(request.getCliente());

        for (ItemRequest itemReq : request.getItens()) {
            Produto produto = catalogo.stream()
                .filter(p -> p.getId() == itemReq.getProdutoId())
                .findFirst()
                .orElse(null);

            if (produto != null && itemReq.getQuantidade() > 0) {
                pedido.adicionarItem(produto, itemReq.getQuantidade());
            }
        }
        return pedido;
    }
}
