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

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(produtoEncontrado -> ResponseEntity.ok(produtoEncontrado))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Produto> alterarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        return produtoRepository.findById(id)
                .map(produtoExistente -> {
                    produtoExistente.setNome(produtoAtualizado.getNome());
                    produtoExistente.setDescricao(produtoAtualizado.getDescricao());
                    produtoExistente.setDetalhes(produtoAtualizado.getDetalhes());
                    produtoExistente.setValor(produtoAtualizado.getValor());
                    produtoExistente.setQuantidadeEmEstoque(produtoAtualizado.getQuantidadeEmEstoque());
                    produtoExistente.setCategoria(produtoAtualizado.getCategoria());
                    produtoExistente.setMarca(produtoAtualizado.getMarca());
                    Produto produtoSalvo = produtoRepository.save(produtoExistente);
                    return ResponseEntity.ok(produtoSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produtoRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}