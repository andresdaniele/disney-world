package com.alkemy.disneyWorld.service;

import com.alkemy.disneyWorld.dto.PersonajeDTO;

import java.util.List;

public interface PersonajeService {

    PersonajeDTO save(PersonajeDTO dto);

    List<PersonajeDTO> getAllPersonajes(boolean loadPeliculas);

    void delete(Long id);
}
