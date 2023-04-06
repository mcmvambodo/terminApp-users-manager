package com.doitwell.group.springTesting.Movie;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

//@Entity
//@Table(name = "movies")
@RedisHash("movie")
public class Movie implements Serializable {

    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    private Long id;
    @NonNull
    private String title;
    @Column(nullable = true)
    private String description;
    private double price;

    private String image;
    private LocalDate created_at = LocalDate.now();

    public Movie(){}

    public Movie(Long id,@NonNull String title, String description, double price, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

}
