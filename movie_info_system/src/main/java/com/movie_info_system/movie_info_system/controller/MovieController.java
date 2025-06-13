package com.movie_info_system.movie_info_system.controller;



import com.movie_info_system.movie_info_system.Movie;
import com.movie_info_system.movie_info_system.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping("/movies")
    public List<Movie> getAll() {
        return service.getAll();
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        Movie movie = service.getById(id);
        return movie != null ? ResponseEntity.ok(movie) : ResponseEntity.notFound().build();
    }

    @GetMapping("/movies/genre/{genre}")
    public List<Movie> getByGenre(@PathVariable String genre) {
        return service.getByGenre(genre);
    }

    @PostMapping("/movie")
    public Movie create(@RequestBody Movie movie) {
        return service.save(movie);
    }

    @PutMapping("/movie/{id}")
    public Movie update(@PathVariable Long id, @RequestBody Movie movie) {
        return service.update(id, movie);
    }

    @DeleteMapping("/movie/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}