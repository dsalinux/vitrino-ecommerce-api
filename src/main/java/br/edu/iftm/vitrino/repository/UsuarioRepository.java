package br.edu.iftm.vitrino.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iftm.vitrino.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);
	
}
