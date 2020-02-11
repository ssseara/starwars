package br.com.b2w.desafio.starwars.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.b2w.desafio.starwars.model.Planeta;

@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlanetaRepositoryTest {

	private static final int ITERACOES = 10;
	private static final int INICIO = 0;
	
	private static final String NOME = "Planeta Teste";
	private static final String CLIMA = "Clima Teste";
	private static final String TERRENO = "Terreno Teste";
	
	@Autowired
	private PlanetaRepository planetaRepository;
	
	@Test
	public void teste01Insercao() {
		List<Planeta> planetas = new ArrayList<Planeta>();
		
		for(int i = INICIO; i < ITERACOES; i++) {
			planetas.add(planetaRepository.insert(new Planeta(NOME+i, CLIMA+i, TERRENO+i)));
		}
		
		assertThat(planetas.size()).isEqualTo(ITERACOES);
	}
	
	@Test
	public void teste02Listagem() {
		List<Planeta> planetas = planetaRepository.findAll();
		
		assertThat(planetas.size()).isGreaterThanOrEqualTo(0);
	}
	
	@Test
	public void teste03BuscaPorNome() {
		Planeta planeta = planetaRepository.insert(new Planeta(NOME,CLIMA,TERRENO));
		
		List<Planeta> planetas = planetaRepository.findAllByNomeIgnoreCase(NOME);
		
		assertThat(planetas).isNotEmpty();
		
		planetaRepository.delete(planeta);
	}
	
	@Test
	public void teste04BuscaPorId() {
		List<Planeta> planetas = planetaRepository.findAll();
		Planeta planeta = planetaRepository.findById(planetas.get(0).getId()).get();
		
		assertThat(planeta).isNotNull();
	}
	
	@Test
	public void teste05Exclusao() {
		List<Planeta> planetas = new ArrayList<Planeta>();
		
		for(int i = INICIO; i < ITERACOES; i++) {
			List<Planeta> ps = planetaRepository.findAllByNomeIgnoreCase(NOME+i);
			if(ps != null && ps.size() > 0) {
				planetas.add(ps.get(0));
			}
		}
		
		for(Planeta planeta : planetas) {
			planetaRepository.delete(planeta);
		}
		
		planetas = new ArrayList<Planeta>();
		for(int i = INICIO; i < ITERACOES; i++) {
			List<Planeta> ps = planetaRepository.findAllByNomeIgnoreCase(NOME+i);
			if(ps != null && ps.size() > 0) {
				planetas.add(ps.get(0));
			}
		}
		
		assertThat(planetas.size()).isEqualTo(0);
	}

	public PlanetaRepository getPlanetaRepository() {
		return planetaRepository;
	}

	public void setPlanetaRepository(PlanetaRepository planetaRepository) {
		this.planetaRepository = planetaRepository;
	}
}
