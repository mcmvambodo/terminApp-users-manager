package com.doitwell.group.springTesting.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserDetailsServiceIntTest {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello() throws Exception{
        RequestBuilder req = MockMvcRequestBuilders.get("/api/test/hello");
        MvcResult result = mvc.perform(req).andReturn();
        assertEquals("Hello, World", result.getResponse().getContentAsString());
    }

    @Test
    public void testHelloWithName() throws Exception{
         mvc.perform(MockMvcRequestBuilders.get("/api/test/hello?name=MC")).andExpect(MockMvcResultMatchers.content().string("Hello, MC")); 
    }
}
