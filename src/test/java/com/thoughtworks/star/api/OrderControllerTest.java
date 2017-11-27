package com.thoughtworks.star.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.star.entity.*;
import com.thoughtworks.star.repository.ItemRepository;
import com.thoughtworks.star.repository.OrderRepository;
import com.thoughtworks.star.repository.UserRepository;
import com.thoughtworks.star.util.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends BaseControllerTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;
    private User user;

    @BeforeEach
    void setup() {
        super.setup();
        List<Address> addresses = new ArrayList<>();
        Address address = Address.builder().id(StringUtils.randomUUID()).description("SOHO T3").build();
        addresses.add(address);

        user = User.builder().id(StringUtils.randomUUID())
                .addresses(addresses).name("future_star").password("123456").age(22)
                .role(Role.builder().name("系统管理员").symbol(Role.Symbol.SYSTEM_ADMIN).build()).build();
        userRepository.saveAndFlush(user);
    }

    /**
     * 3.3 创建一个订单
     */
    @Test
    void should_create_order() throws Exception {
        loginWithUser("future_star", Privilege.Symbol.RETRIEVE_ORDER);

        Item item1 = Item.builder().id(StringUtils.randomUUID()).name("cola").price(3.0).build();
        Item item2 = Item.builder().id(StringUtils.randomUUID()).name("water").price(2.0).build();
        Item item3 = Item.builder().id(StringUtils.randomUUID()).name("tissue").price(1.0).build();
        itemRepository.save(Arrays.asList(item1, item2, item3));

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(Arrays.asList(item1.getId(), item2.getId(), item3.getId()))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.price", is(6.0)));
    }

    /**
     * 3.4 查看用户订单
     */
    @Test
    void should_return_user_order() throws Exception {
        loginWithUser("future_star", Privilege.Symbol.RETRIEVE_ORDER);

        Order order1 = Order.builder().id(StringUtils.randomUUID()).userId(user.getId()).price(99.0).build();
        Order order2 = Order.builder().id(StringUtils.randomUUID()).userId(user.getId()).price(109.0).build();
        Order order3 = Order.builder().id(StringUtils.randomUUID()).userId("other_user").price(19.0).build();
        orderRepository.save(Arrays.asList(order1, order2, order3));

        mockMvc.perform(get("/api/orders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}