package br.edu.iftm.vitrino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import br.edu.iftm.vitrino.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    List<Endereco> findByClienteId(Integer clienteId);
}