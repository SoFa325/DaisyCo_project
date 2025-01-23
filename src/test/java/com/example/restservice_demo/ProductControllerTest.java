package com.example.restservice_demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    private MockMvc mockMvc;
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        productController = new ProductController();
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testCreateProduct() throws Exception {
        mockMvc.perform(post("/create")
                        .param("name", "cavier")
                        .param("description", "kvkvk")
                        .param("price", "120")
                        .param("in_sight", "true"))
                .andExpect(status().isOk());

        Product product = productController.products.get("cavier");
        assertNotNull(product);
        assertEquals("kvkvk", product.getDescription());
        assertEquals(120.0, product.getPrice());
        assertTrue(product.isIn_sight());
    }

    @Test
    public void testGetProduct() throws Exception {
        productController.createProduct("cavier", "kvkvk", 120, true);

        mockMvc.perform(get("/getProduct")
                        .param("name", "cavier"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("cavier"))
                .andExpect(jsonPath("$.description").value("kvkvk"))
                .andExpect(jsonPath("$.price").value(120))
                .andExpect(jsonPath("$.in_sight").value(true));
    }

    @Test
    public void testGetProducts() throws Exception {
        productController.createProduct("cavier", "kvkvk", 120, true);
        productController.createProduct("salmon", "delicious", 300, true);

        mockMvc.perform(get("/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        productController.createProduct("cavier", "kvkvk", 120, true);

        mockMvc.perform(post("/update")
                        .param("name", "cavier")
                        .param("property", "price")
                        .param("value", "150"))
                .andExpect(status().isOk());

        Product product = productController.products.get("cavier");
        assertEquals(150.0, product.getPrice());
    }

    @Test
    public void testDeleteProduct() throws Exception {
        productController.createProduct("cavier", "kvkvk", 120, true);

        mockMvc.perform(post("/delete")
                        .param("name", "cavier"))
                .andExpect(status().isOk());

        Product product = productController.products.get("cavier");
        assertNull(product);
    }
}