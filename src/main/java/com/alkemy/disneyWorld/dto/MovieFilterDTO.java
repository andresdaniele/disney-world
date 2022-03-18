package com.alkemy.disneyWorld.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovieFilterDTO {
    private String title;
    private Long genreID;
    private String order;

    public boolean isASC() {return this.order.compareToIgnoreCase("ASC") == 0;}

    public boolean isDESC() {return this.order.compareToIgnoreCase("DESC") == 0;}

}
