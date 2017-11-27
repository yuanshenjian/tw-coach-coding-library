package com.thoughtworks.star.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.star.entity.Address;
import com.thoughtworks.star.entity.Privilege;
import com.thoughtworks.star.entity.Role;
import com.thoughtworks.star.entity.User;
import com.thoughtworks.star.repository.ItemRepository;
import com.thoughtworks.star.repository.UserRepository;
import com.thoughtworks.star.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AddressControllerTest extends BaseControllerTest {

    @Autowired
    private UserService userService;

    @Override
    @BeforeEach
    void setup() {
        super.setup();
        Privilege privilege = Privilege.builder().name(Privilege.Symbol.CREATE_USER.description()).symbol(Privilege.Symbol.CREATE_USER).build();
        Role role = Role.builder().name(Role.Symbol.SYSTEM_ADMIN.description()).symbol(Role.Symbol.SYSTEM_ADMIN).build();
        role.setPrivileges(Collections.singletonList(privilege));
        userService.create(User.builder().role(role).addresses(Collections.EMPTY_LIST).shoppingCartItems(Collections.emptyList()).age(22).name("future_star").password("123").build());
    }

    /**
     * 2.1 创建一个新的地址
     */
    @Test
    void should_create_address() throws Exception {
        loginWithUser("future_star");

        Address address = Address.builder().description("望京SOHO").build();
        mockMvc.perform(post("/api/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(address)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.description", is("望京SOHO")));
    }
}