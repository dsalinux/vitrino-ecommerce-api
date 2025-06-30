package br.edu.iftm.vitrino.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "marca")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Marca {

    @Id
    private Integer id;

    @Column(name = "marca")
    private String nome;
}
