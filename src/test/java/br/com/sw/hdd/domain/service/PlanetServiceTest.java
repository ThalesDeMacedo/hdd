package br.com.sw.hdd.domain.service;

import br.com.sw.hdd.domain.model.Planet;
import br.com.sw.hdd.infractructure.api.SWApi;
import br.com.sw.hdd.infractructure.exception.PlanetNotFoundException;
import br.com.sw.hdd.infractructure.repository.PlanetRepository;
import br.com.sw.hdd.infrastructure.util.PlanetUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlanetServiceTest {

    @Mock
    private PlanetRepository planetRepository;

    @Mock
    private SWApi swApi;

    private PlanetService planetService;

    @Before
    public void setUp(){
        planetService = new PlanetService(planetRepository, swApi);
        doReturn(PlanetUtil.anyPlanetExternal()).when(swApi).findPlanetBySearch(anyString());
    }

    @Test
    public void testFindAllWithResult(){
        doReturn(PlanetUtil.loadListPlanets()).when(planetRepository).findAll();

        List<Planet> planets = planetService.findAll();

        Assert.assertEquals(PlanetUtil.loadListPlanets(), planets);
    }

    @Test
    public void testFindAllWithoutResult(){
        doReturn(new ArrayList<>()).when(planetRepository).findAll();

        List<Planet> planets = planetService.findAll();

        Assert.assertTrue(planets.isEmpty());
    }

    @Test
    public void testFindByNameWithResult(){
        doReturn(PlanetUtil.anyPlanet()).when(planetRepository).findByName(anyString());

        Planet planet = planetService.findByName(anyString());

        Assert.assertEquals(PlanetUtil.anyPlanet(), planet);
    }

    @Test
    public void testFindByNameWithoutResult(){
        doReturn(null).when(planetRepository).findByName(anyString());

        Planet planet = planetService.findByName(anyString());

        Assert.assertNull(planet);
    }

    @Test
    public void testFindByIdWithResult(){
        doReturn(PlanetUtil.anyPlanet()).when(planetRepository).findOne(anyString());

        Planet planet = planetService.findById(anyString());

        Assert.assertEquals(PlanetUtil.anyPlanet(), planet);
    }

    @Test
    public void testFindByIdWithoutResult(){
        doReturn(null).when(planetRepository).findOne(anyString());

        Planet planet = planetService.findById(anyString());

        Assert.assertNull(planet);
    }

    @Test
    public void testSave(){
        doReturn(PlanetUtil.anyPlanet()).when(planetRepository).save(PlanetUtil.anyPlanet());

        Planet planet = planetService.save(PlanetUtil.anyPlanet());

        Assert.assertEquals(PlanetUtil.anyPlanet(), planet);
    }

    @Test
    public void testUpdate(){
        doReturn(PlanetUtil.anyPlanet()).when(planetRepository).findOne(anyString());
        doReturn(PlanetUtil.anyPlanet()).when(planetRepository).save(PlanetUtil.anyPlanet());

        Planet planet = planetService.update(PlanetUtil.anyPlanet(), anyString());

        Assert.assertEquals(PlanetUtil.anyPlanet(), planet);
    }

    @Test(expected = PlanetNotFoundException.class)
    public void testUpdatePlanetNotFound(){
        doReturn(null).when(planetRepository).findOne(anyString());

        Planet planet = planetService.update(PlanetUtil.anyPlanet(), anyString());
    }

    @Test
    public void testDelete(){
        planetService.delete(anyString());

        verify(planetRepository, times(1)).delete(anyString());
    }

}