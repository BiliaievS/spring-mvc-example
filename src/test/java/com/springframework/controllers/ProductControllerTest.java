package com.springframework.controllers;

import com.springframework.domain.Product;
import com.springframework.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by sbiliaiev on 06/08/17.
 */
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testList() throws  Exception {

        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        when(productService.listAll()).thenReturn((List)products);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/products"))
                .andExpect(model().attribute("products", hasSize(2)));
    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;

        when(productService.getById(id)).thenReturn(new Product());

        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/product"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testEdit() throws Exception {
        Integer id = 1;

        when(productService.getById(id)).thenReturn(new Product());

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testNewProduct() throws Exception {
        Integer id = 1;
        verifyZeroInteractions(productService);
        mockMvc.perform(get("/product/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String description = "Test Description";
        BigDecimal price = new BigDecimal("11.00");
        String imageUrl = "image.com";

        Product testProduct = new Product();
        testProduct.setId(id);
        testProduct.setDescription(description);
        testProduct.setPrice(price);
        testProduct.setImageURL(imageUrl);

        when(productService.saveOrUpdate(Matchers.any())).thenReturn(testProduct);

        mockMvc.perform(post("/product")
                .param("id", id.toString())
                .param("description", description)
                .param("price", price.toString())
                .param("imageURL", imageUrl))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/product/1"))
                    .andExpect(model().attribute("product", instanceOf(Product.class)))
                    .andExpect(model().attribute("product", hasProperty("id", is(id))))
                    .andExpect(model().attribute("product", hasProperty("description", is(description))))
                    .andExpect(model().attribute("product", hasProperty("price", is(price))))
                    .andExpect(model().attribute("product", hasProperty("imageURL", is(imageUrl))));

        ArgumentCaptor<Product> boundProduct = ArgumentCaptor.forClass(Product.class);
        verify(productService).saveOrUpdate(boundProduct.capture());

        assertEquals(id, boundProduct.getValue().getId());
        assertEquals(description, boundProduct.getValue().getDescription());
        assertEquals(price, boundProduct.getValue().getPrice());
        assertEquals(imageUrl, boundProduct.getValue().getImageURL());
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;

        mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/list"));

        verify(productService, times(1)).delete(id);
    }
}