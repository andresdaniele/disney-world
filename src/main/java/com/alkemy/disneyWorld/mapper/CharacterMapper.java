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
public class CharacterMapper {                          //Mapper es reutilizable y deja limpia la logica en el servicio

    @Autowired
    private MovieMapper movieMapper;

/*
    @Autowired
    public CharacterMapper(@Lazy MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }
*/


    //conversion de DTO a entidad
    public CharacterEntity characterDTO2Entity(CharacterDTO dto) {

        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setHistory(dto.getHistory());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setImage(dto.getImage());

        //characterEntity.setMovies(movieMapper.peliculasDTOSet2EntitySet(dto.getPeliculasSeries()));

        return characterEntity;
    }

    //conversion de entidad a DTO
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
        }

        return characterDTO;
    }

    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> characters, boolean loadMovies) {

        List<CharacterDTO> dtos = new ArrayList<>();

        for (CharacterEntity character: characters) {
            dtos.add(characterEntity2DTO(character, loadMovies));
        }
        return dtos;
    }

    public List<CharacterEntity> characterDTOList2EntityList (List<CharacterDTO> charactersDTO ) {

        List<CharacterEntity> characters = new ArrayList<>();
        for (CharacterDTO characterDTO: charactersDTO) {
            characters.add(characterDTO2Entity(characterDTO));
        }

        return characters;
    }

    public CharacterBasicDTO characterEntity2DTOBasic (CharacterEntity characterEntity) {

        CharacterBasicDTO characterBasicDTO = new CharacterBasicDTO();
        characterBasicDTO.setName(characterEntity.getName());
        characterBasicDTO.setImage(characterEntity.getImage());

        return characterBasicDTO;
    }

    public List<CharacterBasicDTO> characterEntityList2DTOBasicList(List<CharacterEntity> characterEntityList) {

        List<CharacterBasicDTO> characterBasicDTOList = new ArrayList<>();

        for(CharacterEntity characterEntity: characterEntityList) {
            characterBasicDTOList.add(characterEntity2DTOBasic(characterEntity));
        }

        return characterBasicDTOList;
    }
}
