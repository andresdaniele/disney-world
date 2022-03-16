package com.alkemy.disneyWorld.dto;

import com.alkemy.disneyWorld.entity.GenreEntity;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
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
    private GenreEntity genre;
}
