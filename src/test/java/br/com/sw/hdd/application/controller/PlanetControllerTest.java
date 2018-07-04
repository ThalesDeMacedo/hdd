package br.com.sw.hdd.application.controller;

import br.com.sw.hdd.domain.model.Planet;
import br.com.sw.hdd.domain.service.PlanetService;
import br.com.sw.hdd.infrastructure.util.Constant;
import br.com.sw.hdd.infrastructure.util.PlanetUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class PlanetControllerTest {

    @Mock
    private PlanetService planetService;

    private PlanetController planetController;

    @Before
    public void setUp(){
        planetController = new PlanetController(planetService);
    }

    @Test
    public void testGetAllWithResults(){
        doReturn(PlanetUtil.loadListPlanets()).when(planetService).findAll();

        ResponseEntity responseEntity = planetController.getAll();

        assertStatusOkAndBodyEquals(PlanetUtil.loadListPlanets(), responseEntity);
    }

    @Test
    public void testGetAllWithoutResults(){
        doReturn(new ArrayList<>()).when(planetService).findAll();

        ResponseEntity responseEntity = planetController.getAll();

        assertStatusNotFoundAndBodyEmpty(responseEntity);
    }

    @Test
    public void testGetByIdWithResult(){
        doReturn(PlanetUtil.anyPlanet()).when(planetService).findById(anyString());

        ResponseEntity responseEntity = planetController.getById(anyString());

        assertStatusOkAndBodyEquals(PlanetUtil.anyPlanet(), responseEntity);
    }

    @Test
    public void testGetByIdWithoutResult(){
        doReturn(null).when(planetService).findById(anyString());

        ResponseEntity responseEntity = planetController.getById(anyString());

        assertStatusNotFoundAndBodyEmpty(responseEntity);
    }

    @Test
    public void testGetByNameWithResult(){
        doReturn(PlanetUtil.anyPlanet()).when(planetService).findByName(anyString());

        ResponseEntity responseEntity = planetController.getByName(anyString());

        assertStatusOkAndBodyEquals(PlanetUtil.anyPlanet(), responseEntity);
    }

    @Test
    public void testGetByNameWithoutResult(){
        doReturn(null).when(planetService).findByName(anyString());

        ResponseEntity responseEntity = planetController.getByName(anyString());

        assertStatusNotFoundAndBodyEmpty(responseEntity);
    }

    @Test
    public void testSave(){
        doReturn(PlanetUtil.anyPlanet()).when(planetService).save(PlanetUtil.anyPlanet());

        ResponseEntity responseEntity = planetController.save(PlanetUtil.anyPlanet());

        assertStatusOkAndBodyEquals(PlanetUtil.anyPlanet(), responseEntity);
    }

    @Test
    public void testUpdate(){
        doReturn(PlanetUtil.anyPlanet()).when(planetService).update(any(Planet.class), anyString());

        ResponseEntity responseEntity = planetController.update(PlanetUtil.anyPlanet(), Constant.ID);

        assertStatusOkAndBodyEquals(PlanetUtil.anyPlanet(), responseEntity);
    }

    @Test
    public void testDelete(){
        ResponseEntity responseEntity = planetController.delete(anyString());

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }





    private void assertStatusOkAndBodyEquals(Object expected, ResponseEntity responseEntity){
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(expected, responseEntity.getBody());
    }

    private void assertStatusNotFoundAndBodyEmpty(ResponseEntity responseEntity){
        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assert.assertNull(responseEntity.getBody());
    }
}