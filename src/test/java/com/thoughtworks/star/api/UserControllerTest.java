package com.thoughtworks.star.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.star.entity.Privilege;
import com.thoughtworks.star.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseControllerTest {

    /**
     * 1.3. 创建一个用户
     */
    @Test
    void should_create_user() throws Exception {
        loginWithUser("future_star", Privilege.Symbol.CREATE_USER);

        User user = User.builder().name("new_future_star").password("123456").age(22).build();
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("new_future_star"))
                .andExpect(jsonPath("$.password", nullValue()))
                .andExpect(jsonPath("$.age").value(22));
    }

}