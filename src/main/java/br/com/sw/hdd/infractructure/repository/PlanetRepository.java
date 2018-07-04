package br.com.sw.hdd.infractructure.repository;

import br.com.sw.hdd.domain.model.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanetRepository extends MongoRepository<Planet, String> {

    Planet findByName(String name);
}
