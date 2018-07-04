package br.com.sw.hdd.infrastructure.util;

import br.com.sw.hdd.domain.model.Planet;
import br.com.sw.hdd.domain.model.external.PlanetExternal;
import br.com.sw.hdd.domain.model.external.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlanetUtil {

    private static List<Planet> planets;
    private static Planet planet;
    private static PlanetExternal planetExternal;
    private static Result result;

    static {
        planet = Planet.builder().id(Constant.ID).climate(Constant.CLIMATE).terrain(Constant.TERAIN).build();
        Planet newPlanet1 = Planet.builder().id(Constant.ID).climate(Constant.CLIMATE).terrain(Constant.TERAIN).build();
        Planet newPlanet2 = Planet.builder().id(Constant.ID).climate(Constant.CLIMATE).terrain(Constant.TERAIN).build();


        planets = Arrays.asList(planet, newPlanet1, newPlanet2);

        planetExternal = PlanetExternal.builder().results(
                Arrays.asList(
                        Result.builder().films(new ArrayList<>()).build(),
                        Result.builder().films(new ArrayList<>()).build(),
                        Result.builder().films(new ArrayList<>()).build()
                )
        ).build();

        result = Result.builder().films(Arrays.asList("filme1", "filme2", "filme3")).build();
    }

    public static List<Planet> loadListPlanets(){
        return planets;
    }

    public static Planet anyPlanet(){
        return planet;
    }

    public static PlanetExternal anyPlanetExternal(){
        return planetExternal;
    }

    public static Result anyResult(){
        return result;
    }
}
