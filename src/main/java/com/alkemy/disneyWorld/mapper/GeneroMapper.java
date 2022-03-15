package com.alkemy.disneyWorld.mapper;

import com.alkemy.disneyWorld.dto.GeneroDTO;
import com.alkemy.disneyWorld.dto.PeliculaSerieDTO;
import com.alkemy.disneyWorld.entity.GeneroEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneroMapper {

    @Autowired
    private PeliculaSerieMapper peliculaMapper;

    public GeneroEntity generoDTO2Entity(GeneroDTO generoDTO) {

        GeneroEntity generoEntity = new GeneroEntity();
        generoEntity.setImagen(generoDTO.getImagen());
        generoEntity.setNombre(generoDTO.getNombre());
        generoEntity.setPeliculasSeries(peliculaMapper.peliculasDTOList2EntityList(generoDTO.getPeliculasSeriesDTO()));

        return generoEntity;
    }

    public GeneroDTO generoEntity2DTO(GeneroEntity genero, boolean loadPeliculas) {

        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(genero.getId());
        generoDTO.setImagen(genero.getImagen());
        generoDTO.setNombre(genero.getNombre());

        if (loadPeliculas) {
            List<PeliculaSerieDTO> peliculasDTO = peliculaMapper.peliculaEntityList2DTOList(genero.getPeliculasSeries(), false);
            generoDTO.setPeliculasSeriesDTO(peliculasDTO);
        }

        return generoDTO;
    }

    public List<GeneroDTO> generoEntityListDTOList(List<GeneroEntity> generos, boolean loadPeliculas) {

        List<GeneroDTO> dtos = new ArrayList<>();
        for (GeneroEntity genero : generos) {
            dtos.add(generoEntity2DTO(genero, loadPeliculas));
        }

        return dtos;
    }
}
