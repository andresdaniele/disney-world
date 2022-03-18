package com.alkemy.disneyWorld.service;

import com.alkemy.disneyWorld.dto.GenreDTO;
import com.alkemy.disneyWorld.entity.GenreEntity;

import java.util.List;

public interface GenreService {

    GenreDTO save(GenreDTO dto);

    List<GenreDTO> getAllGenre();

    void delete(Long id);

    GenreEntity getGenreById(Long id);
}
