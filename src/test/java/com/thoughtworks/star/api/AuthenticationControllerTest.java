package com.thoughtworks.star.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.star.configuration.security.LoginRequestUser;
import com.thoughtworks.star.dto.LoginRequestBody;
import com.thoughtworks.star.entity.Privilege;
import com.thoughtworks.star.entity.Role;
import com.thoughtworks.star.entity.User;
import com.thoughtworks.star.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthenticationControllerTest extends BaseControllerTest {

    @Autowired
    private UserService userService;

    @Override
    @BeforeEach
    void setup() {
        super.setup();
        Privilege privilege = Privilege.builder().name(Privilege.Symbol.CREATE_USER.description()).symbol(Privilege.Symbol.CREATE_USER).build();
        Role role = Role.builder().name(Role.Symbol.SYSTEM_ADMIN.description()).symbol(Role.Symbol.SYSTEM_ADMIN).build();
        role.setPrivileges(Collections.singletonList(privilege));
        userService.create(User.builder().role(role).age(22).name("future_star").password("123").build());
    }

    /**
     * 1 登录API，登录成功
     */
    @Test
    void should_login_successfully() throws Exception {
        LoginRequestUser loginRequestBody = LoginRequestUser.builder()
                .username("future_star").password("123").build();

        mockMvc.perform(post("/api/authentication")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("future_star"));
    }

    /**
     * 1 登录API，登录失败
     */
    @Test
    void should_login_failed_when_login_with_bad_credential() throws Exception {
        LoginRequestBody loginRequestBody = LoginRequestBody.builder()
                .username("future_sta").password("wrong_password").build();

        mockMvc.perform(post("/api/authentication")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequestBody)))
                .andExpect(status().isUnauthorized());
    }

}