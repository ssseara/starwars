package br.com.b2w.desafio.starwars.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.b2w.desafio.starwars.model.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String>{

	public Optional<Planeta> findById(Long id);
	public List<Planeta> findAllByNomeIgnoreCase(String nome);
	
}
