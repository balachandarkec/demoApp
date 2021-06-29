package in.stack.springboot.demoApp.repository;

import in.stack.springboot.demoApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findByName(String name);
}
