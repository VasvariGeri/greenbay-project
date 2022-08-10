package com.gfa.homework.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfa.homework.security.AuthenticationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class APIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createAuthenticationToken_WithValidLogin_ShouldReturnOkAndToken() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername("foo");
        authenticationRequest.setPassword("foo");
        ObjectMapper mapper = new ObjectMapper();
        String postAuthenticationRequest = mapper.writeValueAsString(authenticationRequest);

        mockMvc
                .perform(
                        post("/authenticate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(postAuthenticationRequest))
                .andExpect(status().isOk());
    }

    @Test
    public void createNewItem_withCorrectDTOValues_shouldReturnOkAndItem(){

    }
}
