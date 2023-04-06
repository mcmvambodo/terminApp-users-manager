package com.doitwell.group.springTesting.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repo;

//    public List<Movie> getAllMovies(){
//        return repo.findAll();
//    }

//    public Movie getMovieById(Long id){
//
//        Optional<Movie> movie = repo.findById(id);
//        if (!movie.isPresent()){
//            throw new IllegalStateException("Movie with id "+id+ " does not exist") ;
//        }
//        return movie.get();
//    }

    public String addMovie(Movie movie){

        if (Objects.isNull(movie.getTitle()) | movie.getTitle().length()<3 | movie.getTitle().isBlank()){
            throw new IllegalStateException("You must enter a valid title with more than 3 characters");
        }
        if (Objects.isNull(movie.getPrice()) ){
            throw new IllegalStateException("You must enter a valid price");
        }
        if (Objects.isNull(movie.getImage()) | movie.getImage().length()<3 | movie.getImage().isBlank()){
            throw new IllegalStateException("You must enter a valid image url");
        }

        repo.save(movie);

        return "movie added successfully";
    }

//    @Transactional
//    public String updateMovie(Long id, String title, String description, Double price, String image ){
//
//        Movie movie = repo.findById(id)
//                .orElseThrow(()->{
//                    throw new IllegalStateException("Movie with id "+id+" does not exist");
//                });
//
//        if (!Objects.isNull(title) && title.length()>0 && !title.isBlank() && !Objects.equals(title, movie.getTitle())){
//            movie.setTitle(title);
//        }
//        if (!Objects.isNull(image) && image.length()>0 && !image.isBlank() && !Objects.equals(image, movie.getImage())){
//            movie.setImage(image);
//        }
//        if (!Objects.isNull(description) && description.length()>0 && !description.isBlank() && !Objects.equals(description, movie.getDescription())){
//            movie.setDescription(description);
//        }
//        if (!Objects.isNull(price) && !Objects.equals(price, movie.getTitle())){
//            movie.setPrice(price);
//        }
//        repo.save(movie);
//        return "Movie updated successfully";
//    }

    public String deleteMovie(Long id){

//        Movie movie = repo.findById(id)
//                .orElseThrow(()->{
//                    throw new IllegalStateException("Movie with id "+id+" does not exist");
//                });
//
//        repo.delete(movie);
        return "Movie with id "+id+" deleted successfully.";
    }


}
