package br.edu.iftm.vitrino.controller;

import java.util.List;

import org.springframework.http.HttpStatus; // Para usar HttpStatus.CREATED
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Produto> alterarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        return ResponseEntity.ok(produtoRepository.save(produtoAtualizado));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}