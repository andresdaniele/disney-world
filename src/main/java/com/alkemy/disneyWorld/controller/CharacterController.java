package com.alkemy.disneyWorld.controller;

import com.alkemy.disneyWorld.dto.CharacterBasicDTO;
import com.alkemy.disneyWorld.dto.CharacterDTO;
import com.alkemy.disneyWorld.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/all")
    public ResponseEntity<List<CharacterBasicDTO>> getAllBasicCharacter() {
        List<CharacterBasicDTO> characterBasic = characterService.getAllCharacterBasic();
        return ResponseEntity.ok().body(characterBasic);
    }

    @GetMapping("{id}")
    public ResponseEntity<CharacterDTO> getCharacterDetailbyId(@PathVariable Long id) {
        CharacterDTO characterDTO = characterService.getCharacterDetailById(id);
        return ResponseEntity.ok().body(characterDTO);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO characterDTO) {
        CharacterDTO savedCharacter = characterService.save(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> updateCharacter(@PathVariable Long id, @RequestBody CharacterDTO characterDTO) {
        CharacterDTO updateCharacterDTO = characterService.updateCharacter(id, characterDTO);
        return ResponseEntity.ok().body(updateCharacterDTO);
    }

    @GetMapping()
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Set<Long> moviesIdSet,
            @RequestParam(required = false, defaultValue = "ACS") String order
    ) {
        List<CharacterDTO> characterDTOList = characterService.getCharactersByFilters(name, age, moviesIdSet, order);
        return ResponseEntity.status(HttpStatus.OK).body(characterDTOList);
    }


}
