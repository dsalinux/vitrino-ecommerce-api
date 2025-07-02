package br.edu.iftm.vitrino.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.vitrino.entity.FormaPagamento;
import br.edu.iftm.vitrino.repository.FormaPagamentoRepository;

public class FormaPagamentoController {
    @Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	//@GetMapping("/forma_pagamento")
	//public List<FormaPagamento> listarFluxoCaixa() {
	//	return formaPagamentoRepository.findAll();
	//}

    //Pontuar os outros possivel controllers para as Formas de Pagamento
}
