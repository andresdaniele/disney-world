package com.alkemy.disneyWorld.service.implementation;

import com.alkemy.disneyWorld.dto.GeneroDTO;
import com.alkemy.disneyWorld.entity.GeneroEntity;
import com.alkemy.disneyWorld.mapper.GeneroMapper;
import com.alkemy.disneyWorld.repository.GeneroRepository;
import com.alkemy.disneyWorld.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private GeneroRepository generoRepository;

    public GeneroDTO save(GeneroDTO dto) {
        GeneroEntity generoEntity = generoMapper.generoDTO2Entity(dto);
        GeneroEntity generoSaved = generoRepository.save(generoEntity);
        GeneroDTO generoDTO = generoMapper.generoEntity2DTO(generoSaved, true);

        return generoDTO;
    }

    public List<GeneroDTO> getAllGeneros(boolean loadPeliculas) {
        List<GeneroEntity> generosEntity = generoRepository.findAll();
        List<GeneroDTO> generosDTO = generoMapper.generoEntityListDTOList(generosEntity, loadPeliculas);

        return generosDTO;
    }

    public void delete(Long id) {
        generoRepository.deleteById(id);
    }
}
