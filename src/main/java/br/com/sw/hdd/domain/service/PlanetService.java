package br.com.sw.hdd.domain.service;

import br.com.sw.hdd.domain.model.Planet;
import br.com.sw.hdd.domain.model.external.PlanetExternal;
import br.com.sw.hdd.infractructure.api.SWApi;
import br.com.sw.hdd.infractructure.exception.PlanetNotFoundException;
import br.com.sw.hdd.infractructure.repository.PlanetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class PlanetService {

    private PlanetRepository planetRepository;
    private SWApi swApi;

    public List<Planet> findAll() {
        List<Planet> planets = planetRepository.findAll();
        planets.stream().forEach(this::loadNumberOfApparitionsIfNonNull);
        return planets;
    }

    public Planet findByName(String name){
        Planet planet = planetRepository.findByName(name);
        loadNumberOfApparitionsIfNonNull(planet);

        return planet;
    }

    public Planet findById(String id){
        Planet planet = planetRepository.findOne(id);
        loadNumberOfApparitionsIfNonNull(planet);

        return planet;
    }

    public Planet save(Planet planet){
        Planet save = planetRepository.save(planet);
        loadNumberOfApparitionsIfNonNull(save);
        return save;
    }

    public Planet update(Planet planet, String id){
        Planet currentPlanet = planetRepository.findOne(id);

        if(Objects.isNull(currentPlanet))
            throw new PlanetNotFoundException();

        planet.setId(id);
        Planet update = planetRepository.save(planet);
        loadNumberOfApparitionsIfNonNull(update);
        return update;
    }

    public void delete(String id) {
        planetRepository.delete(id);
    }

    private void loadNumberOfApparitionsIfNonNull(Planet planet){
        if(Objects.nonNull(planet)){
            PlanetExternal planetBySearch = swApi.findPlanetBySearch(planet.getName());
            planet.setNumberOfApparitions(planetBySearch.numberOfApparitions());
        }
    }
}
