package com.alkemy.disneyWorld.mapper;

import com.alkemy.disneyWorld.dto.PeliculaSerieDTO;
import com.alkemy.disneyWorld.dto.PersonajeDTO;
import com.alkemy.disneyWorld.entity.PeliculaSerieEntity;
import com.alkemy.disneyWorld.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PeliculaSerieMapper {

    @Autowired
    private PersonajeMapper personajeMapper;


    public PeliculaSerieEntity peliculaDTO2entity(PeliculaSerieDTO dto) {

        PeliculaSerieEntity peliculaEntity = new PeliculaSerieEntity();
        peliculaEntity.setCalificacion(dto.getCalificacion());
        peliculaEntity.setFechaCreacion(dto.getFechaCreacion());
        peliculaEntity.setImagen(dto.getImagen());
        peliculaEntity.setTitulo(dto.getTitulo());
        peliculaEntity.setGenero(dto.getGenero());
        peliculaEntity.setPersonajes(personajeMapper.personajeDTOList2EntityList(dto.getPersonajes()));

        return peliculaEntity;
    }


    public PeliculaSerieDTO peliculaEntity2DTO(PeliculaSerieEntity pelicula, boolean loadPersonajes) {

        PeliculaSerieDTO peliculaDTO = new PeliculaSerieDTO();
        peliculaDTO.setId(pelicula.getId());
        peliculaDTO.setImagen(pelicula.getImagen());
        peliculaDTO.setCalificacion(pelicula.getCalificacion());
        peliculaDTO.setTitulo(pelicula.getTitulo());
        peliculaDTO.setFechaCreacion(pelicula.getFechaCreacion());
        peliculaDTO.setGenero(pelicula.getGenero());

        if (loadPersonajes) {
            List<PersonajeDTO> personajesDTO = personajeMapper.personajeEntityList2DTOList(pelicula.getPersonajes(), false);
            peliculaDTO.setPersonajes(personajesDTO);
        }
        return peliculaDTO;
    }

    public Set<PeliculaSerieDTO> peliculaEntitySet2DTOSet(Set<PeliculaSerieEntity> peliculas, boolean loadPersonajes) {

        Set<PeliculaSerieDTO> dtos = new HashSet<>();

        for (PeliculaSerieEntity pelicula : peliculas) {
            dtos.add(peliculaEntity2DTO(pelicula, loadPersonajes));
        }
        return dtos;
    }

    public Set<PeliculaSerieEntity> peliculasDTOSet2EntitySet(Set<PeliculaSerieDTO> peliculasDTO) {

        Set<PeliculaSerieEntity> peliculas = new HashSet<>();
        for (PeliculaSerieDTO dto : peliculasDTO) {
            peliculas.add(peliculaDTO2entity(dto));
        }

        return peliculas;
    }

    public List<PeliculaSerieEntity> peliculasDTOList2EntityList(List<PeliculaSerieDTO> peliculasDTO) {

        List<PeliculaSerieEntity> peliculas = new ArrayList<>();
        for (PeliculaSerieDTO dto : peliculasDTO) {
            peliculas.add(peliculaDTO2entity(dto));
        }
        return peliculas;
    }

    public List<PeliculaSerieDTO> peliculaEntityList2DTOList(List<PeliculaSerieEntity> peliculas, boolean loadPeliculas) {

        List<PeliculaSerieDTO> dtos = new ArrayList<>();

        for (PeliculaSerieEntity pelicula : peliculas) {
            dtos.add(peliculaEntity2DTO(pelicula, loadPeliculas));
        }
        return dtos;
    }
}
