package com.alkemy.disneyWorld.controller;

import com.alkemy.disneyWorld.dto.GenreDTO;
import com.alkemy.disneyWorld.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll() {
        List<GenreDTO> genreDTOList = genreService.getAllGenre();
        return ResponseEntity.ok().body(genreDTOList);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> save (@RequestBody GenreDTO genreDTO) {
        GenreDTO savedGenre = genreService.save(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete (@PathVariable long id) {
        genreService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
