package br.com.sw.hdd.domain.model.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetExternal {
    private int count;
    private List<Result> results;

    public int numberOfApparitions(){
        return results.stream().mapToInt(x -> x.getFilms().size()).sum();
    }
}