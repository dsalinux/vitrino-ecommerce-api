package br.edu.iftm.vitrino.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.iftm.vitrino.repository.EnderecoRepository;
import br.edu.iftm.vitrino.repository.ClienteRepository;
import br.edu.iftm.vitrino.entity.Endereco;
import br.edu.iftm.vitrino.entity.Cliente;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repo;

    @Autowired
    private ClienteRepository clienteRepo;

    public List<Endereco> listarPorCliente(Integer clienteId) {
        return repo.findByClienteId(clienteId);
    }

    public Optional<Endereco> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public Endereco salvar(Integer clienteId, Endereco endereco) {
        Cliente cliente = clienteRepo.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        endereco.setCliente(cliente);
        return repo.save(endereco);
    }

    public Endereco atualizar(Integer id, Endereco dto) {
        return repo.findById(id).map(e -> {
            e.setDescricao(dto.getDescricao());
            e.setLogradouro(dto.getLogradouro());
            // ... demais campos ...
            return repo.save(e);
        }).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    public void remover(Integer id) {
        repo.deleteById(id);
    }
}
