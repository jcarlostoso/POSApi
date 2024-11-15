package com.jtorresDev.posAPI.controller;

import com.jtorresDev.posAPI.abstract_services.IProductsService;
import com.jtorresDev.posAPI.models.request.ProductRequest;
import com.jtorresDev.posAPI.models.response.ProductResponse;
import com.jtorresDev.posAPI.util.enums.SortType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "product")
@AllArgsConstructor
public class ProductController {
    private final IProductsService productsService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello Products");
    }
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestHeader(required = false) SortType sortType
    ){
        if (Objects.isNull(sortType)) sortType = SortType.NONE;
        var response = this.productsService.realAll(page, size, sortType);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productsService.read(id));

    }

    @PostMapping
    public ResponseEntity<ProductResponse>createProduct(@Valid @RequestBody ProductRequest request){
        return ResponseEntity.ok(this.productsService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse>updateProduct(@Valid @RequestBody ProductRequest request, @PathVariable Long id){
        return ResponseEntity.ok(this.productsService.update(request,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse>deleteProduct(@PathVariable Long id){
        this.productsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
