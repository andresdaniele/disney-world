package com.alkemy.disneyWorld.mapper;

import com.alkemy.disneyWorld.dto.MovieDTO;
import com.alkemy.disneyWorld.dto.CharacterDTO;
import com.alkemy.disneyWorld.dto.MovieBasicDTO;
import com.alkemy.disneyWorld.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;


    public MovieEntity movieDTO2entity(MovieDTO movieDTO) {

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setRating(movieDTO.getRating());
        movieEntity.setCreationDate(string2LocalDate(movieDTO.getCreationDate()));
        movieEntity.setImage(movieDTO.getImage());
        movieEntity.setTitle(movieDTO.getTitle());
        movieEntity.setGenre(movieDTO.getGenre());

        //movieEntity.setCharacters(characterMapper.characterDTOList2EntityList(movieDTO.getCharacter()));

        return movieEntity;
    }


    public MovieDTO movieEntity2DTO(MovieEntity movie, boolean loadCharacters) {

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setImage(movie.getImage());
        movieDTO.setRating(movie.getRating());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setCreationDate(movie.getCreationDate().toString());
        movieDTO.setGenre(movie.getGenre());


        if (loadCharacters) {
            List<CharacterDTO> charactersDTO = characterMapper.characterEntityList2DTOList(movie.getCharacters(), false);
            movieDTO.setCharacters(charactersDTO);
        }
        return movieDTO;
    }

    public Set<MovieDTO> peliculaEntitySet2DTOSet(Set<MovieEntity> peliculas, boolean loadPersonajes) {

        Set<MovieDTO> dtos = new HashSet<>();

        for (MovieEntity pelicula : peliculas) {
            dtos.add(movieEntity2DTO(pelicula, loadPersonajes));
        }
        return dtos;
    }

    public Set<MovieEntity> peliculasDTOSet2EntitySet(Set<MovieDTO> peliculasDTO) {

        Set<MovieEntity> peliculas = new HashSet<>();
        for (MovieDTO dto : peliculasDTO) {
            peliculas.add(movieDTO2entity(dto));
        }

        return peliculas;
    }

    public List<MovieEntity> movieDTOList2EntityList(List<MovieDTO> movieDTOList) {

        List<MovieEntity> movieEntityList = new ArrayList<>();
        for (MovieDTO movieDTO : movieDTOList) {
            movieEntityList.add(movieDTO2entity(movieDTO));
        }
        return movieEntityList;
    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> movies, boolean loadMovies) {

        List<MovieDTO> dtos = new ArrayList<>();

        for (MovieEntity movie : movies) {
            dtos.add(movieEntity2DTO(movie, loadMovies));
        }
        return dtos;
    }

    public LocalDate string2LocalDate (String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MM,dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    public MovieBasicDTO movieEntity2DTOBasic (MovieEntity movieEntity) {

        MovieBasicDTO movieBasicDTO = new MovieBasicDTO();
        movieBasicDTO.setTitle(movieEntity.getTitle());
        movieBasicDTO.setImage(movieEntity.getImage());
        movieBasicDTO.setCreationDate(movieEntity.getCreationDate().toString());


        return movieBasicDTO;
    }

    public List<MovieBasicDTO> movieEntityList2DTOBasicList(List<MovieEntity> movieEntityList) {

        List<MovieBasicDTO> movieBasicDTOList = new ArrayList<>();

        for(MovieEntity movieEntity: movieEntityList) {
            movieBasicDTOList.add(movieEntity2DTOBasic(movieEntity));
        }

        return movieBasicDTOList;
    }
}
