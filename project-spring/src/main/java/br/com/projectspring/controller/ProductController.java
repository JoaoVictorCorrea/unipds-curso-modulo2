package br.com.projectspring.controller;

import br.com.projectspring.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@RestController
public class ProductController {

    private ArrayList<Product> database;

    public ProductController() {
        this.database = new ArrayList<>() {{
            add(new Product(1, "Computer", 1500.0));
            add(new Product(2, "Mouse", 50.0));
            add(new Product(3, "Keyboard", 100.0));
            add(new Product(4, "Monitor", 500.0));
            add(new Product(5, "Printer", 350.0));
        }};
    }

    @GetMapping("/products")
    public ArrayList<Product> getProducts() {
        return database;
    }

    @GetMapping("/products/sort")
    public ResponseEntity<List<Product>> getProductsSorted(@RequestParam(name = "order", required = false) String order){
        if(order == null){
            return ResponseEntity.ok(database);
        }

        if(order.equals("asc")){
            return ResponseEntity.ok(database.stream().sorted(Comparator.comparing(Product::getPrice)).toList());
        }

        if(order.equals("desc")){
            return ResponseEntity.ok(database.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).toList());
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = database.stream()
                            .filter(p -> p.getId() == id)
                            .findFirst()
                            .orElse(null);

        if (product != null) {
            return ResponseEntity.ok(product);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        database.add(product);
        return product;
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        int index = IntStream.range(0, database.size())
                .filter(i -> database.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if (index == -1) {
            return ResponseEntity.notFound().build();
        }

        database.set(index, updatedProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        int index = IntStream.range(0, database.size())
                .filter(i -> database.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if (index == -1) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(database.remove(index));
    }
}
