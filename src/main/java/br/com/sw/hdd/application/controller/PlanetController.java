package br.com.sw.hdd.application.controller;

import br.com.sw.hdd.domain.model.Planet;
import br.com.sw.hdd.domain.model.external.PlanetExternal;
import br.com.sw.hdd.domain.service.PlanetService;
import br.com.sw.hdd.infractructure.api.SWApi;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("planets")
public class PlanetController {

    private PlanetService planetService;

    @Cacheable("${caching.name.planets-all}")
    @GetMapping
    public ResponseEntity getAll(){
        List<Planet> planets = planetService.findAll();
        return !planets.isEmpty() ? ResponseEntity.ok(planets) : ResponseEntity.notFound().build();
    }

    @Cacheable("${caching.name.planets-id}")
    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable String id){
        Planet planet = planetService.findById(id);
        return Objects.nonNull(planet) ? ResponseEntity.ok(planet) : ResponseEntity.notFound().build();
    }

    @Cacheable("${caching.name.planets-name}")
    @GetMapping(params = "name")
    public ResponseEntity getByName(String name){
        Planet planet = planetService.findByName(name);
        return Objects.nonNull(planet) ? ResponseEntity.ok(planet) : ResponseEntity.notFound().build();
    }

    @CacheEvict(cacheNames={"${caching.name.planets-all}", "${caching.name.planets-id}",
            "${caching.name.planets-name}"}, allEntries=true)
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid Planet planet){
        return ResponseEntity.ok(planetService.save(planet));
    }

    @CacheEvict(cacheNames={"${caching.name.planets-all}", "${caching.name.planets-id}",
            "${caching.name.planets-name}"}, allEntries=true)
    @PutMapping
    public ResponseEntity update(@RequestBody @Valid Planet planet, @PathVariable String id){
        return ResponseEntity.ok(planetService.update(planet, id));
    }

    @CacheEvict(cacheNames={"${caching.name.planets-all}", "${caching.name.planets-id}",
            "${caching.name.planets-name}"}, allEntries=true)
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable String id){
        planetService.delete(id);
        return ResponseEntity.ok().build();
    }

}
