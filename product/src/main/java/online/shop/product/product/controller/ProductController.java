package online.shop.product.product.controller;

import lombok.RequiredArgsConstructor;
import online.shop.product.product.model.dto.*;
import online.shop.product.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/init-product")
    public void initProduct() {
        productService.initProduct();
    }

    @GetMapping("/list")
    public List<ProductListResponse> getProductList() {
        return productService.getProductList();
    }

    @GetMapping("/detail/{id}")
    public ProductDetailResponse getProductDetail(@PathVariable String id) {
        return productService.getProductDetail(id);
    }

    @PostMapping("/cart")
    public AddCartResponse addToCart(@RequestBody CartRequestDto request,
                                     @RequestHeader("USER-DETAILS") HeaderRequestDto header) {
        return productService.addCart(request, header);
    }

    @GetMapping("/cart/{userId}")
    public ViewCartResponse viewCart(@RequestHeader("USER-DETAILS") HeaderRequestDto header) {
        return productService.getCarts(header);
    }
}