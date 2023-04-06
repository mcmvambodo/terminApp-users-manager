package com.doitwell.group.springTesting.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/movie")
public class MovieController {


    @Autowired
    private MovieRepository repo;



    @GetMapping
    public List<Movie> getAll(){
        return repo.findAll();
    }

    @GetMapping(path = "/{id}")
    public Movie getOne(@PathVariable("id") Long id){
        return repo.findById(id);
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie)
    {
        return repo.save(movie);
    }

    @DeleteMapping("{id}")
    public String deleteMovie(@PathVariable("id") Long id)
    {
        return repo.delete(id);
    }

}
