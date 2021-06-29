package in.stack.springboot.demoApp.Service;

import in.stack.springboot.demoApp.model.Product;
import in.stack.springboot.demoApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;


    public Product saveProduct(Product p){
        return repo.save(p);
    }

    public List<Product> getProducts(){
        return repo.findAll();
    }

    public Optional<Product> getProducts(int pid){
        return repo.findById(pid);
    }



}
