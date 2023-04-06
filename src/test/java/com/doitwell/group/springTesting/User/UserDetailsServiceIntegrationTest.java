package com.doitwell.group.springTesting.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import com.doitwell.group.springTesting.User.Roles.RolesUsers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserDetailsServiceIntegrationTest {
    
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository repo;

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost:"+port;

    @Test
    public void getAllUser() throws Exception{
        RequestBuilder req = MockMvcRequestBuilders.get(baseUrl+"/api/test/users");
        List<UserModel> list = getUsersFromDB();
        MvcResult res = mvc.perform(req)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(res.getResponse());
        assertThat(list).isNotNull();
    }

    @Test
    public void getUsers() throws Exception{
        MvcResult res  = this.mvc.perform(MockMvcRequestBuilders.get("/api/test/users"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();
        assertThat(res.getResponse()).isNotNull();
    }

    @Test
    public void addUser() throws Exception{
        MvcResult res =  this.mvc.perform(MockMvcRequestBuilders.post("/api/test/users")
        .param("firstname", "Korkor")
        .param("lastname", "Tehteh")
        .param("username", "korkor203")
        .param("password", "Tehteh1254")
        .param("email", "tehteh205@gmail.com")
        )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();

        assertEquals(res.getResponse().getContentAsString(), "user added succesfully");
        
    }

    @Test
    public void updateUser() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        UserModel user = new UserModel("MC", "Mvambodo", "mcmvambodo@gmail.com", "#Ajoeneya3",List.of(new RolesUsers("user")),
                "+237 699460092", LocalDate.parse("1996-09-29"),"Yaoundé","Passau",
                "Fruelingstrasse 6a","94032");
        MvcResult res = this.mvc.perform(MockMvcRequestBuilders.put("/api/test/users/3")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user)))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn();

        assertEquals("user updated successfully",res.getResponse().getContentAsString() );
    }

    @Test
    public void deleteUser() throws Exception{
        this.mvc.perform(MockMvcRequestBuilders.delete("/api/test/users/4"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("user deleted successfully"));
    }

    @Test
    public List<UserModel> getUsersFromDB(){
        return repo.findAll();
    }
}
