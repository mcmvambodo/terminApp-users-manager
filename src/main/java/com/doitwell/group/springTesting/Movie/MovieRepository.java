package com.doitwell.group.springTesting.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepository{

    public final static String HASH_KEY = "movie";

    @Autowired
    private RedisTemplate template;

    public Movie save(Movie movie){
        template.opsForHash().put(HASH_KEY,movie.getId(),movie);
        return movie;
    }

    public List<Movie> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public Movie findById(Long id){
        return (Movie) template.opsForHash().get(HASH_KEY,id);
    }

    public String delete(Long id){
        template.opsForHash().delete(HASH_KEY,id);
        return "movie deleted successfully";
    }


}
