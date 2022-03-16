package com.alkemy.disneyWorld.service;

import com.alkemy.disneyWorld.dto.MovieDTO;
import com.alkemy.disneyWorld.dto.MovieBasicDTO;
import com.alkemy.disneyWorld.entity.MovieEntity;

import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO dto);

    List<MovieDTO> getAllMovies();

    void delete(Long id);

    MovieEntity getMovieById(Long id);

    List<MovieBasicDTO> getAllMoviesBasic();

    MovieDTO getMovieDetailById(Long id);

    MovieDTO updateMovie(Long id, MovieDTO movieDTO);

    void addCharacter(Long id, Long characterID);

    void deleteCharacter(Long id, Long characterID);

    void addGenre(Long id, Long genreID);

}
