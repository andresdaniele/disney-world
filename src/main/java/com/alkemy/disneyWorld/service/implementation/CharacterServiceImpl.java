package com.alkemy.disneyWorld.service.implementation;

import com.alkemy.disneyWorld.dto.CharacterBasicDTO;
import com.alkemy.disneyWorld.dto.CharacterDTO;
import com.alkemy.disneyWorld.dto.CharacterFilterDTO;
import com.alkemy.disneyWorld.entity.CharacterEntity;
import com.alkemy.disneyWorld.exception.ParamNotFound;
import com.alkemy.disneyWorld.mapper.CharacterMapper;
import com.alkemy.disneyWorld.mapper.MovieMapper;
import com.alkemy.disneyWorld.repository.CharacterRepository;
import com.alkemy.disneyWorld.repository.specifications.CharacterSpecifiaction;
import com.alkemy.disneyWorld.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    private CharacterMapper characterMapper;
    private CharacterRepository characterRepository;
    private MovieMapper movieMapper;
    private CharacterSpecifiaction characterSpecifiaction;

    @Autowired
    public CharacterServiceImpl(CharacterMapper characterMapper, CharacterRepository characterRepository, MovieMapper movieMapper, CharacterSpecifiaction characterSpecifiaction) {
        this.characterMapper = characterMapper;
        this.characterRepository = characterRepository;
        this.movieMapper = movieMapper;
        this.characterSpecifiaction = characterSpecifiaction;
    }

    @Override
    public CharacterDTO save(CharacterDTO dto) {

        CharacterEntity savedEntity = characterMapper.characterDTO2Entity(dto, true);
        CharacterEntity savedCharacter = characterRepository.save(savedEntity);                         //El metodo save devuelve la entidad guardada con id ya asigando
        CharacterDTO characterDTO = characterMapper.characterEntity2DTO(savedCharacter, false);   //y a esa entidad guardada la transformo en dto

        return characterDTO;
    }

    public void delete(Long id) {
        Optional<CharacterEntity> characterOptional = characterRepository.findById(id);

        if (characterOptional.isPresent()) {
            characterRepository.deleteById(id);
        } else {
            throw new ParamNotFound("Movie not found");
        }

    }


    @Override
    public List<CharacterBasicDTO> getAllCharacterBasic() {
        List<CharacterEntity> characterEntityList = characterRepository.findAll();
        List<CharacterBasicDTO> characterBasicDTOList = characterMapper.characterEntityList2DTOBasicList(characterEntityList);
        return characterBasicDTOList;
    }


    @Override
    public CharacterDTO getCharacterDetailById(Long id) {
        CharacterEntity characterEntity = getCharacterById(id);
        CharacterDTO characterDTO = characterMapper.characterEntity2DTO(characterEntity, true);
        return characterDTO;
    }

    @Override
    public CharacterEntity getCharacterById(Long id) {
        Optional<CharacterEntity> characterOptional = characterRepository.findById(id);

        if (characterOptional.isPresent()) {
            return characterOptional.get();
        } else {
            throw new ParamNotFound("Character Not Found");
        }
    }

    @Override
    public CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO) {
        CharacterEntity characterEntity = getCharacterById(id);

        characterEntity.setName(characterDTO.getName());
        characterEntity.setHistory(characterDTO.getHistory());
        characterEntity.setAge(characterDTO.getAge());
        characterEntity.setImage(characterDTO.getImage());
        characterEntity.setWeight(characterDTO.getWeight());

        /*
        if (characterDTO.getMovies() != null) {
            characterEntity.setMovies(movieMapper.movieDTOList2EntityList(characterDTO.getMovies()));
        }
         */

        CharacterEntity updatedCharacter = characterRepository.save(characterEntity);

        CharacterDTO updatedCharacterDTO = characterMapper.characterEntity2DTO(updatedCharacter, false);

        return updatedCharacterDTO;
    }


    @Override
    public List<CharacterDTO> getCharactersByFilters(String name, Integer age, Set<Long> moviesIdSet, String order) {
        CharacterFilterDTO characterFilterDTO = new CharacterFilterDTO(name, age, moviesIdSet, order);
        List<CharacterEntity> characterEntityList = characterRepository.findAll(characterSpecifiaction.getCharacterByFilters(characterFilterDTO));
        List<CharacterDTO> characterDTOList = characterMapper.characterEntityList2DTOList(characterEntityList, true);

        return characterDTOList;
    }


}
