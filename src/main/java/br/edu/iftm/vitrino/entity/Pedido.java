package br.edu.iftm.vitrino.entity;

import java.security.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private Long cliente_id;
	@Column(name="data_registro")
	private Timestamp dataRegistro;
	private Long endereco_entrega_id;
	@Column(name="data_cancelamento")
	private LocalDateTime dataCancelamento;
	
	

}
