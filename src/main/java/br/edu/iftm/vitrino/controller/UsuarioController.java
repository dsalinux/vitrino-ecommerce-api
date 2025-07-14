package br.edu.iftm.vitrino.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.vitrino.entity.Usuario;
import br.edu.iftm.vitrino.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	//CRUD
	//Create
	@PostMapping
	public void criarUsuario(@RequestBody Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	//Read
	@GetMapping
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}
	//Read
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElse(null);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(usuario);
		}
	}
	//Read
	@GetMapping("/email/{email}")
	public Usuario buscarUsuarioPorEmail(@PathVariable String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	//Update
	@PutMapping("/{id}")
	public void atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		if (usuarioRepository.existsById(id)) {
			usuario.setId(id);
			usuarioRepository.save(usuario);
		}
	}
	
	//Delete
	@DeleteMapping("/{id}")
	public void deletarUsuario(@PathVariable Long id) {
		if (usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
		}
	}
	
}
