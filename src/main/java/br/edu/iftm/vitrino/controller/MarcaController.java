package br.edu.iftm.vitrino.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.edu.iftm.vitrino.entity.Marca;
import br.edu.iftm.vitrino.repository.MarcaRepository;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/marca")
public class MarcaController {

    private final MarcaRepository marcaRepository;

    public MarcaController(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @PostMapping
    public ResponseEntity<Marca> cadastrarMarca(@RequestBody @Validated Marca marca) {
        Marca novaMarca = marcaRepository.save(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMarca);
    }

    @GetMapping
    public ResponseEntity<List<Marca>> listarMarcas() {
        return ResponseEntity.ok(marcaRepository.findAll());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Marca> buscarPorId(@PathVariable Long id) {
        return marcaRepository.findById(id)
                .map(marcaRepository -> ResponseEntity.ok(marcaRepository))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Marca> atualizarMarca(@PathVariable Long id, @RequestBody @Validated Marca novaMarca) {
        return marcaRepository.findById(id)
                .map(marcaExistente -> {
                    marcaExistente.setNome(novaMarca.getNome());
                    Marca atualizada = marcaRepository.save(marcaExistente);
                    return ResponseEntity.ok(atualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> excluirMarca(@PathVariable Long id) {
        return marcaRepository.findById(id)
                .map(marca -> {
                    marcaRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
