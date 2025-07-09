package br.edu.iftm.vitrino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.iftm.vitrino.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
