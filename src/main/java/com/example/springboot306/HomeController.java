package com.example.springboot306;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    DirectorRepository directorRepository;
    @RequestMapping("/")
    public String index(Model model){
        //First let's create a director
        Director director = new Director();
        director.setName("Stephan Bullock");
        director.setGenre("Sci Fri");
        //Now let's create a movie
        Movie movie = new Movie();
        movie.setTitle("Star Movie");
        movie.setYear(2019);
        movie.setDescription("About Stars...");
        //Add the movie to an empty list
        Set<Movie> movies = new HashSet<>();
        movies.add(movie);

        movie= new Movie();
        movie.setTitle("DeathStar Eworks");
        movie.setYear(2011);
        movie.setDescription("About Eworks on the DeathStar...");
        movies.add(movie);
        //Add the list of movies to the directors's movie list
        director.setMovies(movies);
        //Save the director to the database
        directorRepository.save(director);
        //Grad all the directors from the database and send them to the template
        model.addAttribute("directors", directorRepository.findAll());
        return "index";
    }
}
