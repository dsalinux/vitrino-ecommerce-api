package br.edu.iftm.vitrino.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iftm.vitrino.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
