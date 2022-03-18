package com.alkemy.disneyWorld.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MovieDTO {

    private Long id;
    private String image;
    private String title;
    private String creationDate;
    private Integer rating;
    private List<CharacterDTO> characters;
    private GenreDTO genre;
}
