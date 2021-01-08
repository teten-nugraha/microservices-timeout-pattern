package id.ten.timeout.productservice.service;

import id.ten.timeout.commondto.ProductRatingDto;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

@Service
public class RatingServiceClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${rating.service.endpoint}")
    private String ratingService;

    @TimeLimiter(name = "ratingService", fallbackMethod = "getDefault")
    public CompletionStage<ProductRatingDto> getProductRatingDto(int productId){
        Supplier<ProductRatingDto> supplier = () ->
                this.restTemplate.getForEntity(this.ratingService + productId, ProductRatingDto.class)
                        .getBody();
        return CompletableFuture.supplyAsync(supplier);
    }

    private CompletionStage<ProductRatingDto> getDefault(int productId, Throwable throwable){
        return CompletableFuture.supplyAsync(() -> ProductRatingDto.of(0, Collections.emptyList()));
    }

}