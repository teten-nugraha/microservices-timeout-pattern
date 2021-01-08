package id.ten.timeout.productservice.service;

import id.ten.timeout.commondto.ProductDto;

import java.util.concurrent.CompletionStage;

public interface ProductService {
    CompletionStage<ProductDto> getProductDto(int productId);
}
