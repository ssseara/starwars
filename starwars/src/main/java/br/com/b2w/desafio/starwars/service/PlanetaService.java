package br.com.b2w.desafio.starwars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.b2w.desafio.starwars.model.Planeta;
import br.com.b2w.desafio.starwars.repository.PlanetaRepository;

@Service
public class PlanetaService {

	@Autowired
	private PlanetaRepository planetaRepository;
	
	public List<Planeta> listar(){
		return planetaRepository.findAll();
	}
	
	public Planeta obterPorId(String id) {
		Optional<Planeta> planetaOptional = planetaRepository.findById(id);
		if(planetaOptional.isPresent()) {
			return planetaOptional.get();
		}
		return null;
	}
	
	public List<Planeta> obterPorNome(String nome) {
		return planetaRepository.findAllByNomeIgnoreCase(nome);
	}
	
	public Planeta inserir(String nome, String clima, String terreno) {
		return inserir(new Planeta(nome, clima, terreno));
	}
	
	public Planeta inserir(Planeta planeta) {
		return planetaRepository.insert(planeta);
	}
	
	public void excluir(Planeta planeta) {
		planetaRepository.delete(planeta);
	}
	
	public void excluir(String id) {
		planetaRepository.deleteById(id);
	}

	public PlanetaRepository getPlanetaRepository() {
		return planetaRepository;
	}

	public void setPlanetaRepository(PlanetaRepository planetaRepository) {
		this.planetaRepository = planetaRepository;
	}
	
}