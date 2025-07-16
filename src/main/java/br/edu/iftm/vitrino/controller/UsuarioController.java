package br.edu.iftm.vitrino.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
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
	public ResponseEntity<Void> criarUsuario(@RequestBody Usuario usuario) {
		if(usuario.getDataRegistro() == null) {
			usuario.setDataRegistro(LocalDateTime.now());
		}
		if(usuario.getSalt() == null) {
			SecureRandom secureRandom = new SecureRandom();
			byte[] bytes = new byte[16];
			secureRandom.nextBytes(bytes);
			usuario.setSalt(bytes.toString());
			String senha = usuario.getSenha();
			senha += usuario.getSalt();
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return ResponseEntity.status(500).build();
			}
			byte[] hash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
			usuario.setSenha(new String(hash));
		}
		
		usuarioRepository.save(usuario);
		return ResponseEntity.ok().build();
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
