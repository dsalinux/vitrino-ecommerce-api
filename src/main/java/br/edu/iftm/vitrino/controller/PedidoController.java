package br.edu.iftm.vitrino.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.vitrino.entity.Pedido;
import br.edu.iftm.vitrino.repository.PedidoRepository;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	//CRUD
	//Create
	@PostMapping
	public void criarPedido(@RequestBody Pedido pedido) {
		pedidoRepository.save(pedido);
	}
	
	//Read
	@GetMapping
	public List<Pedido> listarPedidos() {
		return pedidoRepository.findAll();
	}
	//Read
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElse(null);
		if (pedido == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(pedido);
		}
	}
	
	//Update
	@PutMapping("/{id}")
	public void atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
		if (pedidoRepository.existsById(id)) {
			pedido.setId(id);
			pedidoRepository.save(pedido);
		}
	}
	
	//Delete
	@DeleteMapping("/{id}")
	public void deletarPedido(@PathVariable Long id) {
		if (pedidoRepository.existsById(id)) {
			pedidoRepository.deleteById(id);
		}
	}
	
}
