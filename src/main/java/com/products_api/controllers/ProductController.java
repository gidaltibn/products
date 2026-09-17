package com.products_api.controllers;

import com.products_api.models.ProductModel;
import com.products_api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductModel> saveProduct(@RequestBody ProductModel productModel) {
        ProductModel product = productService.create(productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> listProducts() {
        List<ProductModel> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductModel> getById(@PathVariable UUID idProduct) {
        ProductModel product = productService.findById(idProduct);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID idProduct) {
        productService.delete(idProduct);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<ProductModel> updateProduct(
            @RequestBody ProductModel productModel,
            @PathVariable UUID idProduct
    ) {
        ProductModel productEdited = productService.update(idProduct, productModel);
        return ResponseEntity.ok().body(productEdited);
    }


}
