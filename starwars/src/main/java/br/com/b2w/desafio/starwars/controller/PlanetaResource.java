package br.com.b2w.desafio.starwars.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.b2w.desafio.starwars.model.Planeta;
import br.com.b2w.desafio.starwars.service.PlanetaService;

@RestController
@RequestMapping("/planetas")
public class PlanetaResource {

	@Autowired
	private PlanetaService planetaService;

	@RequestMapping(value="/adicionar", method=RequestMethod.POST)
	public ResponseEntity<?> inserir(@RequestParam String nome, @RequestParam String clima, @RequestParam String terreno) {
		Planeta planeta = planetaService.inserir(nome, clima, terreno);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/buscarPorId/{id}").buildAndExpand(planeta.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping("/buscarPorNome/{nome}")
	public ResponseEntity<?> obterPorNome(@PathVariable String nome) {
		List<Planeta> planetas = planetaService.obterPorNome(nome);
		
		if(planetas == null || planetas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(planetas);
		}
	}
	
	@RequestMapping("/buscarPorId/{id}")
	public ResponseEntity<?> obterPorId(@PathVariable String id, HttpServletResponse response) {
		Planeta planeta = planetaService.obterPorId(id); 
		
		if(planeta == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(planeta);
		}
	}
	
	@RequestMapping("/listar")
	public List<Planeta> listar(){
		return planetaService.listar();
	}
	
	@RequestMapping(value="/remover/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> remover(@PathVariable String id) {
		try {
			planetaService.excluir(id);
			return ResponseEntity.ok("Planeta removido: "+id);
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	public PlanetaService getPlanetaService() {
		return planetaService;
	}

	public void setPlanetaService(PlanetaService planetaService) {
		this.planetaService = planetaService;
	}
	
}
