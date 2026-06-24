const API_URL = "http://localhost:8080/api";
let produtosCatalogo = [];
let carrinho = {};
let nomeCliente = "";

async function carregarProdutos() {
    try {
        const response = await fetch(`${API_URL}/produtos`);
        produtosCatalogo = await response.json();
        renderizarVitrine();
    } catch (error) {
        alert("Erro ao conectar com o servidor backend.");
    }
}

function iniciarPedido() {
    const input = document.getElementById("nome-cliente");
    nomeCliente = input.value.trim();
    
    if (!nomeCliente) {
        alert("Por favor, preencha seu nome.");
        return;
    }

    document.getElementById("label-cliente").innerText = nomeCliente;
    document.getElementById("tela-identificacao").classList.add("hidden");
    document.getElementById("tela-loja").classList.remove("hidden");
    
    carregarProdutos();
}

function renderizarVitrine() {
    const vitrine = document.getElementById("vitrine");
    vitrine.innerHTML = "";

    produtosCatalogo.forEach(p => {
        const qtd = carrinho[p.id] || 0;
        let infoPromo = p.qtdPromo > 0 ? `Leve ${p.qtdPromo} por R$ ${p.precoPromo.toFixed(2).replace('.', ',')}` : "Preço normal";
        
        const card = document.createElement("div");
        card.className = "produto-card";
        card.innerHTML = `
            <div class="produto-info">
                <h3>${p.nome}</h3>
                <p>R$ ${p.precoUnit.toFixed(2).replace('.', ',')} | ${infoPromo}</p>
            </div>
            <div class="seletor-qtd">
                <button onclick="alterarQtd(${p.id}, -1)">-</button>
                <span id="qtd-${p.id}">${qtd}</span>
                <button onclick="alterarQtd(${p.id}, 1)">+</button>
            </div>
        `;
        vitrine.appendChild(card);
    });
}

function alterarQtd(id, delta) {
    const qtdAtual = carrinho[id] || 0;
    const novaQtd = qtdAtual + delta;

    if (novaQtd <= 0) {
        delete carrinho[id];
    } else if (novaQtd <= 20) {
        carrinho[id] = novaQtd;
    }

    const span = document.getElementById(`qtd-${id}`);
    if (span) span.innerText = carrinho[id] || 0;
}

async function enviarPedido() {
    const itens = Object.keys(carrinho).map(id => ({
        produtoId: parseInt(id),
        quantidade: carrinho[id]
    }));

    if (itens.length === 0) {
        alert("Adicione pelo menos um produto ao carrinho.");
        return;
    }

    const payload = {
        cliente: nomeCliente,
        itens: itens
    };

    try {
        const response = await fetch(`${API_URL}/pedidos`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        const pedidoGerado = await response.json();
        exibirTelaPagamento(pedidoGerado);
    } catch (error) {
        alert("Erro ao processar o pedido no servidor.");
    }
}

function exibirTelaPagamento(pedido) {
    document.getElementById("tela-loja").classList.add("hidden");
    document.getElementById("tela-pagamento").classList.remove("hidden");
    document.getElementById("pedido-horario").innerText = pedido.dataHoraFormatada || new Date().toLocaleString();
    document.getElementById("total-geral-valor").innerText = pedido.total.toFixed(2);

    const containerBlocos = document.getElementById("blocos-pagamento");
    containerBlocos.innerHTML = "";

    const grupos = pedido.itens.reduce((acc, item) => {
        const chave = item.produto.chavePix;
        if (!acc[chave]) acc[chave] = { itens: [], total: 0, categoria: item.produto.categoria };
        acc[chave].itens.push(item);
        acc[chave].total += item.subtotal;
        return acc;
    }, {});

    Object.keys(grupos).forEach(chave => {
        const grupo = grupos[chave];
        const divBloco = document.createElement("div");
        divBloco.className = "bloco-pix";
        
        let linhasItens = grupo.itens.map(item => `
            <div class="item-linha">
                <span>${item.quantidade}x ${item.produto.nome}</span>
                <span>R$ ${item.subtotal.toFixed(2)}</span>
            </div>
        `).join("");

        divBloco.innerHTML = `
            <h4>Destinatário Categoria: ${grupo.categoria}s</h4>
            ${linhasItens}
            <div class="total-bloco">Subtotal: R$ ${grupo.total.toFixed(2)}</div>
            <div class="pix-copia">
                <input type="text" class="pix-input" value="${chave}" readonly>
                <button onclick="copiarPix('${chave}')">Copiar Chave</button>
            </div>
        `;
        containerBlocos.appendChild(divBloco);
    });
}

function copiarPix(chave) {
    navigator.clipboard.writeText(chave);
    alert("Chave Pix copiada para a área de transferência!");
}

function voltarParaIdentificacao() {
    document.getElementById("tela-loja").classList.add("hidden");
    document.getElementById("tela-identificacao").classList.remove("hidden");
}

function voltarParaLoja() {
    document.getElementById("tela-pagamento").classList.add("hidden");
    document.getElementById("tela-loja").classList.remove("hidden");
}

function finalizarTudo() {
    alert("Pedido finalizado com sucesso! Bom apetite.");
    carrinho = {};
    document.getElementById("nome-cliente").value = "";
    document.getElementById("tela-pagamento").classList.add("hidden");
    document.getElementById("tela-identificacao").classList.remove("hidden");
}
