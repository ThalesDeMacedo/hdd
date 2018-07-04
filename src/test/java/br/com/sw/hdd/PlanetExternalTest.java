package br.com.sw.hdd;

import br.com.sw.hdd.domain.model.external.PlanetExternal;
import br.com.sw.hdd.infrastructure.util.Constant;
import br.com.sw.hdd.infrastructure.util.PlanetUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class PlanetExternalTest {

    private PlanetExternal planetExternal;

    @Before
    public void setUp(){
        planetExternal = PlanetExternal.builder().results(Arrays.asList(PlanetUtil.anyResult())).build();
    }

    @Test
    public void testThreeFilms(){
        int numberOfApparitions = planetExternal.numberOfApparitions();

        Assert.assertEquals(Constant.QTD_THREE_FILMS, numberOfApparitions);
    }

    @Test
    public void testWithoutFilms(){
        PlanetExternal planetExternal = PlanetExternal.builder().results(new ArrayList<>()).build();
        int numberOfApparitions = planetExternal.numberOfApparitions();

        Assert.assertEquals(Constant.QTD_ZERO_FILMS, numberOfApparitions);
    }
}
