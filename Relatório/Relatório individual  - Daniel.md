# Relatório individual - PARTE 1
Relatório Individual - Daniel Mendonça de Moura Filho - 202503221


## 1. Atribuição de Cargo e Tarefas
  Fui designado como desenvolvedor back-end do sistema, com responsabilidade sobre o módulo de vendas. Minha principal atribuição foi desenvolver a lógica de compra, o registro de vendas e o cálculo dos valores totais, estruturando as classes Venda, ItemVenda e o serviço VendaService.
  Entre as responsabilidades definidas estavam a criação da classe ItemVenda, responsável por representar um produto dentro de uma venda e realizar o cálculo de subtotais; a implementação da classe Venda, encarregada de gerenciar os itens adicionados, calcular o valor total da compra e controlar a finalização da venda; e a construção da classe VendaService, que centraliza as regras de negócio relacionadas ao ciclo de vida do carrinho de compras.
  Na prática, participei da modelagem e implementação completa dessas classes, garantindo que a lógica de negócio permanecesse desacoplada da interface do sistema. Além disso, foram desenvolvidas validações de estoque, controle de estado das vendas e mecanismos para registro do histórico de vendas em memória, utilizando apenas recursos da API padrão do Java.

## 2. Contribuição de Acordo com a Atribuição
###O que foi cumprido

As atividades previstas foram executadas conforme o planejamento da etapa:

  Criação da classe ItemVenda, encapsulando produto e quantidade, com métodos para cálculo de subtotal e exibição formatada dos dados.
  Implementação da classe Venda, responsável por gerenciar uma lista de itens, validar estoque antes da inclusão de produtos, recalcular o valor total da compra, registrar data e hora da finalização e manter um histórico das vendas realizadas durante a execução do sistema.
  Desenvolvimento da classe VendaService, centralizando a lógica de processamento por meio de métodos como abrirCarrinho(), adicionarItem(), removerItem(), exibirCarrinho(), finalizarVenda(), cancelarCarrinho() e exibirHistorico().
  Estruturação do script SQL contendo as tabelas produto, venda e item_venda, visando futura integração com banco de dados.
  
### Commits mais relevantes
* feat: implementa classe ItemVenda com cálculo de subtotal e toString formatado
* feat: implementa classe Venda com controle de estoque, calcularTotal() e finalizarVenda()
* feat: implementa VendaService com ciclo completo do carrinho e histórico de vendas

### O que não foi possível concluir

A integração definitiva com um banco de dados relacional ainda não foi implementada. Atualmente, os dados são armazenados em memória por meio de listas estáticas, servindo como solução temporária até a fase de persistência utilizando JDBC ou outra tecnologia de acesso a banco de dados.

### Principais dificuldades encontradas

Durante o desenvolvimento, algumas dificuldades se destacaram:

Organizar corretamente o fluxo de controle de estoque para que a baixa ocorresse apenas na finalização da venda.
Garantir a separação adequada de responsabilidades entre as classes de modelo e a camada de serviço, evitando acoplamento excessivo.
Aprender e utilizar corretamente as ferramentas Git e GitHub, especialmente no gerenciamento de commits, branches e sincronização com o repositório compartilhado do grupo.

## 3. Contribuição Além do Atribuído
  Além das atividades diretamente relacionadas ao módulo de vendas, contribuí com a elaboração da estrutura SQL do banco de dados, criando o script das tabelas necessárias e definindo seus relacionamentos por meio de chaves estrangeiras.
  Também participei das discussões de modelagem do sistema, analisando a integração entre as classes existentes e identificando funcionalidades já implementadas na classe Produto que poderiam ser reutilizadas, evitando retrabalho e mantendo a consistência do projeto.
  Adicionalmente, auxiliei na documentação do repositório por meio de arquivos Markdown, colaborando na descrição das funcionalidades desenvolvidas e na organização das informações técnicas do projeto.

## 4. Considerações Gerais

  Esta etapa proporcionou um aprofundamento significativo nos conceitos de Programação Orientada a Objetos, especialmente em aspectos como encapsulamento, gerenciamento de coleções, reutilização de código e separação de responsabilidades entre camadas da aplicação.
  Também adquiri experiência prática na utilização de estruturas estáticas para simular persistência em memória e compreendi suas limitações em comparação com soluções baseadas em banco de dados. Além disso, desenvolvi conhecimentos iniciais sobre controle de versão utilizando Git e GitHub.
