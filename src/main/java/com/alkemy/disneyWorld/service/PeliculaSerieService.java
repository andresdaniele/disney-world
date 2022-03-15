package com.alkemy.disneyWorld.service;

import com.alkemy.disneyWorld.dto.PeliculaSerieDTO;

import java.util.List;

public interface PeliculaSerieService {

    PeliculaSerieDTO save(PeliculaSerieDTO dto);

    List<PeliculaSerieDTO> getAllPeliculas (boolean loadPersonaje);

    void delete(Long id);

}
