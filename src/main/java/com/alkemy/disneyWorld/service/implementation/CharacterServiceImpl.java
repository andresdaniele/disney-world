package com.alkemy.disneyWorld.service.implementation;

import com.alkemy.disneyWorld.dto.CharacterDTO;
import com.alkemy.disneyWorld.dto.CharacterBasicDTO;
import com.alkemy.disneyWorld.dto.CharacterFilterDTO;
import com.alkemy.disneyWorld.entity.CharacterEntity;
import com.alkemy.disneyWorld.entity.MovieEntity;
import com.alkemy.disneyWorld.mapper.CharacterMapper;
import com.alkemy.disneyWorld.mapper.MovieMapper;
import com.alkemy.disneyWorld.repository.CharacterRepository;
import com.alkemy.disneyWorld.service.CharacterService;
import com.alkemy.disneyWorld.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieService movieService;

    @Override
    public CharacterDTO save (CharacterDTO dto){

        CharacterEntity savedEntity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity savedCharacter = characterRepository.save(savedEntity);                         //El metodo save devuelve la entidad guardada con id ya asigando
        CharacterDTO characterDTO = characterMapper.characterEntity2DTO(savedCharacter, false);   //y a esa entidad guardada la transformo en dto

        return characterDTO;
    }

    @Override
    public List<CharacterDTO> getAllCharacters() {

        List<CharacterEntity> characterEntities = characterRepository.findAll();
        List<CharacterDTO> characterDTOList = characterMapper.characterEntityList2DTOList(characterEntities, true);

        return characterDTOList;
    }

    public void delete(Long id) {
        characterRepository.deleteById(id);
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

        if(characterOptional.isPresent()) {
            return characterOptional.get();
        }else{
            return null;
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

        characterEntity.setMovies(movieMapper.movieDTOList2EntityList(characterDTO.getMovies()));

        CharacterEntity updatedCharacter = characterRepository.save(characterEntity);

        CharacterDTO updatedCharacterDTO = characterMapper.characterEntity2DTO(updatedCharacter, false);

        return updatedCharacterDTO;
    }

    @Override
    public void addMovie(Long id, Long movieID) {
        CharacterEntity characterEntity = getCharacterById(id);

        MovieEntity movie = movieService.getMovieById(movieID);

        characterEntity.addMovie(movie);

        characterRepository.save(characterEntity);
    }

    @Override
    public void deleteMovie(Long id, Long movieID) {
        CharacterEntity characterEntity = getCharacterById(id);

        MovieEntity movie = movieService.getMovieById(movieID);

        characterEntity.deleteMovie(movie);

        characterRepository.save(characterEntity);

    }

    @Override
    public List<CharacterDTO> getCharactersByFilters(String name, Integer age, Set<Long> moviesIdSet, String order) {
        CharacterFilterDTO characterFilterDTO = new CharacterFilterDTO(name, age, moviesIdSet, order);
        List<CharacterEntity> characterEntityList = characterRepository

        return null;
    }


}
