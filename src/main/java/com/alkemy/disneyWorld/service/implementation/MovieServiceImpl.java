package com.alkemy.disneyWorld.service.implementation;

import com.alkemy.disneyWorld.dto.*;
import com.alkemy.disneyWorld.entity.CharacterEntity;
import com.alkemy.disneyWorld.entity.GenreEntity;
import com.alkemy.disneyWorld.entity.MovieEntity;
import com.alkemy.disneyWorld.exception.ParamNotFound;
import com.alkemy.disneyWorld.mapper.CharacterMapper;
import com.alkemy.disneyWorld.mapper.GenreMapper;
import com.alkemy.disneyWorld.mapper.MovieMapper;
import com.alkemy.disneyWorld.repository.MovieRepository;
import com.alkemy.disneyWorld.repository.specifications.MovieSpecification;
import com.alkemy.disneyWorld.service.CharacterService;
import com.alkemy.disneyWorld.service.GenreService;
import com.alkemy.disneyWorld.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieMapper movieMapper;
    private MovieRepository movieRepository;
    private CharacterMapper characterMapper;
    private CharacterService characterService;
    private GenreService genreService;
    private MovieSpecification movieSpecification;
    private GenreMapper genreMapper;

    @Autowired
    public MovieServiceImpl(MovieMapper movieMapper, MovieRepository movieRepository, CharacterMapper characterMapper, CharacterService characterService, GenreService genreService, MovieSpecification movieSpecification, GenreMapper genreMapper) {
        this.movieMapper = movieMapper;
        this.movieRepository = movieRepository;
        this.characterMapper = characterMapper;
        this.characterService = characterService;
        this.genreService = genreService;
        this.movieSpecification = movieSpecification;
        this.genreMapper = genreMapper;
    }

    @Override
    public MovieDTO save(MovieDTO dto) {
        MovieEntity movieEntity = movieMapper.movieDTO2entity(dto, true);
        MovieEntity savedMovie = movieRepository.save(movieEntity);
        MovieDTO movieDTO = movieMapper.movieEntity2DTO(savedMovie, true);

        return movieDTO;
    }


    @Override
    public void delete(Long id) {
        Optional<MovieEntity> movieOptional = movieRepository.findById(id);

        if(movieOptional.isPresent()) {
            movieRepository.deleteById(id);
        }else{
            throw  new ParamNotFound("Movie not found");
        }
    }

    @Override
    public MovieEntity getMovieById(Long id) {
        Optional<MovieEntity> movieOptional = movieRepository.findById(id);

        if(movieOptional.isPresent()) {
            return movieOptional.get();
        }else{
            throw  new ParamNotFound("Movie not found");
        }
    }

    @Override
    public List<MovieBasicDTO> getAllMoviesBasic() {
        List<MovieEntity> movieEntityList = movieRepository.findAll();
        List<MovieBasicDTO> movieBasicDTOList = movieMapper.movieEntityList2DTOBasicList(movieEntityList);
        return movieBasicDTOList;
    }


    @Override
    public MovieDTO getMovieDetailById(Long id) {
        MovieEntity movieEntity = getMovieById(id);
        MovieDTO movieDTO = movieMapper.movieEntity2DTO(movieEntity, true);
        return movieDTO;
    }

    //Receives character id and DTO with new movie's values and persist it on DB.
    @Override
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        MovieEntity movieEntity = getMovieById(id);

        movieEntity.setTitle(movieDTO.getTitle());
        movieEntity.setRating(movieDTO.getRating());
        movieEntity.setImage(movieDTO.getImage());
        movieEntity.setCreationDate(movieMapper.string2LocalDate(movieDTO.getCreationDate()));

        MovieEntity updatedMovie = movieRepository.save(movieEntity);

        MovieDTO updatedMovieDTO = movieMapper.movieEntity2DTO(updatedMovie, false);

        return updatedMovieDTO;
    }

    //Establishes a new relationship between a movie and a character.
    @Override
    public void addCharacter(Long id, Long characterID) {
        MovieEntity movieEntity = getMovieById(id);

        CharacterEntity characterEntity = characterService.getCharacterById(characterID);

        movieEntity.addCharacter(characterEntity);

        movieRepository.save(movieEntity);
    }

    //Delete an existing relationship between a movie and a character.
    @Override
    public void deleteCharacter(Long id, Long characterID) {
        MovieEntity movieEntity = getMovieById(id);

        CharacterEntity characterEntity = characterService.getCharacterById(characterID);

        movieEntity.removeCharacter(characterEntity);

        movieRepository.save(movieEntity);
    }

    //Persist a relationship between a movie and an existing genre.
    @Override
    public void addGenre(Long id, Long genreID) {
        MovieEntity movieEntity = getMovieById(id);

        GenreEntity genreEntity = genreService.getGenreById(genreID);

        movieEntity.addGenre(genreEntity);

        movieRepository.save(movieEntity);
    }

    //Receives different params and return a list of character DTO with the results. Uses a specification to perform the search.
    //If no entity was found through filters it returns a list of all characters with complete entity attributes.
    @Override
    public List<MovieBasicDTO> getMovieByFilters(String title, Long genreID, String order) {
        MovieFilterDTO movieFilterDTO = new MovieFilterDTO(title, genreID, order);
        List<MovieEntity> movieEntityList = movieRepository.findAll(movieSpecification.getMovieByFilters(movieFilterDTO));
        List<MovieBasicDTO> movieBasicDTOList = movieMapper.movieEntityList2DTOBasicList((movieEntityList));

        return movieBasicDTOList;
    }


}
