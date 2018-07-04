package br.com.sw.hdd.infractructure.api;

import br.com.sw.hdd.domain.model.external.PlanetExternal;
import br.com.sw.hdd.infractructure.exception.IntegrationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "swapi", url = "${application.swapi.url}",
        configuration = SWApi.SWApiErrorDecoder.class)
public interface SWApi {

    @Cacheable("${caching.name.planets-swapi}")
    @GetMapping
    PlanetExternal findPlanetBySearch(@RequestParam("search") String search);

    class SWApiErrorDecoder implements ErrorDecoder {

        public IntegrationException decode(String methodKey, Response response) {

            final HttpStatus statusCode = HttpStatus.valueOf(response.status());

            final String message = String.format("Falha de integracao com o swaip: method: %s, httpStatus: %d",
                    methodKey, statusCode.value());

            throw new IntegrationException();
        }

    }
}
