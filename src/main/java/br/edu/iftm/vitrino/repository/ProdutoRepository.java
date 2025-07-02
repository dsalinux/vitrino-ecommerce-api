package br.edu.iftm.vitrino.repository;

import java.util.Optional; // Garanta que Optional está importado

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Adicione esta importação para boas práticas

import br.edu.iftm.vitrino.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByNome(String nome);

    Optional<Produto> findById(Long id);

    void deleteById(Long id);

    void delete(Long id);
}