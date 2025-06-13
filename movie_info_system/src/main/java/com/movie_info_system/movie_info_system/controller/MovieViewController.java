package com.movie_info_system.movie_info_system.controller;


import com.movie_info_system.movie_info_system.Movie;
import com.movie_info_system.movie_info_system.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MovieViewController {

    @Autowired
    private MovieService service;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("movies", service.getAll());
        return "index";
    }

    @GetMapping("/genre")
    public String searchByGenre(@RequestParam(required = false) String genre, Model model) {
        if (genre != null && !genre.isEmpty()) {
            model.addAttribute("movies", service.getByGenre(genre));
        }
        return "genre";
    }

    @GetMapping("/add")
    public String showForm() {
        return "add";
    }

    @PostMapping("/add")
    public String addMovie(@RequestParam String name,
                           @RequestParam String genre,
                           @RequestParam double rating,
                           @RequestParam int releaseYear) {
        Movie m = new Movie();
        m.setName(name);
        m.setGenre(genre);
        m.setRating(rating);
        m.setReleaseYear(releaseYear);
        service.save(m);
        return "redirect:/";
    }


    @GetMapping("/edit/{id}")
    public String editMovie(@PathVariable Long id, Model model) {
        Movie movie = service.getById(id);
        if (movie != null) {
            model.addAttribute("movie", movie);
            return "edit"; // refers to edit.html
        }
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateMovie(@PathVariable Long id,
                              @RequestParam String name,
                              @RequestParam String genre,
                              @RequestParam double rating,
                              @RequestParam int releaseYear) {
        Movie updated = new Movie();
        updated.setName(name);
        updated.setGenre(genre);
        updated.setRating(rating);
        updated.setReleaseYear(releaseYear);
        service.update(id, updated);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }
}

