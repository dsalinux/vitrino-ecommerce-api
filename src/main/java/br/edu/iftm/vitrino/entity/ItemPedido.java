package br.edu.iftm.vitrino.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long cliente_id;
	private Long produto_id;
	private Float valor;
	private Float quantidade;
	

}
