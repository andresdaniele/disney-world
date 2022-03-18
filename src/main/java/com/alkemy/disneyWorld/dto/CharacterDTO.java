package com.alkemy.disneyWorld.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class CharacterDTO {
    private Long id;
    private String name;
    private String image;
    private Integer age;
    private Integer weight; //kg
    private String history;
    private List<MovieDTO> movies;

}
