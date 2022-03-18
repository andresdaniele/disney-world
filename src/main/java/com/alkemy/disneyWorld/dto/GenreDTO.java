package com.alkemy.disneyWorld.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenreDTO {

    private Long id;
    private String name;
    private String image;
    private List<MovieDTO> movies;
}
