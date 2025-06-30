package br.edu.iftm.vitrino.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iftm.vitrino.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
