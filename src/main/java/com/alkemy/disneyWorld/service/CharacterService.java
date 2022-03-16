package com.alkemy.disneyWorld.service;

import com.alkemy.disneyWorld.dto.CharacterDTO;
import com.alkemy.disneyWorld.dto.CharacterBasicDTO;
import com.alkemy.disneyWorld.entity.CharacterEntity;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto);

    List<CharacterDTO> getAllCharacters();

    void delete(Long id);

    List<CharacterBasicDTO> getAllCharacterBasic();

    CharacterDTO getCharacterDetailById(Long id);

    CharacterEntity getCharacterById(Long id);

    CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO);

    void addMovie(Long id, Long movieID);

    void deleteMovie(Long id, Long movieID);

    List<CharacterDTO> getCharactersByFilters(String name, Integer age, Set<Long> moviesIdSet, String order);
}