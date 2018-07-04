package br.com.sw.hdd.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Planet {

    @Id
    private String id;

    @NotEmpty
    @Indexed(unique = true)
    private String name;
    @NotEmpty
    private String climate;
    @NotEmpty
    private String terrain;

    @Transient
    private int numberOfApparitions;

}
