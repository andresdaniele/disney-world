package com.alkemy.disneyWorld.controller;

import com.alkemy.disneyWorld.dto.MovieBasicDTO;
import com.alkemy.disneyWorld.dto.MovieDTO;
import com.alkemy.disneyWorld.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<MovieBasicDTO>> getAllBasicMovies() {
        List<MovieBasicDTO> movieBasicDTOList = movieService.getAllMoviesBasic();
        return ResponseEntity.ok().body(movieBasicDTOList);
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieDTO> getMovieDetailbyId(@PathVariable Long id) {
        MovieDTO movieDTO = movieService.getMovieDetailById(id);
        return ResponseEntity.ok().body(movieDTO);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO) {
        MovieDTO savedMovie = movieService.save(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @PostMapping("/{id}/character/{characterID}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long id, @PathVariable Long characterID) {
        movieService.addCharacter(id, characterID);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}/genre/{genreID}")
    public ResponseEntity<Void> addGenre(@PathVariable Long id, @PathVariable Long genreID) {
        movieService.addGenre(id, genreID);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}/character/{characterID}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id, @PathVariable Long characterID) {
        movieService.deleteCharacter(id, characterID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        MovieDTO updateMovieDTO = movieService.updateMovie(id, movieDTO);
        return ResponseEntity.ok().body(updateMovieDTO);
    }

    @GetMapping()
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false, defaultValue = "ACS") String order
    ) {
        List<MovieDTO> movieDTOList = movieService.getMovieByFilters(title, genreId, order);
        return ResponseEntity.status(HttpStatus.OK).body(movieDTOList);
    }
}
