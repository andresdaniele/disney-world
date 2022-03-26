package com.alkemy.disneyWorld.service.implementation;

import com.alkemy.disneyWorld.dto.CharacterBasicDTO;
import com.alkemy.disneyWorld.dto.CharacterDTO;
import com.alkemy.disneyWorld.dto.CharacterFilterDTO;
import com.alkemy.disneyWorld.entity.CharacterEntity;
import com.alkemy.disneyWorld.exception.ParamNotFound;
import com.alkemy.disneyWorld.mapper.CharacterMapper;
import com.alkemy.disneyWorld.mapper.MovieMapper;
import com.alkemy.disneyWorld.repository.CharacterRepository;
import com.alkemy.disneyWorld.repository.specifications.CharacterSpecification;
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
    private CharacterSpecification characterSpecification;

    @Autowired
    public CharacterServiceImpl(CharacterMapper characterMapper, CharacterRepository characterRepository, MovieMapper movieMapper, CharacterSpecification characterSpecification) {
        this.characterMapper = characterMapper;
        this.characterRepository = characterRepository;
        this.movieMapper = movieMapper;
        this.characterSpecification = characterSpecification;
    }

    //Receives a character DTO, persist entity on DB and return saved DTO.
    @Override
    public CharacterDTO save(CharacterDTO dto) {

        CharacterEntity savedEntity = characterMapper.characterDTO2Entity(dto, true);
        CharacterEntity savedCharacter = characterRepository.save(savedEntity);                               //JpaRepository's save method return saved entity
        CharacterDTO characterDTO = characterMapper.characterEntity2DTO(savedCharacter, true);

        return characterDTO;
    }

    //Receives a character id,  look if it exists and soft delete it.
    public void delete(Long id) {
        Optional<CharacterEntity> characterOptional = characterRepository.findById(id);

        if (characterOptional.isPresent()) {
            characterRepository.deleteById(id);
        } else {
            throw new ParamNotFound("Movie not found");
        }
    }

    //Returns a list of character DTO that contains basic information about them.
    @Override
    public List<CharacterBasicDTO> getAllCharacterBasic() {
        List<CharacterEntity> characterEntityList = characterRepository.findAll();
        List<CharacterBasicDTO> characterBasicDTOList = characterMapper.characterEntityList2DTOBasicList(characterEntityList);
        return characterBasicDTOList;
    }

    //Receives a character id, find its entity on BD and returns a DTO that contains complete attributes from the character.
    @Override
    public CharacterDTO getCharacterDetailById(Long id) {
        CharacterEntity characterEntity = getCharacterById(id);
        CharacterDTO characterDTO = characterMapper.characterEntity2DTO(characterEntity, true);
        return characterDTO;
    }

    //Method to confirm if character exists and if it not, throws an exception
    @Override
    public CharacterEntity getCharacterById(Long id) {
        Optional<CharacterEntity> characterOptional = characterRepository.findById(id);

        if (characterOptional.isPresent()) {
            return characterOptional.get();
        } else {
            throw new ParamNotFound("Character Not Found");
        }
    }

    //Receives character id and DTO with new character's values and persist it on DB. Returns the new character DTO.
    @Override
    public CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO) {
        CharacterEntity characterEntity = getCharacterById(id);

        characterEntity.setName(characterDTO.getName());
        characterEntity.setHistory(characterDTO.getHistory());
        characterEntity.setAge(characterDTO.getAge());
        characterEntity.setImage(characterDTO.getImage());
        characterEntity.setWeight(characterDTO.getWeight());

        CharacterEntity updatedCharacter = characterRepository.save(characterEntity);

        CharacterDTO updatedCharacterDTO = characterMapper.characterEntity2DTO(updatedCharacter, true);

        return updatedCharacterDTO;
    }


    //Receives different params and return a list of character DTO with the results. Uses a specification to perform the search.
    //If no entity was found through filters it returns a list of all characters with complete entity attributes.
    @Override
    public List<CharacterBasicDTO> getCharactersByFilters(String name, Integer age, Set<Long> moviesIdSet, String order) {
        CharacterFilterDTO characterFilterDTO = new CharacterFilterDTO(name, age, moviesIdSet, order);     //New filterDTO with attribute values received by params
        List<CharacterEntity> characterEntityList = characterRepository.findAll(characterSpecification.getCharacterByFilters(characterFilterDTO));
        List<CharacterBasicDTO> characterBasicDTOList = characterMapper.characterEntityList2DTOBasicList(characterEntityList);

        return characterBasicDTOList;
    }


}
