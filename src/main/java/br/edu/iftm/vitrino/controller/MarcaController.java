package br.edu.iftm.vitrino.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.vitrino.entity.Marca;
import br.edu.iftm.vitrino.repository.MarcaRepository;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private final MarcaRepository marcaRepository;

    public MarcaController(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @PostMapping
    public ResponseEntity<Marca> cadastrarMarca(@RequestBody Marca marca) {
        return ResponseEntity.ok(marcaRepository.save(marca));
    }

    @GetMapping
    public ResponseEntity<List<Marca>> listarMarcas() {
        return ResponseEntity.ok(marcaRepository.findAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<Marca> atualizarMarca(@RequestBody Marca novaMarca) {
        return ResponseEntity.ok(marcaRepository.save(novaMarca));
    }

    @DeleteMapping("{id}")
    public void excluirMarca(Long id) {
        marcaRepository.deleteById(id);
    }
}
