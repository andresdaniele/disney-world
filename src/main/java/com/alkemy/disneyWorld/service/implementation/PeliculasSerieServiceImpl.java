package com.alkemy.disneyWorld.service.implementation;

import com.alkemy.disneyWorld.dto.PeliculaSerieDTO;
import com.alkemy.disneyWorld.entity.PeliculaSerieEntity;
import com.alkemy.disneyWorld.mapper.PeliculaSerieMapper;
import com.alkemy.disneyWorld.repository.PeliculaSerieRepository;
import com.alkemy.disneyWorld.service.PeliculaSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PeliculasSerieServiceImpl implements PeliculaSerieService {

    @Autowired
    private PeliculaSerieMapper peliculaMapper;

    @Autowired
    private PeliculaSerieRepository peliculaRepository;

    public PeliculaSerieDTO save(PeliculaSerieDTO dto) {
        PeliculaSerieEntity peliculaEntity = peliculaMapper.peliculaDTO2entity(dto);
        PeliculaSerieEntity peliculaSaved = peliculaRepository.save(peliculaEntity);
        PeliculaSerieDTO peliculaDTO = peliculaMapper.peliculaEntity2DTO(peliculaSaved, true);

        return peliculaDTO;
    }


    public List<PeliculaSerieDTO> getAllPeliculas(boolean loadPersonajes) {
        List<PeliculaSerieEntity> peliculasEntity = peliculaRepository.findAll();
        List<PeliculaSerieDTO> peliculasDTO = peliculaMapper.peliculaEntityList2DTOList(peliculasEntity, loadPersonajes);

        return peliculasDTO;
    }


    public void delete(Long id) {
        peliculaRepository.deleteById(id);
    }
}
