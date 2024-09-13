package online.shop.product.product.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import online.shop.product.product.service.ProductService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDataProduct {
    private final ProductService productService;

    @PostConstruct
    public void initialize(){
        productService.initProduct();
    }
}

