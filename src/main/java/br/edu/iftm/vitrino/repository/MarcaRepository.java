package br.edu.iftm.vitrino.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iftm.vitrino.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
}
