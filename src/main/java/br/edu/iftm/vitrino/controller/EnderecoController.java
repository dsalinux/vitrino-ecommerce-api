package br.edu.iftm.vitrino.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import br.edu.iftm.vitrino.service.EnderecoService;
import br.edu.iftm.vitrino.entity.Endereco;
import java.util.List;

@RestController
@RequestMapping("/api/clientes/{clienteId}/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping
    public List<Endereco> listar(@PathVariable Integer clienteId) {
        return service.listarPorCliente(clienteId);
    }

    @GetMapping("/{id}")
    public Endereco buscar(@PathVariable Integer id) {
        return service.buscarPorId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco criar(@PathVariable Integer clienteId, @RequestBody Endereco endereco) {
        return service.salvar(clienteId, endereco);
    }

    @PutMapping("/{id}")
    public Endereco atualizar(@PathVariable Integer id, @RequestBody Endereco endereco) {
        return service.atualizar(id, endereco);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        service.remover(id);
    }
}