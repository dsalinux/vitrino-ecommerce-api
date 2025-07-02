package br.edu.iftm.vitrino.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.vitrino.entity.Categoria;
import br.edu.iftm.vitrino.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaRepository.save(categoria));
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Categoria> alterarCategoria(@RequestBody Categoria novaCategoria) {
        return ResponseEntity.ok(categoriaRepository.save(novaCategoria));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluirCategoria(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
