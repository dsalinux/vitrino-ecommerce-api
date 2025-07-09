package br.edu.iftm.vitrino.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.iftm.vitrino.repository.ClienteRepository;
import br.edu.iftm.vitrino.entity.Cliente;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public List<Cliente> listarTodos() {
        return repo.findAll();
    }

    public Optional<Cliente> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public Cliente salvar(Cliente cliente) {
        return repo.save(cliente);
    }

    public Cliente atualizar(Integer id, Cliente dto) {
        return repo.findById(id).map(c -> {
            c.setNome(dto.getNome());
            c.setSobrenome(dto.getSobrenome());
            c.setEmail(dto.getEmail());
            // ... demais campos ...
            return repo.save(c);
        }).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public void remover(Integer id) {
        repo.deleteById(id);
    }
}