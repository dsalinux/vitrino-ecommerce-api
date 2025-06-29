package br.edu.iftm.vitrino;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.vitrino.entity.FluxoCaixa;
import br.edu.iftm.vitrino.repository.FluxoCaixaRepository;

@RestController
public class FluxoCaixaController {
    @Autowired
	private FluxoCaixaRepository fluxoCaixaRepository;
	
	//@GetMapping("/fluxo_caixa")
	//public List<FluxoCaixa> listarFluxoCaixa() {
	//	return fluxoCaixaRepository.findAll();
	//}
}
