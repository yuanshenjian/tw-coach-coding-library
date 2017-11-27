package com.thoughtworks.star.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.star.entity.*;
import com.thoughtworks.star.repository.ItemRepository;
import com.thoughtworks.star.repository.UserRepository;
import com.thoughtworks.star.service.UserService;
import com.thoughtworks.star.util.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ShoppingCartControllerTest extends BaseControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    @Override
    @BeforeEach
    void setup() {
        super.setup();
        Privilege privilege = Privilege.builder().name(Privilege.Symbol.CREATE_USER.description()).symbol(Privilege.Symbol.CREATE_USER).build();
        Role role = Role.builder().name(Role.Symbol.SYSTEM_ADMIN.description()).symbol(Role.Symbol.SYSTEM_ADMIN).build();
        role.setPrivileges(Collections.singletonList(privilege));
        userService.create(User.builder().role(role).shoppingCartItems(Collections.emptyList()).age(22).name("future_star").password("123").build());
    }


    /**
     * 3.2 添加商品到购物车
     */
    @Test
    void should_add_item_to_shopping_cart() throws Exception {
        loginWithUser("future_star");

        Item item = Item.builder().id(StringUtils.randomUUID()).name("iPhone8 64G").price(5888.8).build();
        itemRepository.save(item);

        Map<String, Object> itemIds = new HashMap<>();
        itemIds.put("itemId", item.getId());
        itemIds.put("quantity", 2);

        mockMvc.perform(post("/api/shopping-cart-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(itemIds)))
                .andExpect(status().isCreated());
        assertThat(userService.findShoppingCartItems(), hasSize(1));
    }

    /**
     * 4. 查看购物车里的商品
     */
    @Test
    void should_list_shopping_cart_items() throws Exception {
        loginWithUser("future_star");

        Item item = Item.builder().id(StringUtils.randomUUID()).name("iPhone8 64G").price(5888.8).build();
        Item item2 = Item.builder().id(StringUtils.randomUUID()).name("iPhone8 128G").price(7888.8).build();
        Item.builder().id(StringUtils.randomUUID()).name("iPhone8 32G").price(4888.8).build();
        ShoppingCartItem shoppingCartItem = ShoppingCartItem.builder().item(item).id(StringUtils.randomUUID()).build();
        ShoppingCartItem shoppingCartItem1 = ShoppingCartItem.builder().item(item2).id(StringUtils.randomUUID()).build();
        User user = userRepository.findByName("future_star");
        user.getShoppingCartItems().add(shoppingCartItem);
        user.getShoppingCartItems().add(shoppingCartItem1);
        userRepository.save(user);

        mockMvc.perform(get("/api/shopping-cart-items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}