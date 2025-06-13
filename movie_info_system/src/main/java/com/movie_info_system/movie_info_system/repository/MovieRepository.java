package com.movie_info_system.movie_info_system.repository;



import com.movie_info_system.movie_info_system.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenre(String genre);
}