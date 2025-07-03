package br.edu.iftm.vitrino.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.vitrino.entity.FormaPagamento;
import br.edu.iftm.vitrino.repository.FormaPagamentoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/forma_pagamento")
public class FormaPagamentoController {
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@GetMapping
	public List<FormaPagamento> listarFluxosCaixa() {
		return formaPagamentoRepository.findAll();
	}

	@PostMapping
	public void criarFormaPagamento(@RequestBody FormaPagamento formaPagamento) {
		formaPagamentoRepository.save(formaPagamento);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FormaPagamento> buscarFormaPagamentoId(@PathVariable Long id) {
		FormaPagamento formaPagamento = formaPagamentoRepository.findById(id).orElse(null);
		if (formaPagamento != null) {
			return ResponseEntity.ok(formaPagamento);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public void atualizarFormaPagamento(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento) {
		if (formaPagamentoRepository.existsById(id)) {
			formaPagamento.setId(id);
			formaPagamentoRepository.save(formaPagamento);
		}
	}

	@DeleteMapping("/{id}")
	public void deletarUsuario(@PathVariable Long id) {
		if (formaPagamentoRepository.existsById(id)) {
			formaPagamentoRepository.deleteById(id);
		}
	}
}
