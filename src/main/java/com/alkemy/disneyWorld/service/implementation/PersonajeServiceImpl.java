package com.alkemy.disneyWorld.service.implementation;

import com.alkemy.disneyWorld.dto.PersonajeDTO;
import com.alkemy.disneyWorld.entity.PersonajeEntity;
import com.alkemy.disneyWorld.mapper.PersonajeMapper;
import com.alkemy.disneyWorld.repository.PersonajeRepository;
import com.alkemy.disneyWorld.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeRepository personajeRepository;

    public PersonajeDTO save (PersonajeDTO dto){

        PersonajeEntity personajeEntity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity personajeSaved = personajeRepository.save(personajeEntity);                         //El metodo save devuelve la entidad guardada con id ya asigando
        PersonajeDTO personajeDTO = personajeMapper.personajeEntity2DTO(personajeSaved, true);   //y a esa entidad guardada la transformo en dto

        return personajeDTO;
    }

    public List<PersonajeDTO> getAllPersonajes(boolean loadPeliculas) {

        List<PersonajeEntity> personajes = personajeRepository.findAll();
        List<PersonajeDTO> personajesDTO = personajeMapper.personajeEntityList2DTOList(personajes, loadPeliculas);

        return personajesDTO;
    }

    public void delete(Long id) {
        personajeRepository.deleteById(id);
    }


}
