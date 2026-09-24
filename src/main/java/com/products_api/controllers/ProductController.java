package com.products_api.controllers;

import com.products_api.models.ProductModel;
import com.products_api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Products", description = "Endpoints de produtos")
@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Criar um novo produto", description = "Endpoint para criar produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product data")
    })
    @PostMapping
    public ResponseEntity<ProductModel> saveProduct(@RequestBody ProductModel productModel) {
        ProductModel product = productService.create(productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @Operation(summary = "List all products", description = "List all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products list")
    })
    @GetMapping
    public ResponseEntity<List<ProductModel>> listProducts() {
        List<ProductModel> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Get product by ID", description = "Get product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductModel> getById(@PathVariable UUID idProduct) {
        ProductModel product = productService.findById(idProduct);
        return ResponseEntity.ok().body(product);
    }

    @Operation(summary = "Delete product by ID", description = "Delete product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID idProduct) {
        productService.delete(idProduct);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update product by ID", description = "Update product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PutMapping("/{idProduct}")
    public ResponseEntity<ProductModel> updateProduct(
            @RequestBody ProductModel productModel,
            @PathVariable UUID idProduct) {
        ProductModel productEdited = productService.update(idProduct, productModel);
        return ResponseEntity.ok().body(productEdited);
    }

}
