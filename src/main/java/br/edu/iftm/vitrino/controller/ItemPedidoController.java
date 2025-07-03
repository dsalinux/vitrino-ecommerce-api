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

import br.edu.iftm.vitrino.entity.ItemPedido;


@RestController
@RequestMapping("/itempedidos")
public class ItemPedidoController{

	@Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @PostMapping
    public void criarItemPedido(@RequestBody ItemPedido itemPedido) {
        itemPedidoRepository.save(itemPedido);
    }

    @GetMapping
    public List<ItemPedido> listarItemPedidos() {
        return itemPedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> buscarItemPedidoPorId(@PathVariable Long id) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id).orElse(null);
        if (itemPedido == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(itemPedido);
        }
    }

  //Read
  	@GetMapping("/email/{email}")
  	public ItemPedido buscarItemPedidoPorEmail(@PathVariable String email) {
  		return ItemPedidoRepository.findByEmail(email);
  	}
  	
  	//Update
  	@PutMapping("/{id}")
  	public void atualizarItemPedido(@PathVariable Long id, @RequestBody ItemPedido itemPedido) {
  		if (itemPedidoRepository.existsById(id)) {
  			itemPedido.setId(id);
  			itemPedidoRepository.save(itemPedido);
  		}
  	}
    
    // Delete
    @DeleteMapping("/{id}")
    public void deletarItemPedido(@PathVariable Long id) {
        if (itemPedidoRepository.existsById(id)) {
            itemPedidoRepository.deleteById(id);
        }
    }
}
