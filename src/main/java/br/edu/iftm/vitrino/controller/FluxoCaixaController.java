package br.edu.iftm.vitrino.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.vitrino.entity.FluxoCaixa;
import br.edu.iftm.vitrino.repository.FluxoCaixaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/fluxo_caixa")
public class FluxoCaixaController {
    @Autowired
	private FluxoCaixaRepository fluxoCaixaRepository;

	// Read
	@GetMapping
	public List<FluxoCaixa> listarFluxoCaixa() {
		return fluxoCaixaRepository.findAll();
	}

	// Read
	@GetMapping("/{id}")
	public ResponseEntity<FluxoCaixa> buscarFaluxoPorId(@RequestParam Long id) {
		FluxoCaixa fluxoCaixa = fluxoCaixaRepository.findById(id).orElse(null);
		if (fluxoCaixa != null) {
			return ResponseEntity.ok(fluxoCaixa);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// Create
	@PostMapping
	public void criarFluxo(@RequestBody FluxoCaixa fluxoCaixa) {
		fluxoCaixaRepository.save(fluxoCaixa);
	}
	
	//Update
	@PutMapping("/{id}")
	public void atualizarFluxo(@RequestBody Long id, @RequestBody FluxoCaixa fluxoCaixa) {
		if (fluxoCaixaRepository.existsById(id)) {
			fluxoCaixa.setId(id);
			fluxoCaixaRepository.save(fluxoCaixa);
		}
	}
}
