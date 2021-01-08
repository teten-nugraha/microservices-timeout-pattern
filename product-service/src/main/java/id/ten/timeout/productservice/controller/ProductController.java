package id.ten.timeout.productservice.controller;

import id.ten.timeout.commondto.ProductDto;
import id.ten.timeout.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("{productId}")
    public CompletionStage<ProductDto> getProduct(@PathVariable int productId){
        return this.productService.getProductDto(productId);
    }

}