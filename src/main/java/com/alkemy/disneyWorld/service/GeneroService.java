package com.alkemy.disneyWorld.service;

import com.alkemy.disneyWorld.dto.GeneroDTO;

import java.util.List;

public interface GeneroService {

    GeneroDTO save(GeneroDTO dto);

    List<GeneroDTO> getAllGeneros(boolean loadPeliculas);

    void delete(Long id);

}
