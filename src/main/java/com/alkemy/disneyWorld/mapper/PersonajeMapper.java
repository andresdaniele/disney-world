package com.alkemy.disneyWorld.mapper;


import com.alkemy.disneyWorld.dto.PeliculaSerieDTO;
import com.alkemy.disneyWorld.dto.PersonajeDTO;
import com.alkemy.disneyWorld.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class PersonajeMapper {                          //Mapper es reutilizable y deja limpia la logica en el servicio

    @Autowired
    private PeliculaSerieMapper peliculaMapper;

    //conversion de DTO a entidad
    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto) {

        PersonajeEntity personajeEntity = new PersonajeEntity();
        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setHistoria(dto.getHistoria());
        personajeEntity.setPeso(dto.getPeso());
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setPeliculasSeries(peliculaMapper.peliculasDTOSet2EntitySet(dto.getPeliculasSeriesDTO()));

        return personajeEntity;
    }

    //conversion de entidad a DTO
    public PersonajeDTO personajeEntity2DTO(PersonajeEntity personaje, boolean loadPeliculas){

        PersonajeDTO personajeDTO = new PersonajeDTO();
        personajeDTO.setId(personaje.getId());              //La entidad si viene con el id asi que hay que asignarselo al DTO
        personajeDTO.setEdad(personaje.getEdad());
        personajeDTO.setHistoria(personaje.getHistoria());
        personajeDTO.setPeso(personaje.getPeso());
        personajeDTO.setImagen(personaje.getImagen());

        if(loadPeliculas) {
            Set<PeliculaSerieDTO> peliculasDTO = peliculaMapper.peliculaEntitySet2DTOSet(personaje.getPeliculasSeries(), false);
        }

        return personajeDTO;
    }

    public List<PersonajeDTO> personajeEntityList2DTOList(List<PersonajeEntity> personajes, boolean loadPeliculas) {

        List<PersonajeDTO> dtos = new ArrayList<>();

        for (PersonajeEntity personaje: personajes) {
            dtos.add(personajeEntity2DTO(personaje, loadPeliculas));
        }
        return dtos;
    }

    public List<PersonajeEntity> personajeDTOList2EntityList (List<PersonajeDTO> personajesDTO ) {

        List<PersonajeEntity> personajes = new ArrayList<>();
        for (PersonajeDTO dto: personajesDTO) {
            personajes.add(personajeDTO2Entity(dto));
        }

        return personajes;
    }
}
