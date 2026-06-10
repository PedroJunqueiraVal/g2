import java.util.ArrayList;
import java.util.List;

public class Produto {

    // Atributos
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidadeEstoque;
    private String categoria; // ex: "Laticínios", "Hortifruti", "Bebidas"...

    // Lista estática que armazena todos os produtos cadastrados
    private static List<Produto> listaProdutos = new ArrayList<>();
    private static int contadorId = 1;

    // Construtor
    public Produto(String nome, String descricao, double preco, int quantidadeEstoque, String categoria) {
        this.id = contadorId++;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }

    // ========================
    //        MÉTODOS
    // ========================

    // Adicionar produto à lista
    public static void adicionarProduto(String nome, String descricao, double preco, int quantidadeEstoque, String categoria) {
        Produto novo = new Produto(nome, descricao, preco, quantidadeEstoque, categoria);
        listaProdutos.add(novo);
        System.out.println("✅ Produto \"" + nome + "\" adicionado com sucesso! (ID: " + novo.getId() + ")");
    }

    // Excluir produto pelo ID
    public static void excluirProduto(int id) {
        Produto encontrado = buscarPorId(id);

        if (encontrado != null) {
            listaProdutos.remove(encontrado);
            System.out.println("🗑️ Produto \"" + encontrado.getNome() + "\" excluído com sucesso!");
        } else {
            System.out.println("❌ Produto com ID " + id + " não encontrado.");
        }
    }

    // Editar produto pelo ID
    public static void editarProduto(int id, String novoNome, String novaDescricao, double novoPreco, int novaQuantidade, String novaCategoria) {
        Produto encontrado = buscarPorId(id);

        if (encontrado != null) {
            encontrado.setNome(novoNome);
            encontrado.setDescricao(novaDescricao);
            encontrado.setPreco(novoPreco);
            encontrado.setQuantidadeEstoque(novaQuantidade);
            encontrado.setCategoria(novaCategoria);
            System.out.println("✏️ Produto ID " + id + " atualizado com sucesso!");
        } else {
            System.out.println("❌ Produto com ID " + id + " não encontrado.");
        }
    }

    // Listar todos os produtos
    public static void listarProdutos() {
        if (listaProdutos.isEmpty()) {
            System.out.println("📭 Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n========== 📦 PRODUTOS CADASTRADOS ==========");
        for (Produto p : listaProdutos) {
            System.out.println(p);
        }
        System.out.println("=============================================\n");
    }

    // Método auxiliar — busca produto por ID
    private static Produto buscarPorId(int id) {
        for (Produto p : listaProdutos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // ========================
    //    toString (exibição)
    // ========================
    @Override
    public String toString() {
        return String.format(
            "ID: %d | Nome: %-20s | Preço: R$ %6.2f | Estoque: %3d | Categoria: %s | Descrição: %s",
            id, nome, preco, quantidadeEstoque, categoria, descricao
        );
    }

    // ========================
    //      GETTERS/SETTERS
    // ========================
    public int getId()                          { return id; }
    public String getNome()                     { return nome; }
    public void setNome(String nome)            { this.nome = nome; }
    public String getDescricao()                { return descricao; }
    public void setDescricao(String descricao)  { this.descricao = descricao; }
    public double getPreco()                    { return preco; }
    public void setPreco(double preco)          { this.preco = preco; }
    public int getQuantidadeEstoque()                       { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
    public String getCategoria()                { return categoria; }
    public void setCategoria(String categoria)  { this.categoria = categoria; }
}
