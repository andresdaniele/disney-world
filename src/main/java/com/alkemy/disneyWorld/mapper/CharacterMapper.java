package com.alkemy.disneyWorld.mapper;

import com.alkemy.disneyWorld.dto.CharacterBasicDTO;
import com.alkemy.disneyWorld.dto.MovieDTO;
import com.alkemy.disneyWorld.dto.CharacterDTO;
import com.alkemy.disneyWorld.entity.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {                          //Mapper is convenient to avoid re-writing code in services. Contains universal methods to apply in services.

    @Autowired
    private MovieMapper movieMapper;

<<<<<<< HEAD
    //Receives a DTO and returns an entity
=======
>>>>>>> 93e0f729e166f97dcba96d0cbe9c601f59992bd8
    public CharacterEntity characterDTO2Entity(CharacterDTO dto, boolean loadMovies) {

        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setHistory(dto.getHistory());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setImage(dto.getImage());

        if(loadMovies){
            characterEntity.setMovies(movieMapper.moviesDTOList2EntityList(dto.getMovies(), false));
        }

        return characterEntity;
    }

    //Receives an entity and returns a DTO
    public CharacterDTO characterEntity2DTO(CharacterEntity character, boolean loadMovies){

        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setId(character.getId());              //La entidad si viene con el id asi que hay que asignarselo al DTO
        characterDTO.setName(character.getName());
        characterDTO.setAge(character.getAge());
        characterDTO.setHistory(character.getHistory());
        characterDTO.setWeight(character.getWeight());
        characterDTO.setImage(character.getImage());

        if(loadMovies) {
            List<MovieDTO> moviesDTO = movieMapper.movieEntityList2DTOList(character.getMovies(), false);
            characterDTO.setMovies(moviesDTO);
        }

        return characterDTO;
    }

    //Receives an entity list and returns a DTO list
    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> characters, boolean loadMovies) {

        List<CharacterDTO> dtos = new ArrayList<>();

        for (CharacterEntity character: characters) {
            dtos.add(characterEntity2DTO(character, loadMovies));
        }
        return dtos;
    }

    //Receives a DTO list and returns an entity list
    public List<CharacterEntity> characterDTOList2EntityList (List<CharacterDTO> charactersDTO, boolean loadMovies ) {

        List<CharacterEntity> characters = new ArrayList<>();
        for (CharacterDTO characterDTO: charactersDTO) {
            characters.add(characterDTO2Entity(characterDTO, loadMovies));
        }

        return characters;
    }

    //Receives an entity and returns DTO basic.
    public CharacterBasicDTO characterEntity2DTOBasic (CharacterEntity characterEntity) {

        CharacterBasicDTO characterBasicDTO = new CharacterBasicDTO();
        characterBasicDTO.setName(characterEntity.getName());
        characterBasicDTO.setImage(characterEntity.getImage());

        return characterBasicDTO;
    }

    //Receives an entity list and returns DTO basic list.
    public List<CharacterBasicDTO> characterEntityList2DTOBasicList(List<CharacterEntity> characterEntityList) {

        List<CharacterBasicDTO> characterBasicDTOList = new ArrayList<>();

        for(CharacterEntity characterEntity: characterEntityList) {
            characterBasicDTOList.add(characterEntity2DTOBasic(characterEntity));
        }

        return characterBasicDTOList;
    }


}
