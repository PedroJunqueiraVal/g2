package model;

public class ItemVenda{

    //atributos
    private Produto produto;
    private int quantidade;

    //construtor
    public ItemVenda(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }

    //subtotal do item 
    public double calcularSubtotal(){
        return produto.getPreco() * quantidade;
    }

    public Produto getProduto(){
        return produto;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return String.format(
            "%-20s | Qtd: %3d | Preço Unit.: R$ %6.2f | Subtotal: R$ %8.2f",
            produto.getNome(), quantidade, produto.getPreco(), calcularSubtotal()
        );
    }

}