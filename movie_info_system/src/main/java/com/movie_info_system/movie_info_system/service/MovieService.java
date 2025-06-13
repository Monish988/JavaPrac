package com.movie_info_system.movie_info_system.service;

import com.movie_info_system.movie_info_system.Movie;
import com.movie_info_system.movie_info_system.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieService {
    @Autowired
    private MovieRepository repo;

    public List<Movie> getAll() {
        return repo.findAll();
    }

    public Movie getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Movie> getByGenre(String genre) {
        return repo.findByGenre(genre);
    }

    public Movie save(Movie movie) {
        return repo.save(movie);
    }

    public Movie update(Long id, Movie m) {
        Movie movie = getById(id);
        if (movie != null) {
            movie.setName(m.getName());
            movie.setGenre(m.getGenre());
            movie.setRating(m.getRating());
            movie.setReleaseYear(m.getReleaseYear());
            return repo.save(movie);
        }
        return null;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}