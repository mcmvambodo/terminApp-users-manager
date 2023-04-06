package com.doitwell.group.springTesting.Movie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@SpringBootTest
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        //assertThat()
    }

    @Test
    void getAll() throws Exception {
        MvcResult res =  this.mockMvc.perform(get("api/v1/movie"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(res.getResponse()).isNotNull();
    }

    @Test
    void getOne() {
    }

    @Test
    void updateMovie() {
    }

    @Test
    void addMovie() throws Exception{
        Movie movie = new Movie(1L,"Prison Break", "T. Bag chasse à l'homme dans tout le Nevada",1500,"http://www.images.com/1" );
        MvcResult res =  this.mockMvc.perform(get("/api/v1/movie")).andDo(print())
                .andReturn();

        //assertThat(res.getResponse().getContentAsString()).isEqualTo(movie.toString());
    }

    @Test
    void deleteMovie() {
    }
}