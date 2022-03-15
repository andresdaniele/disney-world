package com.alkemy.disneyWorld.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class PersonajeDTO {
    private Long id;
    private String imagen;
    private Integer edad;
    private Integer peso; //kg
    private String historia;
    private Set<PeliculaSerieDTO> peliculasSeriesDTO;

}
