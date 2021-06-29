package in.stack.springboot.demoApp.controller;

import in.stack.springboot.demoApp.Service.ProductService;
import in.stack.springboot.demoApp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product/api/")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/add")
    public Product saveProduct(@RequestBody Product p){
        return service.saveProduct(p);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts(){
       return service.getProducts();
    }


}
