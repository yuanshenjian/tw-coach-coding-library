package com.thoughtworks.star.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GreetingControllerTest extends BaseControllerTest {

    /**
     * 0. Greeting api
     *
     */
    @Test
    void should_return_greeting() throws Exception {
        mockMvc.perform(get("/api/greeting")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Hello world."));
    }

}