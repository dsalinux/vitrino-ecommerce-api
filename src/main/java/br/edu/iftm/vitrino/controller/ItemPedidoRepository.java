package br.edu.iftm.vitrino.controller;



import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iftm.vitrino.entity.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{

    static ItemPedido findByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }


}
