package com.alkemy.disneyWorld.mapper;

import com.alkemy.disneyWorld.dto.GenreDTO;
import com.alkemy.disneyWorld.dto.MovieBasicDTO;
import com.alkemy.disneyWorld.dto.MovieDTO;
import com.alkemy.disneyWorld.dto.CharacterDTO;
import com.alkemy.disneyWorld.entity.GenreEntity;
import com.alkemy.disneyWorld.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private GenreMapper genreMapper;


    public MovieEntity movieDTO2entity(MovieDTO movieDTO, boolean loadCharacters) {

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setRating(movieDTO.getRating());
        movieEntity.setCreationDate(string2LocalDate(movieDTO.getCreationDate()));
        movieEntity.setImage(movieDTO.getImage());
        movieEntity.setTitle(movieDTO.getTitle());

        GenreEntity genreEntity = genreMapper.genreDTO2Entity(movieDTO.getGenre(), false);
        movieEntity.setGenre(genreEntity);

        if(loadCharacters) {
            movieEntity.setCharacters(characterMapper.characterDTOList2EntityList(movieDTO.getCharacters(), false));
        }


        return movieEntity;
    }


    public MovieDTO movieEntity2DTO(MovieEntity movieEntity, boolean loadCharacters) {

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movieEntity.getId());
        movieDTO.setImage(movieEntity.getImage());
        movieDTO.setRating(movieEntity.getRating());
        movieDTO.setTitle(movieEntity.getTitle());
        movieDTO.setCreationDate(movieEntity.getCreationDate().toString());

        GenreDTO genreDTO = genreMapper.genreEntity2DTO(movieEntity.getGenre(), false);
        movieDTO.setGenre(genreDTO);

        if (loadCharacters) {
            List<CharacterDTO> charactersDTO = characterMapper.characterEntityList2DTOList(movieEntity.getCharacters(), false);
            movieDTO.setCharacters(charactersDTO);
        }
        return movieDTO;
    }


    public List<MovieEntity> moviesDTOList2EntityList(List<MovieDTO> movieDTOList, boolean loadCharacters) {

        List<MovieEntity> movies = new ArrayList<>();
        for (MovieDTO dto : movieDTOList) {
            movies.add(movieDTO2entity(dto, loadCharacters));
        }

        return movies;
    }

    public List<MovieEntity> movieDTOList2EntityList(List<MovieDTO> movieDTOList, boolean loadCharacters) {

        List<MovieEntity> movieEntityList = new ArrayList<>();
        for (MovieDTO movieDTO : movieDTOList) {
            movieEntityList.add(movieDTO2entity(movieDTO, loadCharacters));
        }
        return movieEntityList;
    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> movies, boolean loadCharacters) {

        List<MovieDTO> dtos = new ArrayList<>();

        for (MovieEntity movie : movies) {
            dtos.add(movieEntity2DTO(movie, loadCharacters));
        }
        return dtos;
    }

    //Receives a string date. Format and parse to LocalDate. Returns a LocalDate date.
    public LocalDate string2LocalDate (String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MM,dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }


    public MovieBasicDTO movieEntity2DTOBasic (MovieEntity movieEntity) {

        MovieBasicDTO movieBasicDTO = new MovieBasicDTO();
        movieBasicDTO.setId(movieEntity.getId());
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
