package in.stack.springboot.demoApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.stack.springboot.demoApp.Service.ProductService;
import in.stack.springboot.demoApp.controller.ProductController;
import in.stack.springboot.demoApp.model.Product;
import in.stack.springboot.demoApp.repository.ProductRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductAppTest {
    @MockBean
    private ProductRepository repo;
    @MockBean
    private ProductService service;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc ;

    @Test
    public void productModelTest(){
        Product p= Mockito.mock(Product.class);
        assertNotNull(p);
    }

    @Test
    public void productControllerTest(){
        ProductController pc= Mockito.mock(ProductController.class);
        assertNotNull(pc);
    }

    @Test
    public void getAllProductsTest()throws Exception{

        List<Product> products=new ArrayList<Product>();
        products.add(new Product(101,"Mango",30d,40));
        products.add(new Product(102,"Mango-Banasa",30d,40));
        products.add(new Product(103,"Mango-Alphonsa",30d,40));

        Mockito.when(service.getProducts()).thenReturn(products);

        mockMvc.perform(get("/product/api/all")).andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("Mango")))
                .andExpect(jsonPath("$[1].name",Matchers.equalTo("Mango-Banasa")))
                .andExpect(jsonPath("$[1].price",Matchers.equalTo(30d)));

    }


    @Test
    public void testAddProduct() throws Exception {
        Product p=new Product(105,"Mango",2,300);

        Mockito.when(service.saveProduct(ArgumentMatchers.any())).thenReturn(p);

        String json_content=mapper.writeValueAsString(p);

        mockMvc.perform(post("/product/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                         .characterEncoding("utf-8")
                          .content(json_content)
                           .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().isOk())
                             .andExpect(jsonPath("$.id",Matchers.equalTo(105)));



    }


}
