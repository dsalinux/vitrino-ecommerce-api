package br.edu.iftm.vitrino.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String nome;

    @Column(length = 45)
    private String sobrenome;

    @Column(length = 60, unique = true, nullable = false)
    private String email;

    @Column(length = 100)
    private String senha;

    @Column(length = 100)
    private String salt;

    @Column(length = 45)
    private String cpf;

    @Column(length = 45)
    private String telefone;

    @Column(length = 45)
    private String whatsapp;

    @Column(name = "data_nascimento")
    private LocalDateTime dataNascimento;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    @Column(name = "data_bloqueio")
    private LocalDateTime dataBloqueio;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("cliente")
    private List<Endereco> enderecos = new ArrayList<>();

}
