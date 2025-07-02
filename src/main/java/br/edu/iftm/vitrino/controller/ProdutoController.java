package br.edu.iftm.vitrino.controller;

import java.util.List;
import java.util.Optional; // Importe Optional

import org.springframework.http.HttpStatus; // Para usar HttpStatus.CREATED
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.vitrino.entity.Produto;
import br.edu.iftm.vitrino.repository.ProdutoRepository; // Continue usando o repositório

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository; // Injetamos o repositório diretamente

    // Injeção de dependência via construtor
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto); // 201 Created para criação
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        // Correção aqui: findById retorna Optional, então usamos .map e .orElse
        return produtoRepository.findById(id)
                .map(produto -> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build()); // Retorna 404 se não encontrar
    }

    @GetMapping("/buscar/nome")
    public ResponseEntity<Produto> buscarProdutosPorNome(@RequestParam("nome") String nome) {
        // Correção aqui: findByNome agora retorna Optional, então usamos .map e .orElse
        return produtoRepository.findByNome(nome)
                .map(produto -> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build()); // Retorna 404 se não encontrar
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Produto> alterarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            // Atualize os campos do produto existente com os dados de produtoAtualizado
            produto.setNome(produtoAtualizado.getNome());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setValor(produtoAtualizado.getValor());
            // Adicione aqui todos os outros campos que você deseja permitir atualização

            Produto produtoSalvo = produtoRepository.save(produto); // Salva o produto atualizado
            return ResponseEntity.ok(produtoSalvo);
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 se o produto não for encontrado
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        // Verifica se o produto existe antes de tentar deletar
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id); // Usa o método deleteById da JpaRepository
            return ResponseEntity.noContent().build(); // 204 No Content para deleção bem-sucedida
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found se o produto não existir
        }
    }

    @DeleteMapping("/deletar/nome")
    public ResponseEntity<Void> excluirProdutoPorNome(@RequestParam String nome) {
        Optional<Produto> produto = produtoRepository.findByNome(nome); // Busca o produto pelo nome

        if (produto.isPresent()) {
            produtoRepository.delete(produto.get()); // Se encontrado, deleta a entidade
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/alterar/nome")
    public ResponseEntity<Produto> alterarProdutoPorNome(@RequestParam String nome, @RequestBody Produto dadosNovos) {
        Optional<Produto> produtoExistente = produtoRepository.findByNome(nome); // Busca o produto pelo nome

        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            // Atualize os campos do produto existente com os dados de dadosNovos
            produto.setNome(dadosNovos.getNome());
            produto.setDescricao(dadosNovos.getDescricao());
            produto.setValor(dadosNovos.getValor());
            // Adicione aqui todos os outros campos que você deseja permitir atualização

            Produto produtoSalvo = produtoRepository.save(produto); // Salva o produto atualizado
            return ResponseEntity.ok(produtoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}