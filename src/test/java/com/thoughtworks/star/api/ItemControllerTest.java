package com.thoughtworks.star.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.star.entity.Item;
import com.thoughtworks.star.repository.ItemRepository;
import com.thoughtworks.star.util.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemControllerTest extends BaseControllerTest {

    @Autowired
    private ItemRepository itemRepository;

    /**
     * 2. 查询商品列表
     */
    @Test
    void should_list_items() throws Exception {
        Item item = Item.builder().id(StringUtils.randomUUID()).name("iPhone8 64G").price(5888.8).build();
        Item item2 = Item.builder().id(StringUtils.randomUUID()).name("iPhone8 128G").price(7888.8).build();
        itemRepository.save(Arrays.asList(item, item2));

        mockMvc.perform(get("/api/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("iPhone8 64G", "iPhone8 128G")))
                .andExpect(jsonPath("$[*].price", containsInAnyOrder(5888.8, 7888.8)));
    }

    /**
     * 3.1 创建商品
     */
    @Test
    void should_create_item() throws Exception {
        Item item = Item.builder().name("iPhone8 64G").price(5888.8).build();
        mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item)))
                .andExpect(status().isCreated());
    }


}