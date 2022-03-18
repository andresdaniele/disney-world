package com.alkemy.disneyWorld.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CharacterFilterDTO {

    private String name;
    private Integer age;
    private Set<Long> moviesIdSet;
    private String order;

    public boolean isASC() {return this.order.compareToIgnoreCase("ASC") == 0;}

    public boolean isDESC() {return this.order.compareToIgnoreCase("DESC") == 0;}

}
