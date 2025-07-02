package br.edu.iftm.vitrino.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    private Long id;

    private String nome;

    private String descricao;

    @Column(name = "destalhes", columnDefinition = "LONGTEXT")
    private String detalhes;

    @Column(name = "valor", precision = 9, scale = 2)
    private BigDecimal valor;

    @Column(name = "quantidade_em_estoque")
    private Float quantidadeEmEstoque;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;
}
