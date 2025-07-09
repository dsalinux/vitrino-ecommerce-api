package br.edu.iftm.vitrino.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.edu.iftm.vitrino.entity.Categoria;
import br.edu.iftm.vitrino.repository.CategoriaRepository;

@RestController
@CrossOrigin(origins = "https://trabalhoppw.vercel.app/")
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody @Validated Categoria categoria) {
        Categoria salva = categoriaRepository.save(categoria);
        return ResponseEntity.status(201).body(salva);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @GetMapping("buscar/{id}")
    public ResponseEntity<Categoria> buscarPeloId(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaRepository -> ResponseEntity.ok(categoriaRepository))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Categoria> alterarCategoria(@PathVariable Long id,
            @RequestBody @Validated Categoria novaCategoria) {

        return categoriaRepository.findById(id)
                .map(categoriaExistente -> {
                    categoriaExistente.setNome(novaCategoria.getNome());
                    categoriaExistente.setCategoriaPai(novaCategoria.getCategoriaPai());
                    Categoria atualizada = categoriaRepository.save(categoriaExistente);
                    return ResponseEntity.ok(atualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluirCategoria(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
