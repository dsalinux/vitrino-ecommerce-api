package br.edu.iftm.vitrino.entity;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "fluxo_caixa")
@Data
public class FluxoCaixa {
    //Criar a estrutura da tabela, semelhante a de 'Usuario' e 'Permissao'
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;
    private BigDecimal valor;
    @Column(name = "tipo_transacao")
    private String tipoTransacao;
    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id")
    private FormaPagamento formaPagamento;
}
