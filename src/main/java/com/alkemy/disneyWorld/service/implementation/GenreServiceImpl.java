package com.alkemy.disneyWorld.service.implementation;

import com.alkemy.disneyWorld.dto.GenreDTO;
import com.alkemy.disneyWorld.entity.GenreEntity;
import com.alkemy.disneyWorld.exception.ParamNotFound;
import com.alkemy.disneyWorld.mapper.GenreMapper;
import com.alkemy.disneyWorld.repository.GenreRepository;
import com.alkemy.disneyWorld.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private GenreRepository genreRepository;


    //Receives a character DTO, persist entity on DB and return saved DTO.
    @Override
    public GenreDTO save(GenreDTO dto) {
        GenreEntity genreEntity = genreMapper.genreDTO2Entity(dto, true);
        GenreEntity savedGenre = genreRepository.save(genreEntity);
        GenreDTO genreDTO = genreMapper.genreEntity2DTO(savedGenre, false);

        return genreDTO;
    }

    //Returns a list of genres with complete information.
    @Override
    public List<GenreDTO> getAllGenre() {
        List<GenreEntity> genreEntities = genreRepository.findAll();
        List<GenreDTO> generosDTO = genreMapper.genreEntityListDTOList(genreEntities, true);

        return generosDTO;
    }

    //Receives a genre id, search for it on DB and soft delete it.
    @Override
    public void delete(Long id) {
        Optional<GenreEntity> genreOptional = genreRepository.findById(id);

        if(genreOptional.isPresent()) {
            genreRepository.deleteById(id);
        }else{
            throw  new ParamNotFound("Genre not found");
        }
    }

    //Receives a genre id, find it on DB and return entity.
    @Override
    public GenreEntity getGenreById(Long id) {
        Optional<GenreEntity> genreOptional = genreRepository.findById(id);

        if(genreOptional.isPresent()){
            return genreOptional.get();
        }else {
            throw new ParamNotFound("Genre not found");
        }
    }

}
