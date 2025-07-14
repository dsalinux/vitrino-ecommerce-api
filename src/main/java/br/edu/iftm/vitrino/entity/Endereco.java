package br.edu.iftm.vitrino.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data           
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 60)
    private String descricao;

    @Column(length = 120)
    private String logradouro;

    private Integer numero;

    @Column(length = 60)
    private String complemento;

    @Column(length = 45)
    private String bairro;

    @Column(length = 45)
    private String cidade;

    @Column(length = 45)
    private String uf;

    @Column(length = 8)
    private String cep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("enderecos")
    private Cliente cliente;

}
